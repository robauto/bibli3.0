#!/usr/bin/env python
import urllib2
import json
import sys
import calendar
from datetime import datetime
import redis

from python_http_client import Client

from db import set_kv, get_kv
from app import ip_address, self_dict


def main():
	r = redis.StrictRedis(host='localhost', port=6379, db=0)
	# first look for a router and trust that as an authority if it exists
	router = check_router(r)
	if router:
		return
	# compare current IP to the last know one to see if we've changed networks
	# or at least lost our DHCP lease, implying other BiBlis may have done the same
	last_ip = get_kv("ip")
	ip = ip_address()
	set_kv("ip", ip)
	# if we've changed IP, delete the bibli cache and start fresh
	if last_ip != ip:
		brute_force_scan(r)
		return
	# we still have the same IP, refreh communication
	refresh_scan(r)


def brute_force_scan(r):
	"""Brute force scan the entire subnet looking for BiBlis."""
	print("Running brute force scan of subnet")
	ip = ip_address()
	ip = [int(x) for x in ip.split(".")]
	scan_ip = 0
	biblis = []
	while scan_ip < 256:
		ip_text = "%d.%d.%d.%d" % (ip[0], ip[1], ip[2], scan_ip)
		print("Scanning %s" % ip_text)
		bibli = scan_for_bibli(ip_text)
		if bibli:
			bibli["last_seen"] = calendar.timegm(datetime.utcnow().timetuple())
			biblis.append(bibli)
		scan_ip += 1
		if scan_ip == ip[3]:
			scan_ip += 1
	clear_biblis(r)
	for bibli in biblis:
		r.lpush("biblis", bibli["ip"])
		r.hmset("bibli:%s" % bibli["ip"], {"name": bibli["name"], "fqdn": bibli["fqdn"], "ip": bibli["ip"], "last_seen": bibli["last_seen"]})

def clear_biblis(r):
	# delete all the existing extries for biblis
	for ip in r.lrange("biblis", 0, -1):
		r.delete("bibli:%s" % ip)
	r.delete("biblis")

def refresh_scan(r):
	"""Re-establish communications with all the biblis we know about."""
	now = calendar.timegm(datetime.utcnow().timetuple())
	print("Refreshing communication")
	local_ip = ip_address()
	# dict to accumulate all the biblis we learn about
	# keyed by their IP address
	biblis = {}
	# populate the existing bibli cache
	for ip in r.lrange("biblis", 0, -1):
		bibli = r.hgetall("bibli:%s" % ip)
		# if it's more than 11 minutes old, forget about it
		if bibli["last_seen"] < now - 60 * 11:
			continue
		biblis[ip] = bibli
	# ask every BiBli we know about about all the BiBlis they know about
	for ip in biblis.keys():
		client = Client(host="http://%s" % ip)
		resp = client.api.bibli.get()
		if resp.status_code == 200:
			d = json.loads(resp.body)
			for bibli in d["biblis"]:
				# don't populate own entry
				if bibli["ip"] == local_ip:
					continue
				# if it's more than 11 minutes old, forget about it
				if bibli["last_seen"] < now - 60 * 11:
					continue
				# add this bibli to the dictionary of biblis if it doesn't exist yet
				# or if it's got a more recent last_seen timestamp
				if bibli["ip"] not in biblis or bibli["last_seen"] > biblis[bibli["ip"]]["last_seen"]:
					biblis[bibli["ip"]] = bibli
	clear_biblis(r)
	for bibli in biblis.values():
		r.hmset("bibli:%s" % bibli["ip"], {"name": bibli["name"], "fqdn": bibli["fqdn"], "ip": bibli["ip"], "last_seen": bibli["last_seen"]})

def check_router(r):
	# first look for a Bibli router
	# trust that as sole authority if it exists
	client = Client(host="http://router.robot")
	try:
		resp = client.api.bibli.get()
		if resp.status_code == 200:
			d = json.loads(resp.body)
			# remove any existing biblis
			clear_biblis(r)
			for bibli in d["biblis"]:
				# don't populate own entry
				if bibli["ip"] == local_ip:
					continue
				r.hmset("bibli:%s" % ip, {"name": bibli["name"], "fqdn": bibli["fqdn"], "ip": bibli["ip"], "last_seen": bibli["last_seen"]})
			set_kv("router", True)
			return
	except:
		pass
	set_kv("router", False)


def scan_for_bibli(ip):
	url = "http://%s/api/whoami" % ip
	try:
		result = urllib2.urlopen(url, timeout=0.7)
		d = json.loads(result.read())
		print(d)
	except:
		d = None
	# if we find a bibli on this IP, give them our info
	if d:
		introduce(ip)
	return d

def introduce(ip):
	client = Client(host='http://%s' % ip)
	data = {
		"bibli": self_dict()
	}
	client.api.bibli.post(request_body=data)

def populate_bibli(bibli, c):
	# Insert or replace the entry for this IP address, keeping the most recent last_seen
	print("populating")
	print(bibli)
	sql = """INSERT OR REPLACE INTO bibli (ip, name, fqdn, last_seen) VALUES (?,?,?,max((SELECT last_seen FROM bibli WHERE ip = ?),?))"""
	c.execute(sql, (bibli["ip"], bibli["name"], bibli["fqdn"], bibli["ip"], bibli["last_seen"]))

if __name__ == '__main__':
	main()
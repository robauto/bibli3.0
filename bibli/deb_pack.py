#!/usr/bin/env python

# Debian package creation script
# by James Gardner for Robauto
# started 2016-12-15

import py_compile
from glob import glob
import os
import subprocess
from shutil import copy2 as copy

install_dir = "var/local/bibli/"
pyc_dirs = ["brain/startup/",]
py_dirs = ["demos",]
everything_dirs = [
	"brain/startup/templates/",
	"brain/startup/static/"
]

files = []

for pyc_dir in pyc_dirs:
	for fname in glob(pyc_dir + "*.py") + glob(pyc_dir + "*/*.py"):
		if not os.path.isfile(fname + "c"):
			py_compile.compile(fname)
		files.append(fname + "c")

for py_dir in py_dirs:
	for fname in glob(py_dir + "*.py") + glob(py_dir + "*/*.py"):
		files.append(fname)

for edir in everything_dirs:
	for fname in glob(edir + "*.*") + glob(edir + "*/*.*"):
		files.append(fname)

# sample media files to create the directories
files.append("media/audio/story/storytime_11_peterrabbit_potter_64kb.mp3")
files.append("media/audio/music/grapes_-_I_dunno.mp3")



# clear out the debian directory
subprocess.call("rm -rf debian/var/local/bibli && rm -rf debian/usr && rm -rf debian/etc", shell=True)
# recreate directory structure
dirs = [
	"brain/startup/templates",
	"brain/startup/static/js",
	"brain/startup/static/css",
	"brain/startup/static/img",
	"brain/startup/python_http_client",
	"demos",
	"media/audio/story",
	"media/audio/music"
]
for d in dirs:
	os.makedirs("debian/var/local/bibli/%s" % d)
# make the directory for the flask-sockets library, that doesn't have an existing debian packaeg
os.makedirs("debian/usr/local/lib/python2.7/dist-packages")
# make the configuration directories
os.makedirs("debian/etc/nginx/sites-available")
os.makedirs("debian/etc/supervisor/conf.d")
os.makedirs("debian/etc/redis")

# cp the files into their location in the debian directory
dfiles = []
for fname in files:
	dfname = "var/local/bibli/%s" % fname
	copy(fname, "debian/%s" % dfname)
	dfiles.append(dfname)
# copy the flask-sockets library
copy("brain/startup/flask_sockets.py", "debian/usr/local/lib/python2.7/dist-packages")

linx_conf = [
	"etc/nginx/nginx.conf",
	"etc/nginx/sites-available/bibli",
	"etc/redis/redis.conf",
	"etc/supervisor/conf.d/bibli.conf"
]
for cfile in linx_conf:
	copy("linux_conf/%s" % cfile, "debian/%s" % cfile)

subprocess.call("dpkg-deb --build debian", shell=True)

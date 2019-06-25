import redis

def set_kv(key, value, expires=3600):
	try:
		r = redis.StrictRedis(host='localhost', port=6379, db=0)
		if value is None:
			r.delete(key)
		else:
			r.set(key, value, ex=expires)
	except Exception as e:
		if not expires:
			raise e

def get_kv(key):
	r = redis.StrictRedis(host='localhost', port=6379, db=0)
	val = r.get(key)
	return val if val != "None" else None
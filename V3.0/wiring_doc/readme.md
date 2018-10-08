BiBli is controlled by Restful and websocket based API, with a web interface.

Technologies
-----

* **Redis** - A light, fast, in-memory lightly structured datastore. Allows persistence and multiple workers. Used mostly as a simple key-value store. But also stores structured data and an index for keeping track of the other biblis and bibli knows about. https://redis.io
* **Python 2.7** - There's seemingly no good server stack for 3 yet. I tried to use 3 for a while and it was painful. That makes me so sad. For purposes of keeping packages updated, 2.7 allows us to work almost entirely with libraries that are in the apt repository.
* **Flask** - Lightweight web framework that doesn't make a lot of assumptions and allows easy API building. http://flask.pocoo.org
* **Flask Sockets** - Integrates gevent and gevent-websockets into Flask to give it websocket support. https://github.com/kennethreitz/flask-sockets
* **Gunicorn** - Industrial grade WSGI server. We really shouldn't be running the Flask development server for production use. http://gunicorn.org
* **Supervisor** - Ensures Gunicorn/Flask stays up and running. http://supervisord.org
* **Nginx** - Lightweight apache alternative that all the cool kids are using now. Serves all the staic files and reverse-proxies API calls and websockets back to Gunicorn/Flask.
* **ESpeak** - Text to speech engine. http://espeak.sourceforge.net https://launchpad.net/python-espeak
* **Material Design Lite** - Basic framework for web interface. https://getmdl.io/components/index.html#lists-section

How to recreate setup
=====

* Start with raspbian jessie lite
* sudo su
* apt-get update && apt-get dist-upgrade
* apt-get install vim nginx python-flask python-mutagen python-netifaces python-pil gunicorn supervisor mplayer2 python-redis redis-server python-gevent python-gevent-websocket python-picamera python-espeak espeak python-bluez bluez bluez-tools pulseaudio-module-bluetooth python-pexpect rfkill
* raspi-config to expand partition and enable camera
* mkdir /var/local/bibli && chown pi:pi /var/local/bibli
* rm /etc/nginx/sites-available/default
* touch /etc/nginx/sites-available/bibli && ln -s /etc/nginx/sites-available/bibli /etc/nginx/sites-enabled/
* chmod 664 /etc/hosts && chmod 664 /etc/hostname
& chgrp pi /etc/hosts && chgrp pi /etc/hostname
* /etc/nginx/nginx.conf - change client_max_body_size 100M;
* https://github.com/davidedg/NAS-mod-config/blob/master/bt-sound/bt-sound-Bluez5_PulseAudio5.txt


/etc/supervisor/conf.d/bibli.conf
-----
	[program:bibli]
	command = /usr/bin/gunicorn -w 2 -k flask_sockets.worker app:app -b :5000
	directory = /var/local/bibli/brain/startup/
	user = pi
	autostart=true
	autorestart=true
	stdout_logfile=/var/log/flask.out.log
	stdout_logfile_maxbytes=10MB
	stdout_logfile_backups=5
	stderr_logfile=/var/log/flask.err.log
	stderr_logfile_maxbytes=10MB
	stderr_logfile_backups=5
	environment=SDL_AUDIODRIVER="pulse"


/etc/nginx/sites-available/bibli
-----
	server {
	    location /static {
	        alias  /var/local/bibli/brain/startup/static/;
	    }
	    location /move {
	        proxy_pass http://localhost:5000;
	        proxy_http_version 1.1;
	        proxy_set_header Upgrade $http_upgrade;
	        proxy_set_header Connection "upgrade";
	    }
	    location /video {
	        proxy_pass http://localhost:5000;
	        proxy_http_version 1.1;
	        proxy_set_header Upgrade $http_upgrade;
	        proxy_set_header Connection "upgrade";
	    }
	    location / {
	        proxy_pass http://localhost:5000;
	        proxy_set_header Host $host;
	        proxy_set_header X-Real-IP $remote_addr;
	    }
	}


Extra Router Steps
=====
* sudo apt-get install dnsmasq hostapd
* https://frillip.com/using-your-raspberry-pi-3-as-a-wifi-access-point-with-hostapd/
	* Use 10.1.1 for IP block
* Change machine name to router in /etc/hostname
* Change to router and 10.1.1.1 in /etc/hosts

/etc/dnsmasq.conf
-----

	interface=wlan0      # Use interface wlan0  
	listen-address=10.1.1.1
	listen-address=127.0.0.1
	bind-interfaces      # Bind to the interface to make sure we aren't sending things elsewhere  
	server=8.8.8.8       # Forward DNS requests to Google DNS
	server=8.8.4.4
	domain-needed        # Don't forward short names  
	bogus-priv           # Never forward addresses in the non-routed address spaces.  
	expand-hosts
	domain=robot
	local=/robot/		# .robot requests always handled locally
	dhcp-range=10.1.1.10,10.1.1.200,12h 

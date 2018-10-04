# -*- coding: utf-8-*-
import subprocess
import re
import random

WORDS = ["VIDEO"]

def handle(text, mic, profile):
	n = random.randint(1,7)
	if (n==1): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom1.mp4", shell = True)
	elif(n==2): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom2.mp4", shell = True)
	elif(n==3): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom3.mp4", shell = True)
	elif(n==4): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom4.mp4", shell = True)
	elif(n==5): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom5.mp4", shell = True)
	elif(n==6): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom6.mp4", shell = True)
	elif(n==7): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop /home/pi/videos/backkom7.mp4", shell = True)
	
	subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/gif/bg2.jpg", shell=True)

def isValid(text):
    return bool(re.search(r'\bvideo\b', text, re.IGNORECASE))

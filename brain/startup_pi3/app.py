#!/usr/bin/env python
from flask import Flask, render_template, Response
import base
import play_video
import time
import subprocess
import socket
import fcntl
import struct

# emulated camera
#from camera import Camera

# Raspberry Pi camera module (requires picamera package)
from camera_pi import Camera

app = Flask(__name__)

movedir = base.MoveCtrl()
movie_1 = play_video.Play_vdo()



#pan =servo.ServoController(25)
#pan.setAngle(0)

#tilt =servo.ServoController(24)
#tilt.setAngle(0)

#motorA = motor.MotorController(8,7)
#motorB = motor.MotorController(10,9)

@app.route('/')
def index():
    """Video streaming home page."""
    return render_template('index.html')

def gen(camera):
    """Video streaming generator function."""
    while True:
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(Camera()),
                    mimetype='multipart/x-mixed-replace; boundary=frame')
					
@app.route("/<direction>")
def moveon(direction):
	# Choose the direction of the request
	#print direction
	if direction == 'f':
		movedir.move('f')
        
	elif direction == 'l':
		movedir.move('l')

	elif direction == 'r':
		movedir.move('r')

	elif direction == 'b':
		movedir.move('b')

	elif direction =='s':
		movedir.move('s')

	elif direction =='v':
		movedir.move('v')
	
	elif direction =='x':
		movedir.move('x')
		subprocess.call("sudo SDL_VIDEODRIVER=fbcon SDL_FBDEV=/dev/fb1 mplayer -vo sdl -framedrop -quiet -input file=/home/pi/fifos /home/pi/BiBli/brain/demos/bibli_demo.mp4 < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg &", shell=True)
                time.sleep(2)
		movedir.move('s')		
    	
	return direction

@app.route('/desktop/<dstp>')
def show_desktop(dstp):
	if dstp=='show':
		subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)


@app.route('/jasper/<jsp>')
def jasper_ctrl(jsp):
	print jsp 
	if jsp == 'jON':
		subprocess.call("/home/pi/BiBli/language/jasper/jasper.py &", shell=True)
	elif jsp == 'jOFF':
		subprocess.call("pkill -f jasper.py", shell=True)
	

@app.route('/movie/<movie>')
def play_movie(movie):
	print movie 
	if movie == 'p':
		subprocess.call("/home/pi/rpi-fbcp/build/fbcp &", shell=True)
	elif movie == 'o':
		subprocess.call("pkill fbcp", shell=True)
		subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
		

@app.route('/music/<music>')
def play_music(music):
	print music
	if music == 'mon':
		subprocess.call("mplayer -input file=/home/pi/fifos2 /home/pi/BiBli/media/audio/music/Argon_Dont_Speak.mp3 < /dev/null &", shell=True)
	elif music =='moff':
		subprocess.call("echo 'q' > /home/pi/fifos2", shell = True)


@app.route('/saysth/<msg>')
def saymsg(msg):
	print msg
	msgquote= '"{}"'.format(msg)
	subprocess.call('espeak -ven+f3 -a200 '+ msgquote, shell=True)

@app.route('/saysth_es/<msg>')
def saymsg_es(msg):
	print msg
	msgquote= '"{}"'.format(msg)
	subprocess.call('espeak -ves+f3 -a200 '+ msgquote, shell=True)

@app.route('/videos/<checktext>')
def playvideo(checktext):
	print checktext
	vname= '"{}"'.format(checktext)
	subprocess.call("sudo SDL_VIDEODRIVER=fbcon SDL_FBDEV=/dev/fb1 mplayer -vo sdl -framedrop -quiet -input file=/home/pi/fifos /home/pi/BiBli/media/video/"+ vname+" < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
	#subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/gif/bg2.jpg", shell=True)

@app.route('/videoctrl/<vdoctl>')
def video_ctrl(vdoctl):
	if vdoctl == 'pause':
		subprocess.call("echo 'pause' > /home/pi/fifos", shell = True)
	elif vdoctl == 'stp':
		subprocess.call("echo 'q' > /home/pi/fifos", shell = True)
		subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
		
@app.route('/sounds/<checktext>')
def playsound(checktext):
	print checktext
	sname= '"{}"'.format(checktext)
	subprocess.call("mplayer -input file=/home/pi/sound_fifo /home/pi/BiBli/media/audio/music/"+ sname+" < /dev/null &", shell=True)

@app.route('/soundctrl/<sndctl>')
def sound_ctrl(sndctl):
	if sndctl == 'pause':
		subprocess.call("echo 'pause' > /home/pi/sound_fifo", shell = True)
	elif sndctl == 'stp':
		subprocess.call("echo 'q' > /home/pi/sound_fifo", shell = True)

@app.route('/stories/<checktext>')
def playstory(checktext):
	print checktext
	sname= '"{}"'.format(checktext)
	subprocess.call("mplayer -input file=/home/pi/story_fifo /home/pi/BiBli/media/audio/story/"+ sname+" < /dev/null &", shell=True)

@app.route('/storytrl/<styctl>')
def story_ctrl(styctl):
	if styctl == 'pause':
		subprocess.call("echo 'pause' > /home/pi/story_fifo", shell = True)
	elif styctl == 'stp':
		subprocess.call("echo 'q' > /home/pi/story_fifo", shell = True)
	

@app.route('/PiIP')
def get_ip_address(ifname):
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    return socket.inet_ntoa(fcntl.ioctl(
        s.fileno(),
        0x8915,  # SIOCGIFADDR
        struct.pack('256s', ifname[:15])
    )[20:24])
pi_ip=get_ip_address('wlan0')
psip= "'{}'".format(pi_ip)

if __name__ == '__main__':
	app.run(host=pi_ip, threaded=True)

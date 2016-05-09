# -*- coding: utf-8-*-
import subprocess
import random

class Play_vdo:

        def play(self):
                print 'play video'
                n = random.randint(1,7)
                if (n==1): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound -input file=/home/pi/fifos /home/pi/videos/backkom1.mp4", shell = True)
                elif(n==2): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom2.mp4", shell = True)
                elif(n==3): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom3.mp4", shell = True)
                elif(n==4): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom4.mp4", shell = True)
                elif(n==5): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom5.mp4", shell = True)
                elif(n==6): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom6.mp4", shell = True)
                elif(n==7): subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -nosound  -input file=/home/pi/fifos /home/pi/videos/backkom7.mp4", shell = True)

        def stop_play(self):
                subprocess.call("echo 'q' > /home/pi/fifos", shell = True)

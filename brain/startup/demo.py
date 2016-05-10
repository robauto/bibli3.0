import serial
import subprocess
import base
import time
import math

demo_do = base.MoveCtrl()

class DemoList:
    def demoAction(self):
        #### Play media file
        subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -input file=/home/pi/fifos /home/pi/BiBli/brain/demos/bibli_demo.mp4 < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/image/bg2.jpg &", shell=True)
        time.sleep(2)
        #### BiBli motion
        demo_do.move('b')
        time.sleep(2)
        demo_do.move('f')
        time.sleep(2)
        demo_do.move('l')
        time.sleep(1)
        demo_do.move('r')
        time.sleep(2)
        demo_do.move('l')
        time.sleep(1)
        demo_do.move('s')
        



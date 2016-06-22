import base 
import time
import subprocess

subprocess.call("mplayer -vo fbdev2:/dev/fb1 -x 240 -y 320 -framedrop -quiet -input file=/home/pi/fifos /home/pi/BiBli/brain/demos/RhettNewEdit.mp4 < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/image/bg2.jpg &", shell=True)

move1=base.MoveCtrl()
time.sleep(5)
move1.move('s')
time.sleep(1)
move1.move('d')
time.sleep(3)
print "yup done"
move1.move('s')
time.sleep(1)
move1.move('f')
print "yup second done"
time.sleep(3)
move1.move('s')
time.sleep(1)
move1.move('d')
time.sleep(.5)
move1.move('f')
time.sleep(2)
move1.move('b')
time.sleep(3)
move1.move('s')


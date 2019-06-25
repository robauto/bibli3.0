from subprocess import Popen
from time import sleep

p = Popen(['python', 'autopilot.py'], shell=False)

sleep(20)

p.terminate()

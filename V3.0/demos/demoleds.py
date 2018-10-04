##BDF##
#name: DemoLEDs
#description: An example demo file for LEDS on the Robotjoy Hat.
##BDF##

import time
import sys
sys.path.append('../brain/startup')
import bibli_control
import bibli_defaults as bd

def demoActions(bibli):

	colors = [bd.OFF,bd.OFF,bd.OFF,bd.BLUE,bd.YELLOW,bd.GREEN,bd.RED,bd.OFF,bd.OFF,bd.OFF]

	for i in range(0,len(colors)-2):
		for j in range(0,3):
			bibli.changeColor(colors[i+j],j)

		# Sleep for 0.5 seconds and repeat
		time.sleep(1)
##BDF##
#name: Demo1
#description: An example demo file for the RobotJoy Hat.
##BDF##

###########################################################
#
# File to create a python script for the robot joy hat.
# List of commands can include movement, changing the
# led colors, reading in analog sensors, and controlling 
# several digital input/output pins.
#
# To move bibli use,
#	bibli.moveBibli( direction )
#
# To change the led color use, 
#	bibli.changeColor( color, led_number )
#
# To setup the digital I/O pins use,
# 	bibli.digitalSetup( pin, mode )
#
# To read from a digital pin use,
# 	bibli.digitalRead( pin )
#
# To write to a digital pin use,
# 	bibli.digitalWrite( pin, value )
#
# To read from the analog input use,
# 	bibli.analogRead( pin )
#
# bibli_defaults.py file has some predefined motions
# and colors that can be called.
#	bibli.moveBibli( bd.FORWARD )
#	bibli.changeColor( bd.RED )
#
# To define your own motions, input an x and y value
# between -1 and 1
#	bibli.moveBibli( (-1,0) )
#
# To define your own colors, input a red, green, and blue
# value between 0 and 255. Also pick the led to be changed
#	bibli.changeColor( (255,0,0), 0 )
# You can also define multiple leds at a time with a list
# 	bibli.changeColor( bd.RED, [0,1] )
#
# To setup a digital pin do the following:
# 	for an Input: bibli.digitalSetup( 0, "IN" )
# 	for an Output: bibli.digitalSetup( 0, "OUT" )
#
# To write to a digital pin define either 0 or 1 for the value
# 	To turn on the pin: bibli.digitalWrite( 0, 1 )
# 	To turn off the pin: bibli.digitalWrite( 0, 0 )
#
# To have the demo pause for a certain amount of time use,
#	time.sleep( seconds )
# Input the number of seconds you want the program to wait
#
###########################################################

import time
import sys
sys.path.append('../brain/startup')
import bibli_control
import bibli_defaults as bd

def demoActions(bibli):
	# Define D0 as an input
	bibli.digitalSetup(0,"IN")
	# Define D4 as an output
	bibli.digitalSetup(4,"OUT")

	for i in range(0,100):
		# Read in the analog signal from A0 
		dist = bibli.analogRead(0)
		if( dist > 400 ):
			# Change the led colors to RED
			bibli.changeColor( bd.RED, [0,1,2] )
			# Stop the movement of the robot
			bibli.moveBibli( bd.STOP )
		else:
			# Change the led colors to GREEN
			bibli.changeColor( bd.GREEN, [0,1,2] )
			# Have bibli move forward
			bibli.moveBibli( bd.FORWARD )

		# Read in the value of the D0 pin
		button = bibli.digitalRead(0)
		if( button ):
			# Turn on D4
			bibli.digitalWrite(4,1)
		else:
			# Turn off D4
			bibli.digitalWrite(4,0)

		# Sleep for 0.5 seconds and repeat
		time.sleep(0.5)

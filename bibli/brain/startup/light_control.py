import RPi.GPIO as io
import threading
from db import get_kv, set_kv
from gevent import sleep

def read_leds(led):
	# try to database, but have a default as a backup
	rgb = get_kv(("LED%d" % led)) or "100,100,100"
	# return as an array of ints
	return [int(x) for x in rgb.split(",")]

def write_leds(rgb_values,led):
	rgb_values = list(map(str,rgb_values))
	set_kv( ("LED%d" % led), ",".join(rgb_values))

class light_control:
	## LEDx = [Red, Green, Blue]
	LED0 = [4,3,2]
	LED1 = [11,9,10]
	LED2 = [5,7,8]

	LEDS = LED0 + LED1 + LED2

	def __init__(self):
		for val in self.LEDS:
			io.setup(val,io.OUT)

		red0 = io.PWM(self.LED0[0],50)
		green0 = io.PWM(self.LED0[1],50)
		blue0 = io.PWM(self.LED0[2],50)
		
		red1 = io.PWM(self.LED1[0],50)
		green1 = io.PWM(self.LED1[1],50)
		blue1 = io.PWM(self.LED1[2],50)
		
		red2 = io.PWM(self.LED2[0],50)
		green2 = io.PWM(self.LED2[1],50)
		blue2 = io.PWM(self.LED2[2],50)

		# self.pwms0 = [red0,green0,blue0]
		# self.pwms1 = [red1,green1,blue1]
		# self.pwms2 = [red2,green2,blue2]
			
		# for pwms in self.pwms0:
		# 	pwms.start(100)
		# for pwms in self.pwms1:
		# 	pwms.start(100)
		# for pwms in self.pwms2:
		# 	pwms.start(100)

		self.pwms = [[red0,green0,blue0],[red1,green1,blue1],[red2,green2,blue2]]

		for i in range(0,3):
			for leds in self.pwms[i]:
				leds.start(100)
			
		self.thread = threading.Thread(target=self.set_leds)
		self.thread.daemon = True
		self.thread.start()

	def __del__(self):
		# for pwms in self.pwms0:
		# 	pwms.ChangeDutyCycle(100)
		# 	pwms.stop()
		# for pwms in self.pwms1:
		# 	pwms.ChangeDutyCycle(100)
		# 	pwms.stop()
		# for pwms in self.pwms2:
		# 	pwms.ChangeDutyCycle(100)
		# 	pwms.stop()
		for i in range(0,3):
			for leds in self.pwms[i]:
				leds.ChangeDutyCycle(100)
				leds.stop()
		self.thread.stop()

	def pwm_to_rgb(self,red,green,blue):
		rdc = (255-red)*100/255
		gdc = (255-green)*100/255
		bdc = (255-blue)*100/255
		return [rdc,gdc,bdc]

	def change_color(self,red,green,blue,led):
		try:
			for leds in led:
				write_leds( self.pwm_to_rgb(red,green,blue), leds )
		except TypeError:
			write_leds( self.pwm_to_rgb(red,green,blue), led )
		# for i in range(0,3):
			# self.rgb_pwms[i].ChangeDutyCycle(led_pwms[i])

	def set_leds(self):
		while(True):
			for i in range(0,3):
				led_pwms = read_leds(i)
				for j in range(0,3):
					self.pwms[i][j].ChangeDutyCycle(led_pwms[j])
					self.pwms[i][j].ChangeDutyCycle(led_pwms[j])
					self.pwms[i][j].ChangeDutyCycle(led_pwms[j])
			sleep(0.1)

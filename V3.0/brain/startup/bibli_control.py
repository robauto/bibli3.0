import RPi.GPIO as io
import motor_control
import light_control
import Adafruit_MCP3008
from audio_control import AudioControl

io.setmode(io.BCM)
io.setwarnings(False)

# Pins used for the MCP3008
CLK = 18
MISO = 23
MOSI = 24
CS = 25

# List of GPIO pins used for digitalIO
digitalIO = [19, 20, 16, 13, 12, 6, 26, 21]

# Class for overall Bibli Control including:
#  motor control
#  LED control
#  Sensor readings


class BiBli:
    def __init__(self):
        self.motor = motor_control.motor_control()
        self.lights = light_control.light_control()
        self.audio = AudioControl()
        self.adc = Adafruit_MCP3008.MCP3008(
            clk=CLK, cs=CS, miso=MISO, mosi=MOSI)

    def moveBibli(self, dc):
        self.motor.motor_move(dc)
        # self.motor.joystick_to_duty(cords[0],cords[1])

    def autoBibli(self, status):
        self.motor.autopilot(status)

    def changeColor(self, rgb, led):
        self.lights.change_color(rgb[0], rgb[1], rgb[2], led)

    def analogRead(self, channel):
        return self.adc.read_adc(channel)

    def digitalSetup(self, channel, mode):
        if(mode == "OUT"):
            io.setup(digitalIO[channel], io.OUT)
        elif(mode == "IN"):
            io.setup(digitalIO[channel], io.IN)

    def digitalWrite(self, channel, level):
        io.output(digitalIO[channel], level)

    # Will add pwm interface as a next step

    def digitalRead(self, channel):
        return io.input(digitalIO[channel])

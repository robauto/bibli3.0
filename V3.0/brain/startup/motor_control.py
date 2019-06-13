import RPi.GPIO as io
import time
import math
import threading
from db import get_kv, set_kv
from threading import Timer
from hcsr04sensor import sensor
from repeatedTimer import RepeatedTimer


def read_motors():
    # try to database, but have a default as a backup
    mv = get_kv("motor") or "0,0"
    # return as an array of ints
    return [int(x) for x in mv.split(",")]


def write_motors(pwm_values):
    motor_values = list(map(str, pwm_values))
    set_kv("motor", ",".join(motor_values))

# The class that will handle the motor control for BiBli


class motor_control:
    # Initialize which pins the motors are connected to
    #r_pin1 = 22
    #r_pin2 = 27

    #l_pin2 = 15
    #l_pin1 = 14

    r_pin1 = 19
    r_pin2 = 20

    l_pin2 = 16
    l_pin1 = 13

    #enable = 17
    # Group the pins for the right motor together
    r_motor = [r_pin1, r_pin2]

    # Group the pins for the left motor together
    l_motor = [l_pin1, l_pin2]

    # servo motor
    servo_pin = 26
    TRIGGER_PIN = 12
    ECHO_PIN = 6

    # read distance
    distance = 40
    # read_dis = None
    # selfdrive = None
    # servo = None

    def __init__(self):
        # Disable warnings
        io.setwarnings(False)
        self.read_dis = None
        self.selfdrive = None
        #self.read_dis = RepeatedTimer(0.8, self.get_dis)
        #self.selfdrive = RepeatedTimer(1, self.selfmove)

        # Define all pins as outputs for each motor
        for pins in self.r_motor:
            io.setup(pins, io.OUT)

        for pins in self.l_motor:
            io.setup(pins, io.OUT)

        io.setup(self.servo_pin, io.OUT)
        self.servo = io.PWM(self.servo_pin, 50)
        self.servo.start(7)

        # set GPIO direction (IN / OUT)
        io.setup(self.TRIGGER_PIN, io.OUT)
        io.setup(self.ECHO_PIN, io.IN)

        self.timerflag = False
        self.blocked = True
        self.current_dir = 7
        #io.setup(self.enable, io.OUT)

        # To control the speed of the motors, need to
        # create PWM outputs for those pins
        #r_pwm1 = io.PWM(self.r_pin1, 20)
        #r_pwm2 = io.PWM(self.r_pin2, 20)
        #l_pwm1 = io.PWM(self.l_pin1, 20)
        #l_pwm2 = io.PWM(self.l_pin2, 20)

        #self.motor_pwms = [r_pwm1, r_pwm2, l_pwm1, l_pwm2]

        # Initialize the pins in a zero state
        # for pwms in self.motor_pwms:
        #    pwms.start(0)

        # Enable each motor to be able to move
        #io.output(self.enable, True)

    # function to read the distance
    def get_dis(self):
        '''Calculate the distance of an object in centimeters using a HCSR04 sensor
           and a Raspberry Pi'''

        #trig_pin = 17
        #echo_pin = 27
        # Default values
        # unit = 'metric'
        # temperature = 20
        # round_to = 1
        #  Create a distance reading with the hcsr04 sensor module
        value = sensor.Measurement(self.TRIGGER_PIN, self.ECHO_PIN)
        raw_measurement = value.raw_distance()

        # Calculate the distance in centimeters
        metric_distance = value.distance_metric(raw_measurement)
        self.distance = metric_distance

    # function to  Autopilot
    def selfmove(self):
        if self.distance < 30:
            self.blocked = True
        else:
            self.blocked = False

        if self.blocked:
            if self.explore('left'):
                self.route('left')
            elif self.explore('right'):
                self.route('right')
            else:
                self.route('uturn')
        else:
            self.motor_move(1)

    def explore(self, direction):
        self.motor_move(0)
        if direction is 'left':
            self.current_dir = 4.5
            self.servo.ChangeDutyCycle(self.current_dir)
            time.sleep(2)
            if self.distance < 30:
                self.blocked = True
                return False
            else:
                self.blocked = False
                return True
        elif direction is 'right':
            self.current_dir = 9.5
            self.servo.ChangeDutyCycle(self.current_dir)
            time.sleep(2)
            if self.distance < 30:
                self.blocked = True
                return False
            else:
                self.blocked = False
                return True
        return True

    def route(self, direction):
        self.motor_move(0)
        if direction is 'left':
            self.motor_move(4)
            time.sleep(0.8)
            self.motor_move(1)
        elif direction is 'right':
            self.motor_move(3)
            time.sleep(0.8)
            self.motor_move(1)
        elif direction is 'uturn':
            self.motor_move(3)
            time.sleep(1.2)
            self.motor_move(1)
        if self.current_dir != 7:
            self.current_dir = 7
            self.servo.ChangeDutyCycle(self.current_dir)
        time.sleep(0.5)

    def autopilot(self, status):
        # print(status)
        if status == 1:
            if self.timerflag is False:
                self.read_dis = RepeatedTimer(0.8, self.get_dis)
                self.selfdrive = RepeatedTimer(1, self.selfmove)
                self.timerflag = True
        if status == 0:
            if self.timerflag:
                self.read_dis.stop()
                self.selfdrive.stop()
                self.timerflag = False

    # Function to change the values of the motors
    #  Inputs - which motor to move and the value to set

    def motor_move(self, dc):
        if dc == 1:  # froward
            io.output(self.r_motor[0], 1)
            io.output(self.r_motor[1], 0)
            io.output(self.l_motor[0], 1)
            io.output(self.l_motor[1], 0)
        elif dc == 2:  # backward
            io.output(self.r_motor[0], 0)
            io.output(self.r_motor[1], 1)
            io.output(self.l_motor[0], 0)
            io.output(self.l_motor[1], 1)
        elif dc == 3:  # right
            io.output(self.r_motor[0], 1)
            io.output(self.r_motor[1], 0)
            io.output(self.l_motor[0], 0)
            io.output(self.l_motor[1], 1)
        elif dc == 4:  # left
            io.output(self.r_motor[0], 0)
            io.output(self.r_motor[1], 1)
            io.output(self.l_motor[0], 1)
            io.output(self.l_motor[1], 0)
        elif dc == 0:  # stop
            io.output(self.r_motor[0], 0)
            io.output(self.r_motor[1], 0)
            io.output(self.l_motor[0], 0)
            io.output(self.l_motor[1], 0)

    def motor_power(self, motor, value):
        if(motor == 'r'):
            if(value < 0):
                self.motor_pwms[0].ChangeDutyCycle(0)
                self.motor_pwms[1].ChangeDutyCycle(50)
                # self.motor_pwms[1].ChangeDutyCycle(abs(value))
            elif(value > 0):
                # self.motor_pwms[0].ChangeDutyCycle(value)
                self.motor_pwms[0].ChangeDutyCycle(50)
                self.motor_pwms[1].ChangeDutyCycle(0)
            else:
                self.motor_pwms[0].ChangeDutyCycle(0)
                self.motor_pwms[1].ChangeDutyCycle(0)
        elif(motor == 'l'):
            if(value < 0):
                self.motor_pwms[2].ChangeDutyCycle(0)
                self.motor_pwms[3].ChangeDutyCycle(50)
            elif(value > 0):
                self.motor_pwms[2].ChangeDutyCycle(50)
                self.motor_pwms[3].ChangeDutyCycle(0)
            else:
                self.motor_pwms[2].ChangeDutyCycle(0)
                self.motor_pwms[3].ChangeDutyCycle(0)
        else:
            for motors in self.motor_pwms:
                motors.ChangeDutyCycle(0)

    # Function to convert joystick coordinates into
    #  motor duty cycles
    #  Inputs - x and y joystick coordinates
    def joystick_to_duty(self, x, y):
        # Determine the polar coordinates of the joystick
        magnitude = (x**2 + y**2)**0.5
        # If the magnitude happens to be greater than 1
        # normalize the x and y values to fit
        if magnitude > 1:
            x = x / magnitude
            y = y / magnitude
            magnitude = (x**2 + y**2)**0.5

        theta = math.atan2(y, x)

        # Based on the value of theta, calculate the motor
        # values based on which quadrant x,y are in
        ## 0 <= theta < pi/2
        if(theta >= 0 and theta < (math.pi / 2)):
            r = 100 * magnitude * \
                (((4 / math.pi) * (theta - (math.pi / 2))) + 1)
            l = 100 * magnitude
        ## pi/2 <= theta <= pi
        elif(theta >= (math.pi / 2) and theta <= (math.pi)):
            r = 100 * magnitude
            l = -100 * magnitude * (((4 / math.pi) * (theta - math.pi)) + 1)
        ## -pi/2 <= theta < 0
        elif(theta >= (-math.pi / 2) and theta < 0):
            r = -100 * magnitude
            l = 100 * magnitude * (((4 / math.pi) * theta) + 1)
        ## -pi <= theta < -pi/2
        elif(theta >= (-math.pi) and theta < (-math.pi / 2)):
            r = -100 * magnitude * \
                (((4 / math.pi) * (theta + (math.pi / 2))) + 1)
            l = -100 * magnitude
        else:
            r = 0
            l = 0

        # Round the outputs and conver to integers
        r = int(round(r, 0))
        l = int(round(l, 0))

        print("Right motor: ", r)
        print("Left motor: ", l)

        # Call the functions to change the duty cycle of the motors
        self.motor_power('r', r)
        self.motor_power('l', l)

    def __del__(self):
        # Stop all PWM outputs for the motors
        for motors in self.motor_pwms:
            motors.stop()

        # Disable both of the motors
        io.output(self.enable, False)

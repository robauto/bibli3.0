import RPi.GPIO as io
from . import CONFIG

left_motor_pins = CONFIG.pins.motors.left
right_motor_pins = CONFIG.pins.motors.right

# Setup the motor pins as output
for pin in left_motor_pins:
    io.setup(pin, io.OUT)

for pin in right_motor_pins:
    io.setup(pin, io.OUT)


def set_motor_speeds(left_speed, right_speed):
    """
    Sets the speed of the each motor

    :param left_speed: The desired speed of the left motor, from -1 to 1
    :param right_speed: The desired speed of the right motor, from -1 to 1
    :return: None
    """

    if left_speed >= 0:
        io.output(left_motor_pins[0], left_speed)
        io.output(left_motor_pins[1], 0)
    else:
        io.output(left_motor_pins[0], 0)
        io.output(left_motor_pins[1], -left_speed)

    if right_speed >= 0:
        io.output(right_motor_pins[0], right_speed)
        io.output(right_motor_pins[1], 0)
    else:
        io.output(right_motor_pins[0], 0)
        io.output(right_motor_pins[1], -right_speed)


def stop_motors():
    """Sets the speed of both motors to 0"""
    set_motor_speeds(0, 0)

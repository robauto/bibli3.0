import RPi.GPIO as io
from config import CONFIG

left_motor_pins = CONFIG["pins"]["motors"]["left"]
right_motor_pins = CONFIG["pins"]["motors"]["right"]

# Object to hold the GPIO's pulse width modulation objects
pwms = {
    "right": [],
    "left": []
}

# Setup the motor pins as output and begin pulse width modulation
for pin in right_motor_pins:

    io.setup(pin, io.OUT)  # Set the pin as output

    pwm = io.PWM(pin, 50)  # Create a pulse width modulation object with a frequency of 50 hertz
    pwm.start(0)  # Start the pwm as completely off

    pwms["right"].append(pwm)

for pin in left_motor_pins:
    io.setup(pin, io.OUT)  # Set the pin as output

    pwm = io.PWM(pin, 50)  # Create a pulse width modulation object with a frequency of 50 hertz
    pwm.start(0)  # Start the pwm as completely off

    pwms["left"].append(pwm)


def set_left_motor_speed(speed):
    """
    Sets the speed of the left motor with pwm

    :param speed: The desired speed of the left motor, from -100 to 100
    :return: None
    """
    print(left_speed, right_speed)

    if speed >= 0:
        pwms["left"][0].ChangeDutyCycle(speed)
        pwms["left"][1].ChangeDutyCycle(0)
    else:
        pwms["left"][0].ChangeDutyCycle(0)
        pwms["left"][1].ChangeDutyCycle(-speed)


def set_right_motor_speed(speed):
    """
    Sets the speed of the right motor with pwm

    :param speed: The desired speed of the right motor, from -100 to 100
    :return: None
    """

    if speed >= 0:
        pwms["right"][0].ChangeDutyCycle(speed)
        pwms["right"][1].ChangeDutyCycle(0)
    else:
        pwms["right"][0].ChangeDutyCycle(0)
        pwms["right"][1].ChangeDutyCycle(-speed)


def set_motor_speeds(left_speed, right_speed):
    """
    Sets the speed of the motor with pwm

    :param left_speed: The desired speed of the left motor, from -100 to 100
    :param right_speed: The desired speed of the right motor, from -100 to 100
    :return: None
    """

    set_left_motor_speed(left_speed)
    set_right_motor_speed(right_speed)


def stop_motors():
    """Sets the speed of both motors to 0"""
    set_motor_speeds(0, 0)

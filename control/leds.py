import RPi.GPIO as io
from config import CONFIG

led_pins = CONFIG.pins.leds

pwms = []  # List to hold the GPIO's pulse width modulation objects

# Setup the led pins as output and begin pulse width modulation
# There are 3 leds
for led in led_pins:
    pwms.append({})  # Add a new dictionary to hold the pwm objects for this led

    # Each led has 3 pins
    for pin_name in led.keys():
        pin = led[pin_name]  # Get the pin number

        io.setup(pin, io.OUT)  # Set the pin as output

        pwm = io.PWM(pin, 50)  # Create a pulse width modulation object with a frequency of 50 hertz
        pwm.start(100)  # Start the pwm as on 100% of the time


def rgb_to_pwm(r, g, b):
    """
    Converts a color from rgb notation to 3 values from 0 to 100 which will cause the led to show the desired color
    when used as pwm duty cycles

    :param r: The red component of the color, from 0-255
    :param g: The blue component of the color, from 0-255
    :param b: The green component of the color, from 0-255
    :return: A tuple containing the three pwm values from 0-100
    """

    pwm_values = []

    for value in (r, g, b):
        pwm_values.append((255 - value) * 100 / 255)

    return pwm_values


def set_led_color(led_number, color):
    """
    Changes the color of one or more of the bilbi's leds

    :param led_number: The number (from 0 to 2) of the led to change, or a list/tuple of led numbers to change
    :param color: A list/tuple of three integers from 0-255 representing the desired color in rgb notation
    :return: None
    """

    # Convert the rgb values into 0-100 values needed by the pwm objects
    pwm_values = rgb_to_pwm(color[0], color[1], color[2])

    if isinstance(led_number, int):
        # Change only one led
        pwms[led_number]["r"].ChangeDutyCycle(pwm_values[0])
        pwms[led_number]["g"].ChangeDutyCycle(pwm_values[1])
        pwms[led_number]["b"].ChangeDutyCycle(pwm_values[2])

    elif isinstance(led_number, list) or isinstance(led_number, tuple):
        # Change more than one led
        for i in led_number:
            pwms[i]["r"].ChangeDutyCycle(pwm_values[0])
            pwms[i]["g"].ChangeDutyCycle(pwm_values[1])
            pwms[i]["b"].ChangeDutyCycle(pwm_values[2])

    else:
        raise TypeError("First argument should be type int, list, or tuple, not " + str(type(led_number)))


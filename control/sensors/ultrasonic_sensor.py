import RPi.GPIO as io
import hcsr04sensor
from config import CONFIG

trigger_pin = CONFIG.pins.sensors.ultrasonic.trigger
echo_pin = CONFIG.pins.sensors.ultrasonic.echo

io.setup(trigger_pin, io.OUT)
io.setup(echo_pin, io.IN)


def get_distance():
    """Return the distance from the ultrasonic sensor to the nearest object"""

    value = hcsr04sensor.sensor.Measurement(trigger_pin, echo_pin)
    raw_measurement = value.raw_distance()

    return raw_measurement

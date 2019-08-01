from config import CONFIG

available_sensors = CONFIG["sensors"]

if "ultrasonic" in available_sensors:
    import ultrasonic_sensor


def get_all_sensor_readings():
    """Return a dictionary with the readings of all available sensors"""

    readings = {}
    if "ultrasonic" in available_sensors:
        readings["ultrasonic"] = ultrasonic_sensor.get_distance()

    return readings

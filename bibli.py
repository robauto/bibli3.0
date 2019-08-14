#!/usr/bin/python3

from time import sleep
import json
import aws
import control.sensors

aws.set_shadow_update_callback(aws.update_bibli_callback)

while True:
    aws.update_shadow(
        '{"sensor": ' + json.dumps(control.sensors.get_all_sensor_readings()) + '}'
    )
    sleep(0.2)

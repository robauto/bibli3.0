from time import sleep
import aws
import control.sensors

aws.set_shadow_update_callback(aws.update_bibli_callback)

while True:
    aws.update_shadow(
        '{"sensor": ' + str(control.sensors.get_all_sensor_readings()) + '}}'
    )
    sleep(0.2)

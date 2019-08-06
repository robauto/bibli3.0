from AWSIoTPythonSDK.MQTTLib import AWSIoTMQTTShadowClient
import json
from ..control import motors, leds

CONFIG = json.loads(open("./aws_credentials/aws_config.json", "r").read())

HOST_NAME = CONFIG["account_endpoint"] + ".iot.us-west-2.amazonaws.com"
ROOT_CA = "./aws_credentials/Amazon_Root_CA_1.pem"
PRIVATE_KEY = "./aws_credentials/private.pem.key"
CERT_FILE = "./aws_credentials/certificate.pem.crt"
SHADOW_NAME = CONFIG["thing_name"]
CLIENT_NAME = SHADOW_NAME

shadow_client = AWSIoTMQTTShadowClient(CLIENT_NAME)
shadow_client.configureEndpoint(HOST_NAME, 8883)
shadow_client.configureCredentials(ROOT_CA, PRIVATE_KEY, CERT_FILE)
shadow_client.configureConnectDisconnectTimeout(10)
shadow_client.configureMQTTOperationTimeout(5)
shadow_client.connect()

device_shadow = shadow_client.createShadowHandlerWithName(SHADOW_NAME, True)


def update_shadow(json_data):
    """Update the device's shadow on AWS with the specified data"""
    device_shadow.shadowUpdate('{"state":{"reported":' + json_data + '}}', None, 5)


def update_bibli_callback(payload, responseStatus, token):
    """
    Set the motors and leds based on a MQTT message from AWS and update the shadow to reflect the change

    :param payload: The payload object given to callbacks by AWS
    :return: None
    """

    desired_state = json.loads(payload)["state"]["output"]
    keys = desired_state.keys()
    if "led0" in keys:
        leds.set_led_color(0, desired_state["led0"])
    if "led1" in keys:
        leds.set_led_color(1, desired_state["led1"])
    if "led2" in keys:
        leds.set_led_color(2, desired_state["led2"])
    if "motors" in keys:
        motors.set_motor_speeds(
            desired_state["motors"]["left"],
            desired_state["motors"]["right"]
        )

    update_shadow('{"output":' + json.dumps(desired_state) + '}')


def set_shadow_update_callback(callback):
    """Call the specified function whenever the shadow's desired state differs from its reported state"""
    device_shadow.shadowRegisterDeltaCallback(callback)


if __name__ == '__main__':
    from ..control.sensors import ultrasonic_sensor
    from time import sleep

    set_shadow_update_callback(update_bibli_callback)

    while True:
        distance = ultrasonic_sensor.get_distance()

        update_shadow('{"sensor":{"ultrasonic":' + str(distance) + '}}')
        sleep(0.2)

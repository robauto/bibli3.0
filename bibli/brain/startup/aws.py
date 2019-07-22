from AWSIoTPythonSDK.MQTTLib import AWSIoTMQTTShadowClient
import json
import bibli_control

CONFIG = json.loads(open("./aws_credentials/config.json", "r").read())

HOST_NAME = "amtw5tpjwpfq2-ats.iot.us-west-2.amazonaws.com"
ROOT_CA = "./aws_credentials/Amazon_Root_CA_1.pem"
PRIVATE_KEY = "./aws_credentials/private.pem.key"
CERT_FILE = "./aws_credentials/certificate.pem.crt"
SHADOW_NAME = CONFIG["thing_name"]
CLIENT_NAME = CONFIG["thing_name"]

shadow_client = AWSIoTMQTTShadowClient(CLIENT_NAME)
shadow_client.configureEndpoint(HOST_NAME, 8883)
shadow_client.configureCredentials(ROOT_CA, PRIVATE_KEY, CERT_FILE)
shadow_client.configureConnectDisconnectTimeout(10)
shadow_client.configureMQTTOperationTimeout(5)
shadow_client.connect()

device_shadow = shadow_client.createShadowHandlerWithName(SHADOW_NAME, True)

bibli = bibli_control.BiBli()


def update_shadow(json):
    """Updates the device's shadow on AWS with the specified data"""
    device_shadow.shadowUpdate('{"state":{"reported":' + json + '}}', None, 5)


def delta_callback(payload, responseStatus, token):
    desired_state = json.loads(payload)["state"]["output"]
    keys = desired_state.keys()
    if "led0" in keys:
        bibli.changeColor(desired_state["led0"], 0)
    if "led1" in keys:
        bibli.changeColor(desired_state["led1"], 1)
    if "led2" in keys:
        bibli.changeColor(desired_state["led2"], 2)
    if "moveDirection" in keys:
        direction = desired_state["moveDirection"]
        dc = {
            "stop": 0,
            "forwards": 1,
            "backwards": 2,
            "right": 3,
            "left": 4
        }[direction]
        bibli.moveBibli(dc)

    update_shadow('{"output":' + json.dumps(desired_state) + '}')


def set_shadow_update_callback(callback):
    device_shadow.shadowRegisterDeltaCallback(callback)


set_shadow_update_callback(delta_callback)

if __name__ == '__main__':
    from gpiozero import DistanceSensor
    from time import sleep

    sensor = DistanceSensor(echo=6, trigger=12)

    while True:
        update_shadow('{"sensor":{"ultrasonic":' + str(sensor.distance * 100) + '}}')
        sleep(0.2)

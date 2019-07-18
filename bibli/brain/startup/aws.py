from AWSIoTPythonSDK.MQTTLib import AWSIoTMQTTShadowClient
from json import loads
from config import ULTRASONIC_TRIGGER_PIN, ULTRASONIC_ECHO_PIN

CONFIG = loads(open("./aws_credentials/config.json", "r").read())

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


def update_shadow(json):
    """Updates the device's shadow on AWS with the specified data"""
    device_shadow.shadowUpdate('{"state":{"reported":' + json + '}}', None, 5)


if __name__ == '__main__':
    from gpiozero import DistanceSensor
    from time import sleep

    sensor = DistanceSensor(echo=ULTRASONIC_ECHO_PIN, trigger=ULTRASONIC_TRIGGER_PIN)

    while True:
        update_shadow('{"ultrasonic":' + str(sensor.distance * 100) + '}')
        sleep(0.2)

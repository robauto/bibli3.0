# -*- coding: utf-8-*-
import time
import re
import serial

WORDS = ["DANCE"]

def handle(text, mic, profile):
    ser = serial.Serial('/dev/ttyUSB0', 9600, timeout=1)
    ser.open()
    ser.write('d')
    time.sleep(3)
    ser.write('s')

def isValid(text):
    return bool(re.search(r'\bdance\b', text, re.IGNORECASE))

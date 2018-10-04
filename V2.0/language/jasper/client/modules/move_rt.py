# -*- coding: utf-8-*-
import time
import re
import serial

WORDS = ["RIGHT"]

def handle(text, mic, profile):
    ser = serial.Serial('/dev/ttyUSB0', 9600, timeout=1)
    ser.open()
    ser.write('r')
    time.sleep(0.5)
    ser.write('s')

def isValid(text):
    return bool(re.search(r'\bright\b', text, re.IGNORECASE))

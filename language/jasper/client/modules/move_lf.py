# -*- coding: utf-8-*-
import time
import re
import serial

WORDS = ["LEFT"]

def handle(text, mic, profile):
    ser = serial.Serial('/dev/ttyUSB0', 9600, timeout=1)
    ser.open()
    ser.write('l')
    time.sleep(0.5)
    ser.write('s')

def isValid(text):
    return bool(re.search(r'\bleft\b', text, re.IGNORECASE))

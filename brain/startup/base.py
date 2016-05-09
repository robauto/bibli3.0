import serial
import time
import math

ser = serial.Serial('/dev/ttyUSB0', 9600, timeout=1)
ser.open()

class MoveCtrl:
    #def __init__(self, direction):
        #direction == 'stop'
        
    def move(self, direction):
        if ser.isOpen():
            ser.write(direction)
            print direction
        else:
            time.sleep(0.1)

    

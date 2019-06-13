from gpiozero import DistanceSensor, Robot
from time import sleep
from signal import pause
import random

ultrasonic = DistanceSensor(echo=6, trigger=12, max_distance=1, threshold_distance=0.6)

robot = Robot(left=(13, 16), right=(19, 20))


def forward():
    robot.stop()
    sleep(1)

    if random.choice([True, False]):  # Randomly choose either left or right
        robot.left()
    else:
        robot.right()
    sleep(0.2)
    robot.stop()
    sleep(1)
    robot.forward()


def backward():
    robot.stop()
    sleep(0.1)
    robot.backward()


ultrasonic.when_in_range = backward
ultrasonic.when_out_of_range = forward
pause()

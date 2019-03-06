from gpiozero import DistanceSensor, Robot
from time import sleep
from signal import pause

ultrasonic = DistanceSensor(echo=6, trigger=12, max_distance=1, threshold_distance=0.6)

robot = Robot(left=(13, 16), right=(19, 20))

ct=0
def bd():
    global ct
    ct+=1
    robot.stop()
    sleep(2)
    if ct%4==0:
        robot.left()
    else:
        robot.right()
    sleep(0.200)
    robot.backward()


def fd():
    robot.stop()
    sleep(2)
    robot.forward()

ultrasonic.when_in_range = bd
ultrasonic.when_out_of_range = fd
pause()
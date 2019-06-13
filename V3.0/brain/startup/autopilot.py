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

# count=0
# while True:
#     global count
#     ds1=ultrasonic.distance*100
#     sleep(0.01)
#     ds2=ultrasonic.distance*100
#     sleep(0.01)
#     ds3=ultrasonic.distance*100
#     sleep(0.01)
#     ds=max(ds1,ds2,ds3)
#     #print(ds)
#     if ds<50:
#         count+=1
#         robot.stop()
#         sleep(1)
#         if count%4 == 0:
#             robot.left()
#         else:
#             robot.right()
#             if count>999999:
#                 count=0
#         sleep(0.3)
#         robot.stop()
#         sleep(1)
#     else:
#         robot.forward()
    
        
    

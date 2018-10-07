#ROBAUTO Robotic Operating System
Version 3.0

To run a new instance flash an image to your SD disk
Compatible with Raspberry Pi 2.0 - 3.0

Primary Directories:

/apps
/brain
  /startup/ # (Web Server)
/data
/language
  /jasper #Voice control
/media  #This is where all media is stored
  /audio
  /img
  /video
/navigation
  /navigation/motor #Arduino code, sensors and motors
  
#Core Configuration Areas

/brain
  /startup/
  
#app.py
------
Main Flask application/server [2] & [3]
- creates and monitors camera object
- manages commands to move camera or motors
- runs threaded so both camera and http requests from buttons can be actioned [4]

#camera_pi.py
------------
Raspberry Pi camera module interface [2]

#motor.py
--------
Motor controller class

#servo.py
--------
Servo controller class 

#Requirements

Flask - sudo pip install flask
RPIO - sudo pip install rpio 

#(C) ROBAUTO, INC. 2016-2018
P.O. Box 4688
Boulder, Co 80306
www.ROBAUTO.co
support@robauto.co

#Contributors

Wei Miao

Qi Liu

Jalali Hartman

Christian Kaan

Rhett Sandal

Pi Chander

Andrew Shearer

The Weadleys

Matthew Curry

Hamzh Albar

Robert Jaffe

James Gardner




#Sources
[1] http://blog.miguelgrinberg.com/post/video-streaming-with-flask

[2] https://github.com/miguelgrinberg/flask-video-streaming

[3] http://arusahni.net/blog/2013/10/flask-multithreading.html

[4] http://makezine.com/projects/raspberry-eye-remote-servo-cam/
=======



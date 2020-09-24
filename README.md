BiBli Robotics Operating System for Raspberry Pi
------------------------------------------
==============================
Version 3.0 (C) Robauto, Inc. 

To run a new instance flash an image to your SD disk
Compatible with Raspberry Pi 2.0 - 3.0

#To Access
---------------
ssh pi@ip.address
pw: raspberry
cd /var/local/bibli

#Software Notes: 
------------------

https://github.com/robauto/bibli3.0/blob/master/V3.0/wiring_doc/readme.md

#Compatible Hardware:
--------------

https://docs.google.com/presentation/d/169s771b2DmyN1UYNMk2nO0gniecQ5qyNonMzaQuLid8/edit#slide=id.p1

#Current Image (10/2018):
-------------------------

https://drive.google.com/file/d/1krSru0f-JyPPPCto6M7XDUL-3al_ClVJ/view?usp=sharing

#Primary Directories:
--------------------------

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
--------------------------

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

#Contributors
--------

Jalali Hartman

Wei Miao

Qi Liu

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
-----------------
[1] http://blog.miguelgrinberg.com/post/video-streaming-with-flask

[2] https://github.com/miguelgrinberg/flask-video-streaming

[3] http://arusahni.net/blog/2013/10/flask-multithreading.html

[4] http://makezine.com/projects/raspberry-eye-remote-servo-cam/

#Contact
------------------

#(C) ROBAUTO, INC. 2016-2020
251 Main Street
Longmont, CO 80501
www.robauto.ai
support@robauto.ai

# bibli
# bibli

BiBli Robotic Operating System
Version 2.1

More documenation:

https://docs.google.com/presentation/d/169s771b2DmyN1UYNMk2nO0gniecQ5qyNonMzaQuLid8/edit?usp=sharing

ROBAUTO, INC. 2016
P.O. Box 4688
Boulder, Co 80306
www.ROBAUTO.co
support@robauto.co

Wei Miao
Qi Liu
Jalali Hartman
James Gardner
Rhett Sandal
Andrew Shearer
Christian Kaan
Matt Curry

1. Set-up a wirless network (via hotspot or unused modem) with:

ssid: bibli
user: bibli
pw: 12345678

2. Attach motors to left/right posts on brain

3. Attach 5v battery harness to power on brain shield (note: don't leave this plugged in/pull out a battery when not in use)

4. If you want assemble and attach the wheels and body. It's basically the base first and then add the 2 circles in order. Here is a full document on the body assembly. 

5. Power up pi via micro-usb cable and battery.

6. Go to any browser  (make sure your computer is also connected to wifi: bibli)
*Note you can edit the default ssid of the Pi to run on your home network if you need to. The command panel should load at http://alpha.local:5000\

Hardware Kit: See http://www.robauto.co/bibli.html

7. You can configure the robot ID (above, default is alpha), configure bluetooth and control the robot via game controller or robot

8. If you want to access via ssh it's just going to be 

ssh pi@ip.address 
pw: 1234 (or raspberry)

9. All of the code including jasper is in /var/local/bibli (note: jasper is not currently showing in the UI because it's not working correctly but you can test out the text-to-voice etc)


To run a new instance flash an image to your SD disk
Compatible with Raspberry Pi 2.0 - 3.0

Primary Directories:

/apps
/brain
  /startup/ #BiBli Web Server
/data
/language
  /jasper
/media  #This is where all media is stored
  /audio
  /img
  /video
/navigation
  /navigation/motor #Arduino code, sensors and motors



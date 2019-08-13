# Bibli3.0

This is the codebase for the Bibli robots manufactured by Robauto Inc.

# Installation
To install the Bibli software on a new Raspberry pi, follow these instructions.

### You Will Need:
1. A Raspberry Pi
2. The Bibli Robot
3. A microSD card with at least 8GB of storage. 
4. A computer that can read the microSD (most computers will need an adapter)

### Flashing the microSD
To begin, download this image from google drive.
Then you will need to flash the image onto your SD card.
There are several tools for this. I recommend [Etcher](https://www.balena.io/etcher/) for its simplicity.
It might take a few minutes to flash the microSD.
Once it finishes, eject the SD card, insert it into the Raspberry Pi, and power on the Pi.

### Connecting to the Bibli
Give the Pi a minute to power on. The Pi will automatically connect to the Bibli wifi if available.
To find the pi's IP address, you will need to scan the network.
On can be achieved with the ```nmap``` command.
On Windows, you may have to download a program to do this.
Once you have the IP address, you can connect to it with an ssh client.
Luckily, both Linux and Windows have one built in. Run ```ssh pi@<IP Address>```,
replacing ```<IP Address>``` with the IP of the Pi. You will be prompted for the password,
which is 'raspberry' by default.

### Setup
There are a few things you should do when you first connect to the pi.
Run  
 ```sudo raspi-config --expand-rootfs```  
 and restart the pi. You will have to reconnect after it has restarted.
 The Bibli files are located at ````/opt/Robauto/bibli3.0````. To access them, run  
 ```cd /opt/Robauto/bibli3.0```  
 To update to the latest version of the code, run  ```git pull```.
 You will need to enter your github credentials.
 
### AWS Setup
You will need access to Robuato's AWS account for this step.
1. Go to the [things page](https://us-west-2.console.aws.amazon.com/iot/home?region=us-west-2#/thinghub)
and click 'Create'.
2. Chose 'Create a single thing'.
3. Give the robot a name.
4. Select type 'Bibli'.
5. Under 'Set searchable attributes', the Hardware attribute should be set to 'Pi'.
6. Under 'Set non-searchable thing attributes', set the 'id' attribute to a unique positive integer.
7. Click 'Create certificate'.
8. Download the three files AWS generates, then click 'Activate'.
9. Click on 'Attach a policy'. Select the 'Bibli' policy and click 'Register thing'.

The thing is now ready!

Use Filezilla or another FTP client to transefer the three files you downloaded onto the Bibli in the folder ```/opt/Robauto/bibli3.0/aws/aws_credentials```.
The private key file MUST be named 'private.pem.key' and the certificate file MUST be named 'certificate.pem.crt'

You will also need to edit aws_config.json in the same folder. You can do this with
```nano /opt/Robauto/bibli3.0/aws/aws_credentials/aws_config.json```  
The file should look like this:
````
{
	"thing_name": "<Thing name>",
	"account_endpoint": "<The unique endpoint for your AWS account>"
}
``````
Replace `````<Thing Name>````` with the name you gave the AWS thing.  
Replace ```<The unique endpoint for your AWS account>``` with your AWS account endpoint. Right now, this is ```a2sg04dv4mdd8l```.

### You're Done!
You can run ```python3 /opt/Robauto/bibli3.0/bibli.py``` to start the code. You should be able  to access the robot over AWS.

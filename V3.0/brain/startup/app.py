#!/usr/bin/env python
from flask import Flask, render_template, Response, request, redirect, url_for, jsonify
import play_video
import subprocess
import os
import sys
import time
import socket
import urllib2
import calendar
import logging
import base64
import importlib
import py_compile
import io
from datetime import datetime

import netifaces
import json
import pygame
import redis
from PIL import Image

from db import set_kv, get_kv

sys.path.append(".")
from flask_sockets import Sockets

from gevent import sleep, monkey
monkey.patch_all()

r = redis.StrictRedis(host='localhost', port=6379, db=0)


def which(program):
    def is_exe(fpath):
        return os.path.isfile(fpath) and os.access(fpath, os.X_OK)
    fpath, fname = os.path.split(program)
    if fpath:
        if is_exe(program):
            return program
    else:
        for path in os.environ["PATH"].split(os.pathsep):
            path = path.strip('"')
            exe_file = os.path.join(path, program)
            if is_exe(exe_file):
                return exe_file
    return None


# metadata imports
from mutagen.mp3 import EasyMP3 as MP3
from mutagen.easymp4 import EasyMP4 as MP4
# from mutagen.mp4 import MP4

# Raspberry Pi camera module (requires picamera package)
try:
    # TODO: hardware check
    import picamera
    cam = picamera.PiCamera()
    cam.close()
    camera = False
except:
    camera = False

# Bibli control module
try:
    import bibli_control
    bibli = bibli_control.BiBli()
    bibli_con = True
except:
    bibli_con = False

# ESpeak module
try:
    from espeak import espeak
    speech = True
except:
    speech = False

# mplayer controls
try:
    # TODO: hardware check
    # look for mplayer exacutable
    if not which("mplayer"):
        raise Exception
    from mplayer import Player
    video = True
except:
    video = False

# demos module
sys.path.append("../..")
import demos

# bluetooth module
btc = None
try:
    from bluetoothctl import Bluetoothctl
    bluetooth = True
except:
    bluetooth = False


app = Flask(__name__)
app.config.update(
    DEBUG=True,
    JSON_AS_ASCII=False,
    JSONIFY_MIMETYPE="application/json; charset=utf-8"
)

stream_handler = logging.StreamHandler()
stream_handler.setLevel(logging.WARNING)
app.logger.addHandler(stream_handler)
sockets = Sockets(app)

dir_path = os.path.dirname(os.path.realpath(__file__))
IMAGE_DIR = "%s/static/img" % dir_path
dir_path = dir_path[:dir_path.index("brain/")]
MUSIC_DIR = "%smedia/audio/music" % dir_path
STORY_DIR = "%smedia/audio/story" % dir_path
VIDEO_DIR = "%smedia/video" % dir_path
DEMO_DIR = "%sdemos" % dir_path

# keep track of what media is currently playing
now_playing = None


def self_dict():
    name = my_name()
    fqdn = socket.getfqdn()
    if fqdn == "localhost":
        fqdn = "%s.local" % name
    d = {
        "ip": ip_address(),
        "name": name,
        "fqdn": fqdn,
        "last_seen": calendar.timegm(datetime.utcnow().timetuple()),
        "self": True
    }
    return d


def ip_address():
    for ifacename in netifaces.interfaces():
        iface = netifaces.ifaddresses(ifacename)
        if 2 not in iface or len(iface[2][0]) < 3 or iface[2][0]["addr"] == "127.0.0.1":
            continue
        return iface[2][0]["addr"]
    return None


def my_name():
    """Returns the name of this BiBli"""
    # start with the database
    name = get_kv("name")
    # fall back to the hostname
    if not name:
        name = socket.gethostname()
        if "." in name:
            name = name[:name.index(".")]
    return name


def my_portrait():
    """Returns the URL to this BiBli's portrait"""
    # start with the database, fallback to the BiBli SVG
    portrait = get_kv("portrait")
    if portrait:
        return url_for('static', filename="img/%s" % portrait)
    else:
        return url_for('static', filename='img/portrait.svg')


@app.route("/api/bibli", methods=['GET', ])
def bibli_get():
    """Get all the BiBlis this BiBli knows about."""
    d = {
        "biblis": []
    }
    # start with itself
    d["biblis"].append(self_dict())

    # get the bibli cache from the DB
    for ip in r.lrange("biblis", 0, -1):
        bibli = r.hgetall("bibli:%s" % ip)
        bibli["self"] = False
        d["biblis"].append(bibli)
    return jsonify(d)


@app.route("/api/bibli", methods=['POST', ])
def bibli_post():
    """Accept information about another BiBli"""
    data = request.get_json()
    bibli = data["bibli"]
    bibli["last_seen"] = calendar.timegm(datetime.utcnow().timetuple())
    if bibli["ip"] not in r.lrange("biblis", 0, -1):
        r.lpush("biblis", bibli["ip"])
    r.hmset("bibli:%s" % bibli["ip"], {
            "name": bibli["name"], "fqdn": bibli["fqdn"], "ip": bibli["ip"], "last_seen": bibli["last_seen"]})
    return jsonify({})


@app.route("/api/whoami")
def whoami():
    d = {
        "name": my_name(),
        "ip": ip_address(),
        "fqdn": socket.getfqdn()
    }
    if d["fqdn"] == "localhost":
        d["fqdn"] = "%s.local" % d["name"]
    return jsonify(d)


@app.route('/api/settings', methods=['PATCH', ])
def settings_patch():
    """Update BiBli's settings"""
    data = request.get_json()
    reboot = False
    # are we changeing BiBli's name
    if "name" in data and data["name"].lower() != my_name():
        reboot = True
        name = data["name"].lower()
        change_name(name)
    if "portrait" in data:
        change_portrait(data["portrait"])
    if reboot:
        # TODO: actually reboot
        pass
    d = {
        "reboot": reboot
    }
    return jsonify(d)


def change_name(name):
    """Update the system with a new name"""
    # set the local DB
    set_kv("name", name)
    # update /etc/hostname
    fname = "/etc/hostname"
    # check for if the hostname file exists (it doesn't on mac)
    # to only run this on actual PI systems
    if os.path.isfile(fname):
        with open(fname, "w") as file:
            file.write(name + "\n")
        fname = "/etc/hosts"
        with open(fname, "r") as file:
            content = file.read()
        content = content[:content.rindex("\t") + 1] + name + "\n"
        with open(fname, "w") as file:
            file.write(content)


def change_portrait(portrait):
    fname = "%s/%s" % (IMAGE_DIR, portrait["name"])
    set_kv("portrait", portrait["name"])
    with open(fname, "wb") as file:
        file.write(base64.decodestring(portrait["b64"]))
    # now crop and resize the image we just saved if it's a raster image
    if portrait["mime"] != "image/svg+xml":
        im = Image.open(fname)
        # crop down to a square
        w, h = im.size
        cropBox = [0, 0, w, h]
        if w > h:
            margin = (w - h) / 2
            cropBox[0] = margin
            cropBox[2] -= margin
        elif h > w:
            margin = (h - w) / 2
            cropBox[1] = margin
            cropBox[3] -= margin
        im = im.crop(cropBox)
        # resize down to 660px
        if im.size[0] > 660:
            im = im.resize((660, 660))
        im.save(fname)


def connected_bt():
    """Return the currently connected bluetooth audio device, if any"""
    global btc
    if btc is None:
        btc = Bluetoothctl()
    cds = btc.connected_devices()
    val = get_kv("bluetooth")
    if not val:
        return None
    addr, name = val.split("|")
    if addr in cds:
        return {
            "name": name,
            "mac_address": addr
        }
    else:
        set_kv("bluetooth", None)
    return None


@app.route('/')
def index():
    """Home page."""
    # disable everything on the router version
    control = True
    router = False
    if my_name() == "router":
        global camera
        global bibli_con
        global speech
        global video
        global bluetooth
        camera = False
        bibli_con = False
        speech = False
        video = False
        bluetooth = False
        control = False
    volume = float(get_kv("volume") or 1.0)
    btdev = None
    if bluetooth:
        d = connected_bt()
        btdev = d["name"] if d else None
    params = {
        "camera": camera,
        "video": video,
        "volume": int(volume * 100),
        "name": my_name(),
        "portrait": my_portrait(),
        "bluetooth": btdev,
        "control": control
    }
    return render_template('control.html', **params)


def gen(camera):
    """Video streaming generator function."""
    while True:
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    try:
        return Response(gen(Camera()),
                        mimetype='multipart/x-mixed-replace; boundary=frame')
    except:
        return redirect(url_for('static', filename='img/bg2.jpg'))


pre_x = -444
pre_y = -444


@sockets.route('/move')
def ws_move(ws):
    while not ws.closed:
        message = ws.receive()
        if message and message[:1] == "{":
            data = json.loads(message)
            if "x" in data and "y" in data:
                # get the X and Y coordinates of the joystick with sanity checks
                x = data["x"] if "x" in data and data["x"] <= 1.0 and data["x"] >= -1.0 else 0.0
                y = data["y"] if "y" in data and data["y"] <= 1.0 and data["y"] >= -1.0 else 0.0
                print("Moving %.2f , %.2f" % (x, y))

                # send the coords to bibli
                # if bibli_con:
                #bibli.moveBibli((x, y))


@sockets.route('/video')
def ws_video(ws):
    with picamera.PiCamera(resolution=(320, 240), framerate=30) as camera:
        try:
            # let camera warm up
            camera.start_preview()
            sleep(2)
            stream = io.BytesIO()
            for foo in camera.capture_continuous(stream, 'jpeg', use_video_port=True):
                if ws.closed:
                    break
                # send frame
                stream.seek(0)
                ws.send(base64.encodestring(stream.read()))
                # reset stream for next frame
                stream.seek(0)
                stream.truncate()
        except:
            ws.close()
        camera.stop_preview()
        camera.close()

#logging.basicConfig(filename='1234.log',level=logging.DEBUG)
autopilot_sub = None
@app.route("/api/move", methods=['POST', ])
def move():
    """API call to recieve the new movement coordinates."""
    # decode JSON message body
    data = request.get_json()
    # see if the request is to self drive
    if "autopilot" in data:
        if data["autopilot"]:
            status = data["autopilot"]
            #logging.debug('status is %d', status)
            if status == 111:
                global autopilot_sub
                autopilot_sub = subprocess.Popen(['python','autopilot.py'],shell=False)
                #logging.debug('autopilot start')
            else:
                global autopilot_sub
                #if autopilot_sub is not None:
                autopilot_sub.terminate()
                #autopilot_sub.kill()
                bibli.moveBibli(0)
                autopilot_sub = None
                bibli.moveBibli(0)
                #logging.debug('autopilot stop')
    elif "demorun" in data:
        print("Running Demo")
        demo.demoActions(bibli)
    elif "x" in data:
        x = data["x"]
        if bibli_con:
            bibli.moveBibli(x)

    return jsonify({})
    # elif "x" in data and "y" in data:
    # get the X and Y coordinates of the joystick with sanity checks
    #    x = data["x"] if "x" in data and data["x"] <= 1.0 and data["x"] >= -1.0 else 0.0
    #    y = data["y"] if "y" in data and data["y"] <= 1.0 and data["y"] >= -1.0 else 0.0
    #    print("Moving %.2f , %.2f" % (x, y))
    # send the coords to bibli
    #    if bibli_con:
    #        bibli.moveBibli((x, y))


@app.route("/api/demo/<demo_name>", methods=["GET", ])
def demo_run(demo_name):
    """API call to run a demo file."""
    # try:
    new_module = importlib.import_module("demos.%s" % demo_name)
    new_module.demoActions(bibli)
    # except ImportError:
    # print(demo_name + " is not a valid demo file!")

    return jsonify({})


@app.route('/api/led', methods=['POST', ])
def led():
    """API call to change to change the color of the LED light."""
    data = request.get_json()
    red = int(data["color"][:2], 16)
    green = int(data["color"][2:4], 16)
    blue = int(data["color"][4:], 16)
    print("Changing LED color to: RGB(%s,%s,%s)" % (red, green, blue))
    if bibli_con:
        bibli.changeColor((red, green, blue), [0, 1, 2])
    return jsonify({})


@app.route('/api/speak', methods=['POST', ])
def speak():
    """API call to have BiBli speak a phrase."""
    subprocess.call("amixer sset Master 100%", shell=True)
    data = request.get_json()
    lang = data["lang"] if "lang" in data and len(data["lang"]) else "en"
    #espeak.set_parameter(espeak.Parameter.Rate, 165)
    #espeak.set_parameter(espeak.Parameter.Pitch, 70)
    if lang == "es":
        # espeak.set_voice("europe/es")
        os.system("sudo espeak -v es '" + data["msg"] + "' -a 165 -p 70")
    else:
        os.system("sudo espeak -v en-us '" + data["msg"] + "' -a 165 -p 70")
        # espeak.set_voice("en-us")
    # espeak.synth(data["msg"])
    # subprocess.call('espeak -v%s+f3 -a200 %s &' % (lang, "'\"%s\"'" % data["msg"]), shell=True)
    return jsonify({})


@app.route("/api/media", methods=['GET', ])
def media_get():
    """API call to retrieve all playable media on the BiBli."""
    # for music and stories, we can read the id3 tags out of the mp3s and get good info
    # we're a lot more limited on the metadata on video
    # We should deliberately compress to a format with good metadata when picking actual videos
    d = {
        "music": [],
        "story": [],
        "video": []
    }
    # find any music
    for fname in os.listdir(MUSIC_DIR):
        if ".mp3" in fname:
            # parse mp3 id tag info
            audiofile = MP3("%s/%s" % (MUSIC_DIR, fname))
            track = {"file": fname, "title": "", "artist": "?"}
            tags = audiofile.tags
            if tags:
                track["artist"] = tags["artist"][0] if "artist" in tags else "?"
                track["title"] = tags["title"][0] if "title" in tags else None
            if audiofile.info:
                seconds = int(audiofile.info.length)
                minutes = seconds / 60
                seconds = seconds % 60
                track["duration"] = "%s:%02d" % (minutes, seconds)
            # make sure there's a title
            if not track["title"]:
                track["title"] = fname.replace(".mp3", "")
            d["music"].append(track)
    # stories
    for fname in os.listdir(STORY_DIR):
        if ".mp3" in fname:
            # parse mp3 id tag info
            audiofile = MP3("%s/%s" % (STORY_DIR, fname))
            track = {"file": fname, "title": "", "author": "?"}
            tags = audiofile.tags
            if tags:
                track["author"] = tags["artist"][0] or "?"
                track["title"] = tags["title"][0] if "title" in tags else None
            if audiofile.info:
                seconds = int(audiofile.info.length)
                minutes = seconds / 60
                seconds = seconds % 60
                track["duration"] = "%s:%02d" % (minutes, seconds)
            # make sure there's a title
            if not track["title"]:
                track["title"] = fname.replace(".mp3", "")
            d["story"].append(track)
    # videos
    for fname in os.listdir(VIDEO_DIR):
        if ".mp4" in fname:
            videofile = MP4("%s/%s" % (VIDEO_DIR, fname))
            track = {
                "file": fname,
                "title": ""
            }
            tags = videofile.tags
            if tags:
                for k, v in tags.items():
                    track[k] = v
            seconds = int(videofile.info.length)
            minutes = seconds / 60
            seconds = seconds % 60
            track["duration"] = "%s:%02d" % (minutes, seconds)
            # make sure there's a title
            if not track["title"]:
                track["title"] = fname.replace(".mp4", "")
            d["video"].append(track)
    return jsonify(d)


@app.route("/api/media", methods=['POST', ])
def media_post():
    """API call to store new media on the BiBli"""
    data = request.get_json()
    fname = "%s/%s" % (MUSIC_DIR, data["name"])
    with open(fname, "wb") as file:
        file.write(base64.decodestring(data["b64"]))
    audiofile = MP3(fname)
    track = {"file": data["name"], "title": "", "artist": "?"}
    tags = audiofile.tags
    if tags:
        track["artist"] = tags["artist"][0] if "artist" in tags else "?"
        track["title"] = tags["title"][0] if "title" in tags else None
    if audiofile.info:
        seconds = int(audiofile.info.length)
        minutes = seconds / 60
        seconds = seconds % 60
        track["duration"] = "%s:%02d" % (minutes, seconds)
    # make sure there's a title
    if not track["title"]:
        track["title"] = fname.replace(".mp3", "")
    return jsonify({"music": track})


@app.route("/portrait")
def portrait():
    return redirect(my_portrait())


@app.route("/api/audio", methods=['POST', ])
def audio_play():
    """API call to play music"""
    data = request.get_json()
    pygame.mixer.init()
    subprocess.call("amixer sset Master 100%", shell=True)
    if "file" in data:
        if len(data["file"]):
            if data["type"] == "story":
                filepath = "%s/%s" % (STORY_DIR, data["file"])
            else:
                filepath = "%s/%s" % (MUSIC_DIR, data["file"])
            print("Now playing audio: %s" % data["file"])
            volume = float(get_kv("volume") or "1.0")
            pygame.mixer.music.load(filepath)
            pygame.mixer.music.set_volume(volume)
            pygame.mixer.music.play()
            set_kv("now_playing", "audio")
            # subprocess.call("mplayer -input file=/home/pi/sound_fifo %s < /dev/null &" % filepath, shell=True)
        else:
            pygame.mixer.music.stop()
            set_kv("now_playing", None)
            # subprocess.call("echo 'q' > /home/pi/sound_fifo", shell = True)
    return jsonify({})


@app.route("/api/audio", methods=['PATCH', ])
def audio_control():
    """API call to modify audio playback"""
    data = request.get_json()
    pygame.mixer.init()
    now_playing = get_kv("now_playing")
    if "volume" in data and (type(data["volume"]) is float or type(data["volume"]) is int):
        volume = data["volume"]
        if volume > 1.0:
            volume = 1.0
        elif volume < 0.0:
            volume = 0.0
        set_kv("volume", str(volume))
        pygame.mixer.music.set_volume(volume)
    if "pause" in data and now_playing == "audio":
        if data["pause"]:
            pygame.mixer.music.pause()
        else:
            pygame.mixer.music.unpause()

    if now_playing == "audio":
        if "pause" in data:
            print("Pausing video")
            subprocess.call("echo 'pause' > /home/pi/fifos", shell=True)
    jsond = {
        "volume": get_kv("volume")
    }
    return jsonify(jsond)


@app.route("/api/video", methods=['POST', ])
def video_play():
    """API call to control the video screen on the Bibli"""
    data = request.get_json()
    if "camera" in data:
        if data["camera"]:
            print("Displaying camera feed on screen")
            subprocess.call("/home/pi/rpi-fbcp/build/fbcp &", shell=True)
        else:
            subprocess.call("pkill fbcp", shell=True)
            subprocess.call(
                "sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
    elif "file" in data:
        if data["file"]:
            filepath = "%s/%s" % (VIDEO_DIR, data["file"])
            print("Now playing video: %s" % filepath)
            subprocess.call("sudo SDL_VIDEODRIVER=fbcon SDL_FBDEV=/dev/fb1 mplayer -vo sdl -framedrop -quiet -input file=/home/pi/fifos %s < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg" % filepath, shell=True)
        else:
            subprocess.call("echo 'q' > /home/pi/fifos", shell=True)
            subprocess.call(
                "sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
    return jsonify({})


@app.route("/api/video", methods=['PATCH', ])
def video_control():
    """API call to pause/resume existing video"""
    data = request.get_json()
    if now_playing == "video":
        if "pause" in data:
            print("Pausing video")
            subprocess.call("echo 'pause' > /home/pi/fifos", shell=True)
    return jsonify({})


@app.route("/api/bluetooth", methods=["GET", ])
def bluetooth_get():
    if bluetooth:
        global btc
        if btc is None:
            btc = Bluetoothctl()
        btc.start_scan()
        sleep(10)
        devices = btc.get_connectable_devices()
        for device in btc.get_available_devices():
            devices.append(device)
        data = {
            "devices": devices
        }
    else:
        data = {
            "devices": [{
                "mac_address": "E4:22:A5:1C:FB:19",
                        "name": "PLT_BBFIT"
                        }, {
                "mac_address": "AA:BB:CC:DD:EE:FF",
                        "name": "Other Device"
                        }]
        }
    return jsonify(data)


@app.route("/api/bluetooth", methods=["POST", ])
def bluetooth_post():
    """Pair with a new bluetooth device"""
    data = request.get_json()
    if "mac_address" in data and "name" in data and btc:
        btc.trust(data["mac_address"])
        # btc.pair(data["mac_address"])
        btc.connect(data["mac_address"])
        set_kv("bluetooth", "%s|%s" % (data["mac_address"], data["name"]))
        pygame.mixer.quit()

        # bt.pair(data["addr"], data["name"])
    return jsonify({})


@app.route("/api/bluetooth", methods=["DELETE", ])
def bluetooth_delete():
    """Unpair a bluetooth device"""
    dev = connected_bt()
    if dev:
        btc.remove(dev["mac_address"])
        pygame.mixer.quit()
    return jsonify({})


@app.route("/api/bluetooth", methods=["PATCH", ])
def bluetooth_patch():
    """Connect/unconnect the paired bluetooth device"""
    data = request.get_json()
    dev = connected_bt()
    if "connect" in data and dev:
        if data["connect"]:
            btc.connect(dev["mac_address"])
        else:
            btc.disconnect(dev["mac_address"])
        pygame.mixer.quit()
    return jsonify({})


def demo_information(fname):
    with open("%s/%s" % (DEMO_DIR, fname), 'r') as file:
        lines = file.readlines()
        if "##BDF##" in lines[0]:
            return {
                "file": fname.replace(".py", ""),
                "name": lines[1].split(":")[1].strip(),
                "description": lines[2].split(":")[1].strip()
            }
    return None


@app.route("/api/demo", methods=["GET", ])
def demo_get():
    """Return the list of existing demo files."""
    data = {
        "demos": []
    }
    for fname in os.listdir(DEMO_DIR):
        # open the file to make sure it's got valid demo headers
        if "__init__" in fname or ".pyc" in fname:
            continue
        demo = demo_information(fname)
        if demo:
            data["demos"].append(demo)
    return jsonify(data)


@app.route("/api/demo", methods=["POST", ])
def demo_post():
    """Upload a new demo file."""
    data = request.get_json()
    fname = "%s/%s" % (DEMO_DIR, data["file"])
    fstr = base64.decodestring(data["b64"])
    if "##BDF##" not in fstr:
        return jsonify({"demo": None})
    with open(fname, "wb") as file:
        file.write(fstr)
    try:
        py_compile.compile(fname)
    except:
        os.remove(fname)
        return jsonify({"demo": None})
    data = {
        "demo": demo_information(data["file"])
    }
    return jsonify(data)


@app.route('/desktop/<dstp>')
def show_desktop(dstp):
    if dstp == 'show':
        subprocess.call(
            "sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)


@app.route('/jasper/<jsp>')
def jasper_ctrl(jsp):
    print(jsp)
    if jsp == 'jON':
        subprocess.call(
            "/home/pi/BiBli/language/jasper/jasper.py &", shell=True)
    elif jsp == 'jOFF':
        subprocess.call("pkill -f jasper.py", shell=True)


@app.route('/movie/<movie>')
def play_movie(movie):
    print(movie)
    if movie == 'p':
        subprocess.call("/home/pi/rpi-fbcp/build/fbcp &", shell=True)
    elif movie == 'o':
        subprocess.call("pkill fbcp", shell=True)
        subprocess.call(
            "sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)


@app.route('/music/<music>')
def play_music(music):
    print(music)
    if music == 'mon':
        subprocess.call(
            "mplayer -input file=/home/pi/fifos2 /home/pi/BiBli/media/audio/music/Argon_Dont_Speak.mp3 < /dev/null &", shell=True)
    elif music == 'moff':
        subprocess.call("echo 'q' > /home/pi/fifos2", shell=True)


@app.route('/videos/<checktext>')
def playvideo(checktext):
    print(checktext)
    vname = '"{}"'.format(checktext)
    subprocess.call("sudo SDL_VIDEODRIVER=fbcon SDL_FBDEV=/dev/fb1 mplayer -vo sdl -framedrop -quiet -input file=/home/pi/fifos /home/pi/BiBli/media/video/" +
                    vname + " < /dev/null && sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)
    #subprocess.call("sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/gif/bg2.jpg", shell=True)


@app.route('/videoctrl/<vdoctl>')
def video_ctrl(vdoctl):
    if vdoctl == 'pause':
        subprocess.call("echo 'pause' > /home/pi/fifos", shell=True)
    elif vdoctl == 'stp':
        subprocess.call("echo 'q' > /home/pi/fifos", shell=True)
        subprocess.call(
            "sudo fbi -T 2 -d /dev/fb1 -noverbose -a /home/pi/BiBli/media/img/bg2.jpg", shell=True)


@app.route('/sounds/<checktext>')
def playsound(checktext):
    print(checktext)
    sname = '"{}"'.format(checktext)
    subprocess.call("mplayer -input file=/home/pi/sound_fifo /home/pi/BiBli/media/audio/music/" +
                    sname + " < /dev/null &", shell=True)


@app.route('/soundctrl/<sndctl>')
def sound_ctrl(sndctl):
    if sndctl == 'pause':
        subprocess.call("echo 'pause' > /home/pi/sound_fifo", shell=True)
    elif sndctl == 'stp':
        subprocess.call("echo 'q' > /home/pi/sound_fifo", shell=True)


@app.route('/stories/<checktext>')
def playstory(checktext):
    print(checktext)
    sname = '"{}"'.format(checktext)
    subprocess.call("mplayer -input file=/home/pi/story_fifo /home/pi/BiBli/media/audio/story/" +
                    sname + " < /dev/null &", shell=True)


@app.route('/storytrl/<styctl>')
def story_ctrl(styctl):
    if styctl == 'pause':
        subprocess.call("echo 'pause' > /home/pi/story_fifo", shell=True)
    elif styctl == 'stp':
        subprocess.call("echo 'q' > /home/pi/story_fifo", shell=True)


if __name__ == '__main__':
    # app.run(host='0.0.0.0', threaded=True)
    from gevent import pywsgi
    from geventwebsocket.handler import WebSocketHandler
    server = pywsgi.WSGIServer(('', 5000), app, handler_class=WebSocketHandler)
    server.serve_forever()

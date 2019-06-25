import os
import pygame
from espeak import espeak

from db import get_kv, set_kv

class AudioControl:

	def __init__(self):
		pygame.mixer.init()
		# starting from the current file, fine the media directory
		dir_path = os.path.dirname(os.path.realpath(__file__))
		dir_path = dir_path[:dir_path.index("brain/")]
		self.MUSIC_DIR = "%smedia/audio/music" % dir_path
		self.STORY_DIR = "%smedia/audio/story" % dir_path

	def playMusic(self, filename):
		"""Plays a music file from the music directory. Takes a full file name."""
		self._playFile("%s/%s" % (self.MUSIC_DIR, filename))

	def playStory(self, filename):
		"""Plays a story file from the story directory. Takes a full file name."""
		self._playFile("%s/%s" % (self.STORY_DIR, filename))
		

	def _playFile(self, filename):
		volume = float(get_kv("volume") or "1.0")
		pygame.mixer.music.load(filename)
		pygame.mixer.music.set_volume(volume)
		pygame.mixer.music.play()
		set_kv("now_playing", "audio")

	def stop(self):
		"""Stops and currently playing """
		pygame.mixer.music.stop()
		set_kv("now_playing", None)

	def pause(self):
		pygame.mixer.music.pause()

	def unpause(self):
		pygame.mixer.music.unpause()

	def setVolume(self, volume):
		if type(volume) is not float:
			volume = 1.0
		if volume > 1.0:
			volume = 1.0
		elif volume < 0.0:
			volume = 0.0
		set_kv("volume", str(volume))
		pygame.mixer.music.set_volume(volume)

	def speak(self, msg, lang="en"):
		espeak.set_parameter(espeak.Parameter.Rate, 165)
		espeak.set_parameter(espeak.Parameter.Pitch, 70)
		if lang == "es":
			espeak.set_voice("europe/es")
		else:
			espeak.set_voice("en-us")
		espeak.synth(msg)
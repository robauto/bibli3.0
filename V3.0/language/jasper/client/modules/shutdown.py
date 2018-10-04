import subprocess
import re

WORDS = ["POWER OFF"]

def handle(text, mic, profile):
    subprocess.call("sudo shutdown -h now", shell = True)


def isValid(text):
    return bool(re.search(r'\bpower off\b', text, re.IGNORECASE))

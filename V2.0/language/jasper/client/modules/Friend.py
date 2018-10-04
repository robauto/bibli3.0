import re

WORDS=["FRIEND"]

def handle(text, mic, profile):
	mic.say("yes, you are my best friend, I will alway be with you.")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'friend', text, re.IGNORECASE))


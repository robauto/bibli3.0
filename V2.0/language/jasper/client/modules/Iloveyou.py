import re

WORDS=["LOVE"]

def handle(text, mic, profile):
	mic.say("Thank you, I love you too.")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'love', text, re.IGNORECASE))


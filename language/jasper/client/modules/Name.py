import re

WORDS=["NAME"]

def handle(text, mic, profile):
	mic.say("my name is Lucky. stay with me, you will always be lucky!")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'name', text, re.IGNORECASE))


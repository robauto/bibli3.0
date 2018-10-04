import re

WORDS=["THANK YOU","THANKS"]

def handle(text, mic, profile):
	mic.say("you are wellcome.")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'thank', text, re.IGNORECASE))


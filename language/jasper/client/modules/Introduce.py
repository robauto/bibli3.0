import re

WORDS=["INTRODUCE"]

def handle(text, mic, profile):
	mic.say("Hi, I am Bibli, The second generation of Bibli robot, the fist generation works in Longmont Library, we are produced by Robauto. designed by Jalali, Qi, and Wei.")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'introduce', text, re.IGNORECASE))


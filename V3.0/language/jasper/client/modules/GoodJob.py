import re

WORDS=["GOOD JOB","SMART"]

def handle(text, mic, profile):
	mic.say("Thank you, I will keep going and do my best.")

def isValid(text):
    """
        Returns True if the input is related to thank.

        Arguments:
        text -- user-input, typically transcribed speech
    """
    return bool(re.search(r'job', text, re.IGNORECASE))


import json
import os

with open(os.path.dirname(__file__) + '/config.json', 'r') as config_file:
    config_string = config_file.read()
    CONFIG = json.loads(config_string)

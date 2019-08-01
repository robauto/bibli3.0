import json

with open('config.json', 'r') as config_file:
    config_string = config_file.read()
    CONFIG = json.loads(config_string)

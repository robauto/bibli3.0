import RPi.GPIO as io
import atexit

# Set up the pin numbering scheme
io.setmode(io.BOARD)

# Cleanup when the program ends
atexit.register(io.cleanup)

import data
import json
import requests
import RPi.GPIO as GPIO
import dht11
import time
import datetime

# initialize GPIO
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.cleanup()

# read data using pin 14
instance = dht11.DHT11(pin=14)

while True:
    result = instance.read()
    if result.is_valid():
        data = {}
        data['temperature'] = result.temperature
        data['humidity'] = result.humidity
        data['deviceId'] = '371f8d2e-766f-11e8-adc0-fa7ae01bbebc'
        data_json = json.dumps(data)
        r = requests.post('https://localhost:8080/measurement', data_json)
        print(r.status_code)

time.sleep(1)
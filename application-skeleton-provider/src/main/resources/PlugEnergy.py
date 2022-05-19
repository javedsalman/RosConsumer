import requests
import json
import sys
import os
import glob


device_id = sys.argv[1]

plug_data_energy = requests.get('http://130.240.234.31:8083/JS/Run/zway.devices['+str(device_id)+'].instances[0].commandClasses[0x32].valueOf()',auth=('admin','eislab'),stream=True)

print (plug_data_energy.json()['data']['0']['val']['value'])
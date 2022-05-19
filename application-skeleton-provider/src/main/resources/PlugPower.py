import requests
import json
import sys
import os
import glob

device_id = sys.argv[1]


plug_data_power = requests.get('http://130.240.234.31:8083/JS/Run/zway.devices['+str(device_id)+'].instances[0].commandClasses[0x31].valueOf()',auth=('admin','eislab'),stream=True)


print ("print plug_data_power() :")
print (plug_data_power.json()['data']['4']['val']['value'])


with open("plug_data_power.csv", "w") as outfile: 
    json.dump(plug_data_power.json()['data']['4']['val']['value'], outfile)     

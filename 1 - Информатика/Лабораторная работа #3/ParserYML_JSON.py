import yaml
import json
import time
start=time.time()
with open("input.yml", 'r') as yaml_in, open("output2.json", "w") as json_out:
    yaml_object = yaml.safe_load(yaml_in)
    json.dump(yaml_object, json_out)
print(time.time()-start)
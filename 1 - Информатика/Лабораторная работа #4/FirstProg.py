import re

s = input()
print(re.sub(r'\b([^\W\d]+)(\s+\1)+\b', r'\1', s, flags=re.I))

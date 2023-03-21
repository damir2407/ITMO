inputfile = open('input.yml', 'r')
outputfile = open('output.json', 'w')
import time
start=time.time()
a = list()
b = list()
c = list()
d = list()
a = inputfile.read().split('\n')
b.append('{')
for i in range(0, len(a)):
    if (a[i].find(":") == len(a[i])-1):
        o1 = a[i].replace(a[i].lstrip(), '"' + a[i].lstrip())
        o1 = '  ' + o1[:o1.find(':')] + '"' + o1[o1.find(':'):] + " {"
    else:
        o1 = a[i].replace(a[i].lstrip(), '"' + a[i].lstrip())
        o1 = '  ' + o1[:o1.find(':')] + '"' + o1[o1.find(':'):]
    b.append(o1)
c.append('{')
for i in range(0, len(b)-1):
    if (len(b[i]) - len(b[i].lstrip())) <= (len(b[i + 1]) - len(b[i + 1].lstrip())):
        c.append(b[i + 1])
    else:
        c.append((len(b[i + 1]) - len(b[i + 1].lstrip())) * ' ' + '},')
        c.append(b[i + 1])
c.append('      }')
for i in range(0, len(c)-1):
    if (c[i].find(",") != len(c[i]) - 1) and (c[i].find("{") != len(c[i]) - 1) and (c[i + 1].count('}') == 0):
        o1 = c[i] + ','
    else:
        o1=c[i]
    d.append(o1)
d.append('      }')
d.append('    }')
d.append('  }')
d.append('}')
for i in range(0,len(d)):
    outputfile.write(d[i]+'\n')
print(time.time()-start)
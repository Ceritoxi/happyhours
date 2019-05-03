#!/usr/bin/python
import time;

timee = time.asctime(time.localtime(time.time())) + ''
splitted = timee.split(" ")
if (splitted[2] != ''):
	timee = '';
	splitted[1] += ' '
	for split in splitted:
		timee += split + ' '
		
localtimeparts = timee.split(" ")
month = localtimeparts[1]
year = localtimeparts[5]

filename = year + "-" + month + ".txt"


hoursfile = open(filename, "a+")
hoursfile.close()


laststamp = 'END'
with open(filename) as hoursfile:
	for line in hoursfile:
		splits = line.split(" ")
		laststamp = splits[6]
		
hoursfile = open(filename, "a+")


if (laststamp == 'START') :
	hoursfile.write(timee + ' END ' + "\n")
else:
	hoursfile.write(timee + ' START ' + "\n")

hoursfile.close()

total = 0
loggedtotal = 0;
avarage = 0
noofdays = 0

isdaystart = 'YOU STARTED THE DAY ';

with open(filename) as hoursfile:
	currentday = '0';
	for line in hoursfile:
		splits = line.split(" ")
		if (len(splits) > 5):
			laststamp = splits[6]
			timeparts = splits[4].split(":")
			if (laststamp == 'END'):
				if (currentday != splits[3]):
					currentday = splits[3]
					noofdays = noofdays + 1
				total = total + int(timeparts[2])
				total = total + int(timeparts[1]) * 60
				total = total + int(timeparts[0]) * 60 * 60
				loggedtotal = total
				isdaystart = 'GTFO'
			else:
				total = total - int(timeparts[2])
				total = total - int(timeparts[1]) * 60
				total = total - int(timeparts[0]) * 60 * 60
				isdaystart = 'YOU STARTED THE DAY'
		
print(isdaystart)
print(loggedtotal, "secs total |", int(loggedtotal / 60 / 60), ":", int((loggedtotal / 60) % 60), ":", int((loggedtotal) % 60), "hours total")
if (noofdays> 0):
	print(int(loggedtotal / noofdays), "secs on avarage |", int(loggedtotal / noofdays / 60 / 60), ":", int((loggedtotal / noofdays / 60) % 60), ":", int((loggedtotal / noofdays) % 60), " time on avarage")

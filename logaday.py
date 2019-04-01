#!/usr/bin/python
import time;

laststamp = 'END'
fak = False

with open("hours.txt" , "a+") as hoursfile:
	for line in hoursfile:
		splits = line.split(" ")
		if (len(splits) > 5):
			laststamp = splits[6]
		else:
			fak = True
		
hoursfile = open("hours.txt", "a+")

if (laststamp == 'START') :
	hoursfile.write(time.asctime(time.localtime(time.time())) + ' END ' + "\n")
else:
	hoursfile.write(time.asctime(time.localtime(time.time())) + ' START ' + "\n")

hoursfile.close()

if fak:
	exit()

total = 0
loggedtotal = 0;
avarage = 0
noofdays = 0

isdaystart = 'YOU STARTED THE DAY ';

with open("hours.txt") as hoursfile:
	for line in hoursfile:
		splits = line.split(" ")
		if (len(splits) > 5):
			laststamp = splits[6]
			timeparts = splits[4].split(":")
			if (laststamp == 'END'):
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
print(loggedtotal, "secs total |", loggedtotal / 60 / 60, "hours total")
print(loggedtotal / noofdays, " secs on avarage |", loggedtotal / noofdays / 60 / 60, " hours on avarage")

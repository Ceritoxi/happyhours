#!/usr/bin/python
import time;

laststamp = 'END'
total = 0
loggedtotal = 0;
avarage = 0
noofdays = 0

with open("hours.txt") as hoursfile:
	currentday = 0
	for line in hoursfile:
		splits = line.split(" ")
		if (len(splits) > 5):
			laststamp = splits[6]
			timeparts = splits[4].split(":")
			if (laststamp == 'END'):
				if (currentday != splits[3]):
					noofdays = noofdays + 1
				total = total + int(timeparts[2])
				total = total + int(timeparts[1]) * 60
				total = total + int(timeparts[0]) * 60 * 60
				loggedtotal = total
			else:
				total = total - int(timeparts[2])
				total = total - int(timeparts[1]) * 60
				total = total - int(timeparts[0]) * 60 * 60
		
print(loggedtotal, "secs total |", loggedtotal / 60 / 60, "hours total")
if (noofdays > 0):
	print(loggedtotal / noofdays, " secs on avarage |", loggedtotal / noofdays / 60 / 60, " hours on avarage")
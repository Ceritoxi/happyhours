#!/usr/bin/python
import time;

laststamp = 'END'
total = 0
loggedtotal = 0;
avarage = 0
noofdays = 0

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
			else:
				total = total - int(timeparts[2])
				total = total - int(timeparts[1]) * 60
				total = total - int(timeparts[0]) * 60 * 60
		else:
			exit()
		
print(loggedtotal, "secs total |", loggedtotal / 60 / 60, "hours total")
print(loggedtotal / noofdays, " secs on avarage |", loggedtotal / noofdays / 60 / 60, " hours on avarage")
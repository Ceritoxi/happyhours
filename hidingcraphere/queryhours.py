#!/usr/bin/python
import time;

localtime = time.asctime(time.localtime(time.time()))
localtimeparts = localtime.split(" ")
month = localtimeparts[1]
year = localtimeparts[5]

filename = year + "-" + month + ".txt"

laststamp = 'END'
total = 0
loggedtotal = 0;
avarage = 0
noofdays = 0

with open(filename) as hoursfile:
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
				loggedtotal = total
		
if (laststamp == 'START'):
	now = time.asctime(time.localtime(time.time())).split(" ")
	nowtimes = now[4].split(":")
	noofdays = noofdays + 1
	loggedtotal = loggedtotal + int(nowtimes[2])
	loggedtotal = loggedtotal + int(nowtimes[1]) * 60
	loggedtotal = loggedtotal + int(nowtimes[0]) * 60 * 60
print(loggedtotal, "secs total |", int(loggedtotal / 60 / 60), ":", int((loggedtotal / 60) % 60), ":", int((loggedtotal) % 60), "hours total")
if (noofdays > 0):
	print(int(loggedtotal / noofdays), "secs on avarage |", int(loggedtotal / noofdays / 60 / 60), ":", int((loggedtotal / noofdays / 60) % 60), ":", int((loggedtotal / noofdays) % 60), " time on avarage")
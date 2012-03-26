import re
import json


def text2List(text):
	textstart = 0
	for textm in re.finditer("\[\[([^\[\|]+)(\|([^\[]+))?\]\]",text):
		linkm = re.match("(([^\:]+):)?(.+)", textm.group(1))
		yield ('text', text[textstart:textm.start()])
		yield ('link', linkm.group(2), linkm.group(3), textm.group(3))
		textstart = textm.end()

def listify(json, textsections):
	for t in json:
		for e in json[t]:
			for attr in json[t][e]:
				if attr in textsections:
					json[t][e][attr] = list(text2List(json[t][e][attr]))				


json101 = json.load(open("../data/generated/all.json"))		
listify(json101,['discussion', 'dicussion', 'architecture', 'intent', 'motivation', 'issues', 'usage', 'description', 'summary', 'illustration'])
outf = open("../data/generated/allLinkified.json", "write")
outf.write(json.dumps(json101))
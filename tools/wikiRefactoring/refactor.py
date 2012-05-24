import sys
import getpass
from wikitools import wiki
import page2
import category2

apiurl = "http://101companies.org/api.php"
wiki101 = wiki.Wiki(apiurl)

def dorename(titles, flags):
	if len(titles) < 2:
		exit("Need two titles to do renaming")
	if titles[0].startswith("Category:"):
		p = category2.Category2(wiki101, title=titles[0])
	else:
		p = page2.Page2(wiki101, title=titles[0])
	p.intermove(mvto=titles[1], **flags)


def dopromote(titles, flags):
	p = page2.Page2(wiki101, title=titles[0])
	if len(titles) > 1:
		ntitle = titles[1]
	else:
		ntitle = False
	p.promote(title=ntitle, **flags)

def dodemote(titles, flags):
	p =  category2.Category2(wiki101, title=titles[0])
	if len(titles) > 1:
		ntitle = titles[1]
	else:
		ntitle = False
	p.demote(title=ntitle, **flags)

commands = dict(rename=dorename, promote=dopromote, demote=dodemote)	

if len(sys.argv) < 3 or not commands.has_key(sys.argv[1]):
	exit("Syntax: (rename|demote|promote) <title1> [<title2>] [-- <flags>]")

if (len(sys.argv) > 3 and sys.argv[3] == "--"):
	titles = sys.argv[2:3]
	rawflags = sys.argv[4:]
elif (len(sys.argv) > 4 and sys.argv[4] == "--"):
	titles = sys.argv[2:4]
	rawflags = sys.argv[5:]
else:
	titles = sys.argv[2:]
	rawflags = []

flags = {}
for flag in rawflags:
	flags[flag] = True

wiki101.login(raw_input("Wikisername: "), getpass.getpass("Password: "))
commands[sys.argv[1]](titles, flags)



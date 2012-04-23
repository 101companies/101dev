from wikitools import wiki
from wikitools import page
import page2
import category2
import getpass

apiurl = "http://localhost/api.php"

wiki101 = wiki.Wiki(apiurl)
wiki101.login(raw_input("Wikisername: "), getpass.getpass("Password: "))
#testpage2 = page2.Page2(wiki101, title="Bar")
testpage = category2.Category2(wiki101, title="Category:Programming paradigm")
#testpage = page.Page(wiki101, title="101implementation:happstack")
#testpage.move("DOM")
testpage.demote("Programming paradigm2",dropchilds=True)
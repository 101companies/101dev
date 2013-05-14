from wikitools import wiki
import page2

apiurl = "http://101companies.org/api.php"
wiki101 = wiki.Wiki(apiurl)

p = page2.Page2(wiki101, "Contribution:haskellDB")
p.rewriteInternlink("Language:Haskelll", "Language:Haskell")

# MediaWiki page with extended refactoring functionalities

# This class extends a class of python-wikitools.
# See <code.google.com/p/python-wikitools/> for source code and documentation.

import re
from wikitools import api
from wikitools import wiki
from wikitools import page
from wikitools import category

class AlreadyExists(wiki.WikiError):
	'''Page already exists'''	

class NotPromotable(wiki.WikiError):
	'''Page is already a category'''
	
class Page2(page.Page):

	def __init__(self, wiki, title=False, check=True, followRedir=False, section=False, sectionnumber=False, pageid=False):
		page.Page.__init__(self, site=wiki, title=title, check=check, followRedir=followRedir, section=section, sectionnumber=sectionnumber, pageid=pageid)

	def getBacklinks(self):
		''' Find all pages that link to this page

		limit - maximum number of pagenames to return
		'''
		params = {
			'action': 'query',
			'list': 'backlinks',
			'bltitle': self.title,
		}
		apiresponse = api.APIRequest(self.site, params).query()
		return map(lambda x: x['title'], apiresponse['query']['backlinks'])

	def rewriteReferences(self, pagetitles, newtitle):
		print pagetitles, newtitle
		for pn in pagetitles:
			page = Page2(self.site, title=pn)
			page.rewriteInternlink(self.title, newtitle)


	def rewriteInternlink(self, clink, nlink, nlinktext=""):	
		''' Rewrite all given link on this page and return the new wikitext

		olink - the current name of the intern link
		nlink - the new name of the intern link
		nlinkname - new text for the new intern link
		'''
		fromCat = clink.startswith("Category:")		
		toCat = nlink.startswith("Category:")
		ntext = self.getWikiText()
		offset = 0
		for textm in re.finditer("(\[\[:?)(" + re.escape(clink.replace(" ", "_")) + ")(\|[^\[]+)?(\]\])", self.getWikiText().replace(" ", "_"), re.IGNORECASE):
			if fromCat and not toCat and not textm.group(1).endswith(":"):
				ntext = ntext[:textm.start() + offset] + ntext[textm.end() + offset:]
				offset = offset - len(textm.group(0))
				continue
			if not fromCat and toCat:
				openb = "[[:"
			elif fromCat and not toCat:
				openb = "[["
			else:
				openb = textm.group(1)
			if nlinktext == "" and textm.group(3) and nlink.count(":") == 0:
				lt = textm.group(3)
			elif nlinktext != "":
				lt = "|" + nlinktext
			else:
				lt = ""
			if textm.group(2)[0].islower() :
				nlink = nlink[0].lower() + nlink[1:]
			newlink = (openb + nlink + lt + "]]").replace("_"," ")

			print "Replacing", textm.group(0), "by", newlink, "on", self.title
			ntext = ntext[:textm.start() + offset] +  newlink + ntext[textm.end() + offset:]
			offset =  offset + len(newlink) - len(textm.group(0))
		self.edit(text=ntext)
		return ntext

	def promote(self, title=False, removePrefix=False, force=False, reason=False, watch=False, unwatch=False):
		''' Promote a page to a category 

		newtitle - title of the category
		removePrefix - when using the page title as the category title remove the prefix
		force - in case the category already exists, delete it
		'''
		if not self.exists:
			print self.getWikiText()
			raise page.NoPage
		if self.title.startswith("Category:"):
			raise NotPromotable
		if not title:
			title = self.title
			if removePrefix:
				title = re.sub(".*:", "", title)
			title = "Category:" + title
		cat = category.Category(self.site, title)
		if cat.exists and not force:
			raise AlreadyExists
		cat.edit(text=self.getWikiText())	
		self.rewriteReferences(self.getBacklinks(), title)
		self.delete(reason=reason, watch=watch, unwatch=unwatch)
		return cat


	def intermove(self, mvto, reason=False, movetalk=False, noredirect=False, watch=False, unwatch=False, removePrefix=False, force=False):
		''' Move page or promote it and rename links on backlinking pages

		force, removePrefix, delete - see promote()
		other arguments - see python-wikitools documentation
		'''
		fromCat =  self.title.startswith("Category:")
		toCat = mvto.startswith("Category:")
		if not fromCat and toCat:
			self.promote(title=mvto, removePrefix=removePrefix, force=force)
		else:
			mvtopage = Page2(self.site, title=mvto)
			if mvtopage.exists:
				if force:
					mvtopage.delete(reason=reason, watch=watch, unwatch=unwatch)
				else:
					raise AlreadyExists
			self.rewriteReferences(self.getBacklinks(), mvto)		
			print "FUUUU"
			t = self.title
			page.Page.move(self, mvto, reason=reason, movetalk=movetalk, noredirect=True, watch=watch, unwatch=unwatch)
			tod = Page2(self.site, title=t)
			tod.delete()
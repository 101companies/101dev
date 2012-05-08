# MediaWiki category with extended refactoring functionalities

# This class extends a class of python-wikitools.
# See <code.google.com/p/python-wikitools/> for source code and documentation.

import re
from wikitools import api
from wikitools import wiki
from wikitools import page
from wikitools import category
import page2

class NoCategoryTitle(wiki.WikiError):
	''' Not a category title'''

class NotNonCategoryTitle(wiki.WikiError):
	''' Not a non-category title'''

class NonEmptyCategory(wiki.WikiError):
	''' Category has members and/or subcategories'''

class Category2(page2.Page2, category.Category):

	def __init__(self, wiki, title=False, check=True, followRedir=False, section=False, sectionnumber=False, pageid=False):
		if not title.startswith("Category:"):
			raise NoCategoryTitle
		self.cattitle = title 
		page2.Page2.__init__(self, wiki=wiki, title=title, check=check, followRedir=followRedir, section=section, sectionnumber=sectionnumber, pageid=pageid)
		category.Category.__init__(self, site=wiki, title=re.sub("Category:", "", title), check=check, followRedir=followRedir, section=section, sectionnumber=sectionnumber, pageid=pageid)

	def demote(self, title=False, dropchilds=False, force=False, reason=False, watch=False, unwatch=False):
		''' Demote a category to a page

		newtitle - title of the page
		force - in case the page already exists, delete it
		'''
		if not self.exists:
			raise page.NoPage
		if title and title.startswith("Category:"):
			raise NotNonCategoryTitle		
		if self.getAllMembers() and not dropchilds:
			raise NonEmptyCategory
		if not title:
			title = self.title.split("Category:")[1]
		page = page2.Page2(self.site, title=title)
		if page.exists and not force:
			raise page2.AlreadyExists
		page.edit(text=self.getWikiText())	
		self.rewriteReferences(self.getBacklinks() + self.getAllMembers(titleonly=True), title)
		self.delete(reason=reason, watch=watch, unwatch=unwatch)

	def intermove(self, mvto, force=False, reason=False, movetalk=False, noredirect=False, watch=False, unwatch=False, dropchilds=False):
		''' Move category by deleting and creating a new page or demote the category to a page.
		Rename links on backlinking pages.

		force - see demote()
		other arguments - see python-wikitools documentation
		'''
		toCat = mvto.startswith("Category:")
		if not toCat:
			demote(mvto, force=force, reason=reason, watch=watch, unwatch=unwatch, dropchilds=dropchilds)
		else:
			mvtocat = Category2(self.site, title=mvto)
			if mvtocat.exists:
				if force:
					mvtocat.delete()
				else:	
					raise page2.AlreadyExists
			mvtocat.edit(text=self.getWikiText())
			self.rewriteReferences(self.getBacklinks() + self.getAllMembers(titleonly=True), mvto)
			self.delete(reason=reason, watch=watch, unwatch=unwatch)


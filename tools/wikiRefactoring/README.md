# Configuration

All you have to do to configure the tool for you wiki is to change the URL of the API in [refactor.py](https://github.com/101companies/101dev/edit/master/tools/wikiRefactoring/refactor.py)

# Flags

The overall set of possible flags is:
 * *dropchilds* - in case demotion is de
 * *force* - overwrite possibly existing target of moving/promotion/demotion
 * *noredirect* - do not create any redirections for deleted pages
 * *removePrefix* - in case of promotion remove any title prefix 

# Commands

## Renaming

One can move a page/category, promotion/demotion is detected:

    python refactor.py rename \<oldtitle\> \<newtitle\> [-- \<flags\>]
    
Allowed flags: *dropchilds*, *force*, *noredirect*, *removePrefix*
             
## Promotion

One can promote a page to become a category:

    python refactor.py promote \<pagetitle\> [\<categorytitle\>] [-- \<flags\>]

In case \<categorytitle\> is missing Category:\<pagetitle\> is used.
    
Allowed flags: *force*, *noredirect*, *removePrefix*
             
## Demotion

One can demote a category to become a page:

    python refactor.py demote \<categorytitle\> [\<pagetitle\>] [-- \<flags\>]

In case \<pagetitle\> is missing \<categorytitle\> without "Category:" is used.
    
Allowed flags: *force*, *noredirect*, *dropchilds*
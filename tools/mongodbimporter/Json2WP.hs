module Json2WP where

import Text.JSON
import Types
import Data.Ratio


json2WP (JSObject p) =
    WikiPage title ns sections $ 
      Meta cats (version, lrAuthor, lsTime) (cAuthor, cTime) 
      where 
        -- general matching       
        [  ("title", JSString titleJ) 
         , ("namespace", JSString nsJ) 
         , ("sections" ,JSArray sectionsJ) 
         , ("meta", JSObject metaJ) ]  = fromJSObject p
         
        -- title and namespace extraction
        title = fromJSString titleJ
        ns = fromJSString nsJ
            
        -- sections extraction
        sections = map getSec sectionsJ
        getSec (JSObject s) = (fromJSString tagJ, fromJSString contentJ)
          where
            [  ("tag", JSString tagJ)
             , ("content", JSString contentJ)] = fromJSObject s
             
        -- meta extraction
        [  ("categories", JSArray catsJ)
         , ("lastrev", JSObject lastrevJ)
         , ("creation", JSObject creationJ) ] = fromJSObject metaJ
         
        -- categories extraction
        cats = map getCat catsJ
        getCat (JSString j) = fromJSString j
        
        -- last revision (lastrev) extraction
        [  ("version", JSRational False versionJ)
         , ("author", JSString lrAuthorJ)
         , ("time", JSString lrTimeJ) ] = fromJSObject lastrevJ
        
        -- version and last revision author and time extraction  
        version = numerator versionJ
        lrAuthor = fromJSString lrAuthorJ
        lsTime = read $ fromJSString lrTimeJ
        
        -- creation abstraction 
        [  ("author", JSString cAuthorJ)
         , ("time", JSString cTimeJ) ] = fromJSObject creationJ
         
        -- creation author and time extraction
        cAuthor = fromJSString cAuthorJ
        cTime = read $ fromJSString cTimeJ

json _ = error "Invalid wikipage json data."
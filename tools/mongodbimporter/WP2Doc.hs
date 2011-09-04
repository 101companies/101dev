{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module WP2Doc where

import Database.MongoDB
import Types

wp2Doc :: WikiPage -> Document
wp2Doc (WikiPage title ns secs (Meta cats (version, lra, lrt) (ca, ct))) = 
  ["title" =: title, "ns" =: ns, "sections" =: secdocs, "meta" =: meta]
    where
      secdocs = map (\(t,c) -> ["tag" =: t, "content" =: c]) secs
      meta = ["categories" =: cats, "lastrev" =: lsDoc, "creation" =: cDoc]
      lsDoc = ["version" =: version, "author" =: lra, "time" =: lrt]
      cDoc = ["author" =: ca, "time" =: ct] 
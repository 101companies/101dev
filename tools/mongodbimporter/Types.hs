module Types where

import Data.Time

data WikiPage = WikiPage String String [Section] Meta
  deriving (Show, Read, Eq)
data Meta = Meta [String] LastRev Creation
  deriving (Show, Read, Eq)

type Section = (String, String)
type LastRev = (Integer, String, UTCTime)
type Creation = (String, UTCTime)
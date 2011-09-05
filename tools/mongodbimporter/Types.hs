module Types where

import Data.Time
import Database.MongoDB

-- wikipage types
data WikiPage = WikiPage String Namespace [Section] Meta
  deriving (Show, Read, Eq)
data Meta = Meta [String] LastRev Creation
  deriving (Show, Read, Eq)

type Section = (Tag, String)
type LastRev = (Integer, String, UTCTime)
type Creation = (String, UTCTime)

data Builder a = Builder {
                    namespace :: Namespace
                  , tags  :: [Tag]
                  , build :: Pipe -> IO a }              

type Tag = String
type Namespace = String 

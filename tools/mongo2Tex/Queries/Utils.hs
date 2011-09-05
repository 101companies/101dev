module Queries.Utils where

import Database.MongoDB

getString (String s) = unpack s
                                  
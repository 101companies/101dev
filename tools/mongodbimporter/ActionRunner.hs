{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module ActionRunner where

import Database.MongoDB

-- run a action
run :: Pipe -> Action IO a -> IO (Either Failure a)
run pipe act = access pipe master "wiki" act 
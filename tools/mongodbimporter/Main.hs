{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Main where

import Database.MongoDB
import Control.Monad
import Control.Monad.Trans (liftIO)
import Text.JSON
import Directory
import Types
import Json2WP
import WP2Doc

-- pipe to localhost
localpipe = runIOE $ connect (host "127.0.0.1")

-- run funtion
run act = do
  pipe <- localpipe
  access pipe master "wiki" act

-- pretty printing documents 
printDocs docs = liftIO $ mapM_ (print . exclude ["_id"]) docs

-- get latest contributions by author
getLatestContribs author ns = do
  run $ query >>= printDocs
    where
      query = rest =<< find (select ["meta.lastrev.author" =: author, "ns" =: ns]  "page") {project = ["title" =: 1]} 

-- get all JSON data from a dir 
getJSONs :: String -> IO [JSValue]
getJSONs datadir = do
  fsIO <- liftM (filterM doesFileExist) $
            liftM (map (\s -> datadir ++ s)) $ 
              getDirectoryContents  datadir
  contents <- liftM (mapM readFile) fsIO
  liftM (map (unpackResult.decode)) contents
    where
      unpackResult (Ok a) = a
      unpackResult (Error s) = error s

-- insert all JSON data      
main = do
  -- converting from .json files to mongoDB docs
  docs <- liftM (map (wp2Doc.json2WP)) (getJSONs "../jsongenerator/data/")
  -- connecting
  pipe <- localpipe
  -- deleting
  access pipe master "wiki" (delete (select [] "page"))
  -- inserting 
  mapM (ins pipe (length docs)) (zip docs [1..length docs])
  -- close connection
  close pipe
    where
      ins pipe m (d,n) = do
        putStrLn $ "Inserting " ++ show n ++ "/" ++ show m
        access pipe master "wiki" (insert "page" d)      
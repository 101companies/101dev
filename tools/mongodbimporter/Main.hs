{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Main where

import Database.MongoDB
import Control.Monad
import Control.Monad.Trans (liftIO)
import qualified Data.ByteString.Lazy as BS

import Text.JSON
import Directory
import qualified Data.Enumerator
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

--get all file names from a dir
getFileNames :: String -> IO [FilePath]
getFileNames datadir = do
	fsIO <- liftM (filterM doesFileExist) $
	            liftM (map (\s -> datadir ++ s)) $ 
	              getDirectoryContents  datadir
	fsIO

-- get all JSON data from a dir 
main :: IO ()
main = do
  let datadir = "../jsongenerator/data/"
  fsIO <- liftM (filterM doesFileExist) $
              liftM (map (\s -> datadir ++ s)) $ 
                getDirectoryContents  datadir
  pipe <- localpipe 
  access pipe master "wiki" (delete (select [] "page"))             
  fs <- fsIO       
  putStrLn $ show $length fs         
  mapM_ (toMongoDB pipe) fs
   
      
toMongoDB :: Pipe -> String -> IO ()
toMongoDB pipe s = do 
  c <- readFile s
  print $ "Converting .json " ++ s ++ " to Document..."
  let r = (wp2Doc.json2WP.unpackResult.decode) c
  print "Done. Inserting into MongoDB..."
  access pipe master "wiki" (insert "page" r)
  putStrLn "Done."
  return () 
     where
      unpackResult (Ok a) = a
      unpackResult (Error s) = error s
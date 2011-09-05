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
import ActionRunner
import Builder.CoverageBuilder

-- pipe to localhost
localpipe = runIOE $ connect (host "127.0.0.1")
 
--get all file names from a dir
getFileNames :: String -> IO [FilePath]
getFileNames datadir = do
	fsIO <- liftM (filterM doesFileExist) $
	            liftM (map (\s -> datadir ++ s)) $ 
	              getDirectoryContents datadir
	fsIO

-- get all JSON data from a dir 
main :: IO ()
main = do
  let datadir = "../jsongenerator/data/"
  fsIO <- liftM (filterM doesFileExist) $
              liftM (map (\s -> datadir ++ s)) $ 
                getDirectoryContents  datadir
  pipe <- localpipe 
  run pipe (delete (select [] "page"))             
  fs <- fsIO              
  mapM_ (jsonFileToMongoDB pipe "page") fs

populateCoverage :: IO ()
populateCoverage = do
  pipe <- localpipe   
  featuress <- build coverageBuilder pipe
  mapM_ (putStrLn.show) featuress   
 
-- convert json file to doc and insert into mongoDB     
jsonFileToMongoDB :: Pipe ->  Collection -> String -> IO ()
jsonFileToMongoDB pipe coll path = do 
  c <- readFile path
  print $ "Converting .json " ++ path ++ " to Document..."
  let r = (wp2Doc.json2WP.unpackResult.decode) c
  print "Done. Inserting into MongoDB..."
  run pipe (insert coll r)
  putStrLn "Done."
  return () 
     where
      unpackResult (Ok a) = a

      unpackResult (Error e) = error e
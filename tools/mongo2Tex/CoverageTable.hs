module CoverageTable where

import Control.Monad (liftM,forM_)
import Database.MongoDB

import Queries.Coverage
import Queries.Features

createCoverageTable = do
  pipe <- runIOE $ connect (host "127.0.0.1") 
  features <- liftM reverse $ queryFeatures pipe
  coverage <- liftM reverse $ queryCoverage pipe
  -- head
  putStrLn $ "\\begin{longtable}{|r" 
            ++ concat (replicate (length features) "|c") 
            ++ "|}\\hline"
  putStrLn $ "\\begin{sideways}Implementation\\end{sideways}"
  -- feature names
  forM_ features $ \feature -> do
    putStrLn $ "&\\begin{sideways}" ++ feature ++ "\\end{sideways}"
  
  putStrLn "\\hline"
  
  -- feature coverage  
  forM_ coverage $ \(title, implFs) -> do 
    let line = title ++ 
               " " ++ 
               (concat $ map (\f -> symbol $ elem f implFs) features)
    putStrLn $ line ++ "\\\\\\hline"
           
  putStrLn "\\hline"           
  putStrLn "\\end{longtable}"             

symbol :: Bool -> String
symbol True = "&$\\bullet$ "  
symbol False = "& "

module CoverageTable where

import Control.Monad (liftM,forM_)
import Data.List
import Database.MongoDB

import Queries.Coverage
import Queries.Features

main = do
  pipe <- runIOE $ connect (host "127.0.0.1") 
  features <- liftM reverse $ queryFeatures pipe
  coverageAll <- liftM reverse $ queryCoverage pipe
  let coverageU = filter (not.null.snd) coverageAll
  let coverage = filter (((flip $ elem) hsTitles).fst) coverageU     
  let covNum = [sum [if elem f fimpl then 1 else 0 | (_, fimpl)  <- coverage] | f <- features]
  
  
  -- head
  putStrLn $ "\\begin{longtable}{|r" 
            ++ concat (replicate (length features) "|c") 
            ++ "|}\\hline"
  putStrLn $ "\\caption[Feature Model Coverage]{Feature Model Coverage} \\\\"
  
  putStrLn $ "\\hline"
           ++ "\\multicolumn{1}{|c|}{\\textbf{\\begin{sideways}Implementation \\textcolor{blue}{" ++ show (length coverage) ++ "}  "++ "\\end{sideways}}} & " 
           ++ (concat $ intersperse " & " $ map 
                          (\(f,c) -> "\\multicolumn{1}{|c|}{\\textbf{\\begin{sideways}" ++ f ++ " \\textcolor{blue}{" ++ show c ++ "}"++ "\\end{sideways}}}")
                          (zip features covNum))
          ++ "\\\\ \\hline"  
  putStrLn "\\endfirsthead"
  putStrLn $ "\\multicolumn{" ++ (show $ length features) ++ "}{c}%"
  putStrLn "{{\\bfseries \\tablename\\ \\thetable{} -- continued from previous page}} \\\\" 
  
  putStrLn $ "\\hline"
           ++ "\\multicolumn{1}{|c|}{\\textbf{\\begin{sideways}Implementation \\textcolor{blue}{" ++ show (length coverage) ++ "}  "++ "\\end{sideways}}} & " 
           ++ (concat $ intersperse " & " $ map 
                          (\(f,c) -> "\\multicolumn{1}{|c|}{\\textbf{\\begin{sideways}" ++ f ++ " \\textcolor{blue}{" ++ show c ++ "}"++ "\\end{sideways}}}")
                          (zip features covNum))
          ++ "\\\\ \\hline"  
  putStrLn "\\endhead"
  
  putStrLn $ "\\hline \\multicolumn{"++  (show $ length features) ++ "}{|r|}{{Continued on next page}} \\\\ \\hline"
  putStrLn "\\endfoot" 
  
  putStrLn "\\hline \\hline"
  putStrLn "\\endlastfoot"
   
  -- feature coverage  
  forM_ coverage $ \(title, implFs) -> do 
    let line = title ++ 
               " " ++ 
               (concat $ map (\f -> symbol $ elem f implFs) features)
    putStrLn $ line ++ "\\\\\\hline"
                  
  putStrLn "\\end{longtable}"             

symbol :: Bool -> String
symbol True = "&$\\bullet$ "  
symbol False = "& "  

hsTitles = ["haskellLogger","haskellParser", "haskellConcurrent", "dph", "hdbc", "haskellDB", "hxt", "hxtPickler", "wxHaskell",	"haskellCGI",	"happstack"]
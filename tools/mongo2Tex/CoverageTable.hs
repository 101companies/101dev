module CoverageTable where

import Control.Monad (liftM,forM_)
import Data.List
import Database.MongoDB

import Queries.Coverage
import Queries.Features

main = do
  pipe <- runIOE $ connect (host "127.0.0.1") 
  --features <- liftM reverse $ queryFeatures pipe
  let features =     ["Company","Cut","Total","Access control","Concurrency"] 
                  ++ ["Distribution", "Fault tolerance", "Interaction", "Logging"]
                  ++ ["Mapping","Parallelism", "Parsing", "Persistence", "Serialization"]
                  ++ ["Visualization", "Depth", "Mentoring", "foo"]
  coverageAll <- liftM reverse $ queryCoverage pipe
  let coverageU = filter (not.null.snd) coverageAll
  let coverage = filter (((flip $ elem) hsTitles).fst) coverageU     
  let covNum = [sum [if elem f fimpl then 1 else 0 | (_, fimpl)  <- coverage] | f <- features]
  
  let baseURL = "http://101companies.org/index.php/"
  
  -- head
  putStrLn $ "\\tabcolsep 1mm"
  putStrLn $ "\\begin{longtable}"  
  putStrLn $ "{c|c|c|c||c|c|c|c|c|c|c|c|c|c|c|c||c|c|c|}\\hline"
  putStrLn $ "\\caption[Coverage of the feature model]{Coverage of the feature model} \\\\"
  putStrLn $ "\\cline{2-19} & "
           ++ " \\multicolumn{3}{c|}{\\textbf{\\href{"++ baseURL ++ "Category:101basics}{basics}}} & "
           ++ " \\multicolumn{12}{|c|}{\\textbf{\\href{"++ baseURL ++ "Category:101capabilities}{capabilities}}} & "
           ++ " \\multicolumn{3}{|c|}{\\textbf{\\href{"++ baseURL ++ "Category:101extras}{extras}}}"
           ++ "\\\\ \\cline{2-19}"
  putStrLn $ ""
           ++ " & " 
           ++ (concat $ intersperse " & " $ map 
                          (\(f,c) -> "\\textbf{\\begin{sideways}\\href{" ++ baseURL ++ "101feature:" ++ f ++ "}{" ++ f++ "}\\end{sideways}}")
                          (zip features covNum))
          ++ "\\\\ \\hline"  
  putStrLn "\\endfirsthead"
  putStrLn $ "\\multicolumn{" ++ (show $ length features) ++ "}{c}%"
  putStrLn "{{\\bfseries \\tablename\\ \\thetable{} -- continued from previous page}} \\\\" 
  
  putStrLn $ "\\hline"
           ++ " & " 
           ++ (concat $ intersperse " & " $ map 
                          (\(f,c) -> "\\multicolumn{1}{|c|}{\\textbf{\\begin{sideways}" ++ f ++  "\\end{sideways}}}")
                          (zip features covNum))
          ++ "\\\\ \\hline"  
  putStrLn "\\endhead"
  
  putStrLn $ "\\hline \\multicolumn{"++  (show $ length features) ++ "}{|r|}{{Continued on next page}} \\\\ \\hline"
  putStrLn "\\endfoot" 
  
  putStrLn "\\hline \\hline"
  putStrLn "\\endlastfoot"
   
  -- feature coverage  
  forM_ coverage $ \(title, implFs) -> do 
    let line = "\\multicolumn{1}{|r|}{\\hyperlink{impl" ++ title ++ "}{"++ title ++ "}}" ++    
               " " ++ 
               (concat $ map (\f -> symbol $ elem f implFs) features)
    putStrLn $ line ++ "\\\\\\hline"
  putStrLn "\\hline\\hline"   
  putStrLn "\\multicolumn{1}{|r|}{\\textbf{Coverage}}"
  putStrLn $ concat $
        map (\n ->  " &\\textbf{" ++ show n ++ "}") covNum
  
  putStrLn "\\\\\\hline" 
                              
  putStrLn "\\end{longtable}"             

symbol :: Bool -> String
symbol True = "&$\\bullet$ "  
symbol False = "& "  

hsTitles = ["haskellLogger","haskellParser", "haskellConcurrent", "dph", "hdbc", "haskellDB", "hxt", "hxtPickler", "wxHaskell",	"haskellCGI",	"happstack"]
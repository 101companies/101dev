import Data.List (isInfixOf, findIndex, elemIndex)  
import System.FilePath ((</>)) 
import System.Directory (doesFileExist, getDirectoryContents,createDirectoryIfMissing)
import Monad (filterM,when)
import Control.Monad (forM_)
import Text.Regex.Posix ((=~))
import Data.Char (isSpace, toUpper)
import Text.XML.HXT.Parser.XmlParsec
import Text.XML.HXT.XPath.XPathEval
import Text.XML.HXT.DOM.TypeDefs
import Data.Tree.NTree.TypeDefs


trim      :: String -> String
trim      = f . f
   where f = reverse . dropWhile isSpace

replaceStr :: String -> String -> String -> String
replaceStr [] old new = []
replaceStr str old new = loop str
  where
    loop [] = []
    loop str =
      let (prefix, rest) = splitAt n str
      in
        if old == prefix                
        then new ++ loop rest           
        else head str : loop (tail str) 
    n = length old

extractText :: NTree XNode -> String
extractText (NTree _ [NTree (XText tag) []]) = tag

companiesPath = "code/"

nameTo x name = concat [companiesPath, name </> x]
nameToReadmePath = nameTo "README"
nameToTagPath = nameTo "TAGS.xml"

trd (_,_,a) = a
snd3 (_,a,_) = a
fst3 (a,_,_) = a

getProjects :: IO [String]
getProjects = do
        dirContents <- getDirectoryContents companiesPath 
        f <- filterM (doesFileExist.nameToReadmePath) dirContents
        return $ map (\(h:t) -> (toUpper h):t) f


loadValidProjects :: [String] -> IO [(String,String,[String])]
loadValidProjects names = 
                    do contents <- mapM (readFile.nameToReadmePath) names
                       tagss <- mapM (loadTags) names
                       return $ filter (validate.snd3) $ zip3 names contents tagss 
                          where  validate s = (isInfixOf "== Summary ==" s) && 
                                              (isInfixOf "== Description ==" s) && 
                                              (isInfixOf "== Contributors ==" s)
                                              
loadTags :: String  -> IO [String]
loadTags name = do
                exists <- doesFileExist $ nameToTagPath name
                if (not $ exists) then (return []) else tags
                    where
                        tags = do
                            content <- readFile $ nameToTagPath name
                            let xmlTree = head $ xread content
                            let tagTrees = getXPath "//tag" xmlTree
                            return $ map (extractText) tagTrees
                                                                 


flipList ::  [(String, [String])] -> [(String,[String])]
flipList = foldr (\(p,cs) acc -> foldr (\c acc2 -> addE acc2 (p,c)) acc cs ) 
                 []
                    where
                      addE old (p,c) = case (findIndex ((==c).fst)) old of
                         Nothing -> (c,[p]):old                   
                         Just n  -> (take n old) 
                                    ++ [(fst $ old !! n, p : (snd $ old !! n))] 
                                    ++ (drop (n+1) old)
                
                
extractContributors :: String -> [String]
extractContributors = filter (not.null) . dropWhile (not.null)
                            .map (trim.trd.((=~"(\\* )") :: String -> (String,String,String))) 
                                  .lines 
                                       . trd
                                           .((=~"(== Contributors ==)") :: String -> (String,String,String))
                    
setLinks :: String -> [String] -> String
setLinks c contrs = foldr (\contr acc -> replaceStr acc contr ("[[Contributor:" ++ contr ++ "|]]")) c contrs 


writeMain :: [(String,String,[String])] -> IO ()
writeMain ps = writeFile "Main_Page.wiki" $
                unlines ( 
                "== List of [http://sourceforge.net/apps/mediawiki/developers/index.php?title=101companies 101companies] implementations =="  :
                (map (\(n,_,_) -> "* [[" ++ n ++ "]]") (reverse ps)))   

tagsToWiki :: [String] -> [String]
tagsToWiki [] = []
tagsToWiki tags =  [
                    "",
                    "{|  style=\"width: 100%; border:1px solid #AAA; text-align: center;\"",
                    "|\'\'\'Tags\'\'\'" ++ (init $ concat $ map (\t -> "|[[Tag:" ++ t ++ "|]]|") tags),
                    "|}"]

writeImpl :: (String, String,[String]) -> IO ()
writeImpl (name, content, tags) = writeFile ("implementations" </> name ++ ".wiki") text
        where
            text =  unlines ([
                    "__FORCETOC__This is an implementation in the [http://sourceforge.net/apps/mediawiki/developers/index.php?title=101companies 101companies] software corpus.",
                    content ]  ++ tagsToWiki tags)
                    
                    
writeContr :: (String, [String]) -> IO()
writeContr (contr, ps) = writeFile ("contributors" </> contr ++ ".wiki") text
        where
            text = unlines ([
					"== List of contributions by " ++ contr ++ " =="] ++
                    map (\p ->  "* [[" ++ p ++ "]]") (reverse ps)  ++ 
                    ["[[Category:Contributors | Contributors]]"])
                            
writeTags :: (String, [String]) -> IO()
writeTags (tag, ps) = writeFile ("tags" </> tag ++ ".wiki") text
        where
            text = unlines ([
					"== List of implementations tagged \"" ++ tag ++ "\" =="] ++
                    map (\p ->  "* [[" ++ p ++ "]]") (reverse ps)  ++ 
                    ["[[Category:Tags | Tag]]"])                               

                                   
main = do 
        projectsNames <- getProjects                                                                                         
        putStrLn $ "All projects with a README:\n" ++ show projectsNames ++ "\nValidating READMEs...\n"
        
        readmesWithContentsTags <- loadValidProjects projectsNames
        putStrLn $ "All projects with a valid README with Tags:\n"
        mapM_ (\(n, _, ts) -> putStrLn $ (show (n, ts)) ++ "\n") readmesWithContentsTags
        
        putStrLn "Writing main_page file..."
        putStrLn "Done.\n"
        writeMain readmesWithContentsTags
    
        let readmesWithContentsContrsTags = map (\(n,c, ts) -> (n, c, extractContributors c, ts)) readmesWithContentsTags
        
        putStrLn "Cosmetics and writing implementation files..." 		
        let readmesWithContentsTags = map (\(n, content, contr, tags) -> (n, setLinks content contr, tags)) readmesWithContentsContrsTags
	
        createDirectoryIfMissing False ("" </> "implementations")
        forM_ readmesWithContentsTags writeImpl
        putStrLn "Done.\n"
        
        putStrLn "Analyzing contributors..."    
        putStrLn "Projects with Contributors:\n"
        forM_  readmesWithContentsContrsTags (\(n,_,c,ts) -> putStrLn (show (n,c,ts) ++ "\n"))
        
        let contributorsWithProjects =  flipList (map (\(n, _, cs, _) -> (n, cs))readmesWithContentsContrsTags)
        putStrLn "Contributors with Projects:\n"
        forM_  contributorsWithProjects (\x -> putStrLn (show x ++ "\n"))
        
        putStrLn "Writing contributors files..."
        createDirectoryIfMissing False ("" </> "contributors")
        forM_ contributorsWithProjects writeContr
        putStrLn "Done\n"  
        
        let tagsWithProjects = flipList ((map (\(n, _, _, ts) -> (n, ts)) readmesWithContentsContrsTags))
        putStrLn "Tags with Projects:\n"
        forM_ tagsWithProjects (\x -> putStrLn (show x ++ "\n")) 
        
        putStrLn "Writing tags files..."
        createDirectoryIfMissing False ("" </> "tags")
        forM_ tagsWithProjects writeTags
        putStrLn "Done\n"                                            
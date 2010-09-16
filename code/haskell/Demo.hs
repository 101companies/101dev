module Demo where

import Company
import Total
import Cut
import Depth
import Sample

main 
 = do
      print $ company == read (show company)
      print $ total company
      print $ total (cut company)
      print $ depth company

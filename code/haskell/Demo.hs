module Demo where

import Company
import Total
import Cut
import Sample

main 
 = do
      print $ company == read (show company)
      print $ total company == 399747
      print $ total (cut company) == 199873.5

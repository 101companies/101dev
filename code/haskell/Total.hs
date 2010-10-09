module Total where

import Company
import Data.Generics

total :: Company -> Float
total = everything (+) (mkQ 0 id)

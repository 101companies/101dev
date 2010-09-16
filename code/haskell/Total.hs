module Total where

import Company
import Data.Generics

total :: Company -> Float
total = everything (+) (mkQ 0 getSalary)
 where
  getSalary (Salary s) = s

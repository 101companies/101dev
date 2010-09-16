module Cut where

import Company
import Data.Generics

cut :: Company -> Company
cut = everywhere (mkT modifySalary)
 where
  modifySalary (Salary s)  = Salary (s/2)

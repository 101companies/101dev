module Total where

import Company

total :: Company -> Float
total = sum . map totalDept . snd
 where
  totalDept :: Dept -> Float
  totalDept (Dept _ m sus) = sum (totalEmp m : map totalSu sus)
  totalEmp :: Employee -> Float
  totalEmp (Employee _ _ s) = s
  totalSu :: SubUnit -> Float
  totalSu (PU e) = totalEmp e
  totalSu (DU d) = totalDept d

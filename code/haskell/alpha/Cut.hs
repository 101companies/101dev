module Cut where

import Company

cut :: Company -> Company

cut (n,ds) = (n,map cutDept ds)
 where
  cutDept :: Dept -> Dept
  cutDept (Dept n m sus) = Dept n (cutEmp m) (map cutSu sus)
  cutEmp :: Employee -> Employee
  cutEmp (Employee n a s) = Employee n a (s/2)  
  cutSu :: SubUnit -> SubUnit
  cutSu (PU e) = PU (cutEmp e)
  cutSu (DU d) = DU (cutDept d)

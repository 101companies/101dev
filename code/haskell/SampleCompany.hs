module SampleCompany where

import Company

company = [research, dev]
 where
  research = Dept "Research" (Manager craig) [PU erik, PU ralf]
   where
    craig = Employee (Person "Craig" "Redmond") (Salary 123456)
    erik  = Employee (Person "Erik" "Utrecht") (Salary 12345)
    ralf  = Employee (Person "Ralf" "Koblenz") (Salary 1234)
  dev = Dept "Development" (Manager ray) [DU dev1]
   where
    ray  = Employee (Person "Ray" "Redmond") (Salary 234567)
    dev1 = Dept "Dev1" (Manager klaus) [DU dev11]
     where
      klaus = Employee (Person "Klaus" "Boston") (Salary 23456)
      dev11 = Dept "Dev1.1" (Manager karl) [PU joe]
       where
        karl = Employee (Person "Karl" "Riga") (Salary 2345)
        joe  = Employee (Person "Joe" "Wifi City") (Salary 2344)

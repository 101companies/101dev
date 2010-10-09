module SampleCompany where

import Company

company = [research, dev]
 where
  research = Dept "Research" craig [PU erik, PU ralf]
   where
    craig = Employee "Craig" "Redmond" 123456
    erik  = Employee "Erik" "Utrecht" 12345
    ralf  = Employee "Ralf" "Koblenz" 1234
  dev = Dept "Development" ray [DU dev1]
   where
    ray  = Employee "Ray" "Redmond" 234567
    dev1 = Dept "Dev1" klaus [DU dev11]
     where
      klaus = Employee "Klaus" "Boston" 23456
      dev11 = Dept "Dev1.1" karl [PU joe]
       where
        karl = Employee "Karl" "Riga" 2345
        joe  = Employee "Joe" "Wifi City" 2344

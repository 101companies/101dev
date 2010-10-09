module Cut where

import Company
import Data.Generics

cut :: Company -> Company
cut = everywhere (mkT (/(2::Float)))

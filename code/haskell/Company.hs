{-# LANGUAGE DeriveDataTypeable #-}

module Company where

import Data.Typeable
import Data.Data

type Company = [Dept]
data Dept = Dept Name Manager [SubUnit]
 deriving (Eq, Read, Show, Typeable, Data)
newtype Manager = Manager Employee
 deriving (Eq, Read, Show, Typeable, Data)
data Employee = Employee Person Salary
 deriving (Eq, Read, Show, Typeable, Data)
data Person = Person Name Address
 deriving (Eq, Read, Show, Typeable, Data)
type Name = String
type Address = String
newtype Salary = Salary Float
 deriving (Eq, Read, Show, Typeable, Data)
data SubUnit = PU Employee | DU Dept
 deriving (Eq, Read, Show, Typeable, Data)

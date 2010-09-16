total([],0).

total([H|T],R)
 :-
    total(H,R1),
    total(T,R2),
    R is R1 + R2.
 
 total(dept(_,manager(_,_,S),Units),R)
  :-
    total(Units,R1),
    R is S + R1.
 
 total(employee(_,_,S),S).
 
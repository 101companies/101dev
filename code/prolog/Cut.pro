cut(N1,N2) :-
	number(N1), N2 is N1 / 2.
    
cut([],[]).
cut([H1|T1],[H2|T2]) :-
	cut(H1,H2), cut(T1,T2).
 
cut( dept(X,manager(Y,Z,S1),Units1),
	 dept(X,manager(Y,Z,S2),Units2))
 :-
	cut(S1,S2), cut(Units1,Units2).
 
cut( employee(X,Y,S1),
	 employee(X,Y,S2))
 :-
	cut(S1,S2).
lexer grammar Structo;
@header {
package structo.processor.acceptor;
@SuppressWarnings({"unused", "cast"})
}

T8 : '{' ;
T9 : '}' ;
T10 : 'int' ;
T11 : ';' ;
T12 : 'read' ;
T13 : 'write' ;
T14 : '=' ;
T15 : 'if' ;
T16 : 'else' ;
T17 : 'while' ;
T18 : '(' ;
T19 : ')' ;
T20 : '==' ;
T21 : '!=' ;
T22 : '<' ;
T23 : '>' ;
T24 : '<=' ;
T25 : '>=' ;
T26 : '+' ;
T27 : '-' ;
T28 : '*' ;
T29 : '/' ;

// $ANTLR src "Structo.g" 47
ID  	:   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;
// $ANTLR src "Structo.g" 48
INT 	:   '0'..'9'+ ;
// $ANTLR src "Structo.g" 49
WS  	:   (' '|'\t'|'\n'|'\r')+ 	{ skip(); };
// $ANTLR src "Structo.g" 50
COMMENT :   '//' (~('\n'|'\r'))*	{ skip(); };

// (C) 2009 Ralf Laemmel

grammar Structo;

@header {
package structo.processor.acceptor;
@SuppressWarnings({"unused", "cast"})
}
@lexer::header {
package structo.processor.acceptor;
@SuppressWarnings({"unused", "cast"})
}

program: statements;

statements : statement | '{' statement* '}';
                
statement:   
		'int' ID ';'
	|	'read' ID ';'
	|	'write' aexpression ';'
	|   ID '=' aexpression ';'
	|	'if' cexpression statements ('else' statements)?
	|   'while' cexpression statements
	;


// Comparison expressions

cexpression : '(' aexpression rop aexpression ')';


// Relational operators

rop : '==' | '!=' | '<' | '>' | '<=' | '>=';


// Arithmetic expressions

aexpression : term (( '+' | '-' ) term)*;
term 		: factor (( '*' | '/') factor)*;
factor 		: ID | INT | '(' aexpression ')';


// Lexical categries

ID  	:   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;
INT 	:   '0'..'9'+ ;
WS  	:   (' '|'\t'|'\n'|'\r')+ 	{ skip(); };
COMMENT :   '//' (~('\n'|'\r'))*	{ skip(); };

// (C) 2009 Ralf Laemmel

grammar Structo;

@header {
package structo.processor.visitor;
import structo.types.visitor.*;
}
@lexer::header {
package structo.processor.visitor;
}

program returns [ Statement value ]
	:
		statements
		{ $value = $statements.value; }
	;

statements returns [ Statement value ]
	: 
		statement
		{ $value = $statement.value; }	
	|
		{ 
			Sequence s = new Sequence(); 
			$value = s;
		}
	 	'{' ( 	statement
				{ s.getStatements().add($statement.value); }
			)* 
		'}'
	;
                
statement returns [ Statement value ]
	:
		'int' 
		ID 
		';'
		{ $value = new Declare($ID.text); }
	|	
		'read' 
		ID 
		';'
		{ $value = new Read($ID.text); }
	|	
		'write'
		aexpression 
		';'
		{ $value = new Write($aexpression.value); }
	|   
		ID 
		'=' 
		aexpression 
		';'
		{ $value = new Assign($ID.text, $aexpression.value); }
	|	
		{ Statement s = new Skip(); }
		'if' 
		cond = cexpression
		thenBranch = statements 
		('else' elseBranch = statements { s = $elseBranch.value; })?
		{ $value = new If($cond.value, $thenBranch.value, s); }
	|   
		'while'
		cond = cexpression
		statements
		{ $value = new While($cond.value, $statements.value); }
	;


// Comparison expressions

cexpression returns [ Expression value ]
	: 
		'(' 
		left = aexpression 
		rop 
		right = aexpression 
		')'
		{ $value = new Binary($rop.value, $left.value, $right.value); }
	;


// Relational operators

rop returns [ Op value ]
	: 	
		'=='
		{ $value = Op.Equal; }
	|
	 	'!=' 
		{ $value = Op.NotEqual; }
	 |
	 	'<' 
		{ $value = Op.Below; }
	 |
	 	'>'
		{ $value = Op.Greater; }
	 |
	 	'<=' 
		{ $value = Op.BelowEq; }
	 |
	 	'>='
		{ $value = Op.GreaterEq; }
	 ;


// Arithmetic expressions

aexpression returns [ Expression value ]
	: 
		left = term 
		{ $value = $left.value; }
		(
			{ Op op = null; }
			(
				'+' 
				{ op = Op.Plus; } 
			| 
				'-' 
				{ op = Op.Minus; } 
			)
			right = term
			{ $value = new Binary(op,$value,$right.value); }
		)*
	;
	
term returns [ Expression value ]		
	: 
		left = factor 
		{ $value = $left.value; }
		(	
			{ Op op = null; }
			(	
				'*'
				{ op = Op.Times; } 
			| 	
				'/'
				{ op = Op.Div; } 
			)
			right = factor
			{ $value = new Binary(op,$value,$right.value); }
		)*
	;
	
factor returns [ Expression value ]
	: 
		ID 
		{ $value = new Id($ID.text); }		
	|
	 	INT 
		{ $value = new Int(Integer.parseInt($INT.text)); }		
	| 
		'(' aexpression ')'
		{ $value = $aexpression.value; }
	;


// Lexical categries

ID  	:   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;
INT 	:   '0'..'9'+ ;
WS  	:   (' '|'\t'|'\n'|'\r')+ 	{ skip(); };
COMMENT :   '//' (~('\n'|'\r'))*	{ skip(); };

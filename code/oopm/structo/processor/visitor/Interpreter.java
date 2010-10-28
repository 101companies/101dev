// (C) 2009 Ralf Laemmel

package structo.processor.visitor;

import java.util.HashMap;
import java.util.Scanner;
import structo.types.visitor.*;

/**
 * An interpreter for Structo
 */
public class Interpreter implements Visitor {

	// The scanner needed for the read statement
	public Scanner scanner;
	
	// The memory, i.e., variable assignment
	private HashMap<String, Integer> memory = new HashMap<String, Integer>();

	// A helper field for the result of expression evaluation
	public int result;
		
	public void visit(Int that) {
		result = that.getValue();
	}

	public void visit(Id that) {
		result = memory.get(that.getValue());
	}

	public void visit(Binary that) {
		that.getLeft().accept(this);
		int v1 = result;
		that.getRight().accept(this);
		int v2 = result;
		switch (that.getOp()) {
		case Plus : result = v1 + v2; break;
		case Minus : result = v1 - v2; break;
		case Times : result = v1 * v2; break;
		case Div : result = v1 / v2; break;
		case Equal : result = v1 == v2 ? 1 : 0; break;
		case NotEqual : result = v1 == v2 ? 0 : 1; break;
		case Below : result = v1 < v2 ? 1 : 0; break;
		case Greater : result = v1 > v2 ? 1 : 0; break;
		case BelowEq : result = v1 <= v2 ? 1 : 0; break;
		case GreaterEq : result = v1 >= v2 ? 1 : 0; break;
		default : throw new RuntimeException();
		}		
	}

	public void visit(Skip that) {
	}

	public void visit(Sequence that) {
		for (Statement s : that.getStatements())
			s.accept(this);		
	}

	public void visit(Declare that) {		
	}

	public void visit(Assign that) {
		that.getRhs().accept(this);
		memory.put(that.getLhs(), result);		
	}

	public void visit(If that) {
		that.getCond().accept(this);
		if (result!=0)
			that.getThenBranch().accept(this);
		else
			that.getElseBranch().accept(this);
	}

	public void visit(While that) {
		do {
			that.getCond().accept(this);
			if (result==0) break;
			that.getBody().accept(this);
		} while (true);
	}

	public void visit(Read that) {
		memory.put(that.getId(), scanner.nextInt());		
	}

	public void visit(Write that) {
		that.getExpr().accept(this);
		System.out.println(result);		
	}
	
}

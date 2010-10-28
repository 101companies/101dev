// (C) 2009 Ralf Laemmel

package structo.processor.visitor;

import java.util.HashSet;
import structo.types.visitor.*;

/**
 * A static checker for Structo.
 * Check that a variable is declared before it is ever referenced.
 * Check that a variable has been (definitely) initialized before it is read.
 */
public class Checker implements Visitor {

	// Maintain variables that were declared or initialized
	private HashSet<String> declared = new HashSet<String>();
	private HashSet<String> initialized = new HashSet<String>();
		
	public void visit(Int that) {
	}

	public void visit(Id that) {
		assert initialized.contains(that.getValue());
	}

	public void visit(Binary that) {
		that.getLeft().accept(this);
		that.getRight().accept(this);
	}

	public void visit(Skip that) {
	}

	public void visit(Sequence that) {
		for (Statement s : that.getStatements())
			s.accept(this);		
	}

	public void visit(Declare that) {
		assert declared.add(that.getId());
	}

	public void visit(Assign that) {
		that.getRhs().accept(this);
		assert declared.contains(that.getLhs());		
		initialized.add(that.getLhs());
	}

	public void visit(If that) {
		that.getCond().accept(this);
		
		// Prepare collections for the branches
		HashSet<String> declaredBackup = declared;
		HashSet<String> initializedBackup = initialized;
		
		// Analyze then
		declared = new HashSet<String>();
		declared.addAll(declaredBackup);
		initialized = new HashSet<String>();
		initialized.addAll(initializedBackup);
		that.getThenBranch().accept(this);
		HashSet<String> initializedThen = initialized;
		
		// Analyze then
		declared = new HashSet<String>();
		declared.addAll(declaredBackup);
		initialized = new HashSet<String>();
		initialized.addAll(initializedBackup);
		that.getElseBranch().accept(this);		
		
		// Restore and combine
		declared = declaredBackup;
		initialized.retainAll(initializedThen);

	}

	public void visit(While that) {
		that.getCond().accept(this);
		HashSet<String> declaredBackup = declared;
		HashSet<String> initializedBackup = initialized;
		declared = new HashSet<String>();
		declared.addAll(declaredBackup);
		initialized = new HashSet<String>();
		initialized.addAll(initializedBackup);
		that.getBody().accept(this);
		declared = declaredBackup;
		initialized = initializedBackup;
	}

	public void visit(Read that) {
		assert declared.contains(that.getId());		
		initialized.add(that.getId());
	}

	public void visit(Write that) {
		that.getExpr().accept(this);
	}
	
}

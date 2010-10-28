// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * The base class for all company objects.
 * In particular, we anticipate the accept method for the visitor pattern.
 */
public abstract class Base {

	public abstract void accept(Visitor v);
	
}

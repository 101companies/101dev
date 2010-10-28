// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public interface Visitor {

	public void visit(Int that);
	public void visit(Id that);
	public void visit(Binary that);
	public void visit(Skip that);
	public void visit(Sequence that);
	public void visit(Declare that);
	public void visit(Assign that);
	public void visit(If that);
	public void visit(While that);
	public void visit(Read that);
	public void visit(Write that);
	
}

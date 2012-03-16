package mega.test.JUnit.tracer;


public class VarTestTracer extends TestTracer {

	@Override
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return true;}
}

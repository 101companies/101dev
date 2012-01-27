package mega.test.data;


public class VarTestTracer extends TestTracer {

	@Override
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return true;}


}

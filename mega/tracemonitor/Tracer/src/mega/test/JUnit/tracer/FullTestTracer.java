package mega.test.JUnit.tracer;


public class FullTestTracer extends TestTracer {
	
	@Override
	public boolean traceClass(String name) {
		return (name.startsWith("org/softlang/"));
	}

//enable full code injection
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return true;}
	
	public boolean injectBeforeMethodCall(String classname,String methodname) {return true;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return true;}
	
	public boolean injectBeforeConstructorCall(String classname) {return true;}
	public boolean injectAfterConstructorCall(String classname) {return true;}
	
	public boolean injectBeforeInterfaceCall(String classname,String methodname) {return true;}
	public boolean injectAfterInterfaceCall(String classname,String methodname) {return true;}
	
}

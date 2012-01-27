package mega.test.data;


public class FullTestTracer extends TestTracer {

//enable full code injection
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return true;}
	
	public boolean injectBeforeMethodCall(String classname,String methodname) {return true;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return true;}
	
	public boolean injectBeforeConstructorCall(String classname) {return true;}
	public boolean injectAfterConstructorCall(String classname) {return true;}
	
	public boolean injectBeforeInterfaceCall(String classname,String methodname) {return true;}
	public boolean injectAfterInterfaceCall(String classname,String methodname) {return true;}
	
}

package mega.test.JUnit.tracer;


public class MethodTestTracer extends TestTracer {
	public boolean ignoreClass(String pname){return false;}
	
//enable code injection for method calls.
	public boolean injectBeforeMethodCall(String classname,String methodname) {return true;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return true;}

}

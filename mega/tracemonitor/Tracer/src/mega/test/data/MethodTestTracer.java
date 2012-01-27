package mega.test.data;


public class MethodTestTracer extends TestTracer {

//enable code injection for method calls.
	public boolean injectBeforeMethodCall(String classname,String methodname) {return true;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return true;}

}

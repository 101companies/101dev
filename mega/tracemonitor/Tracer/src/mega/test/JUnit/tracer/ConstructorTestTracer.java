package mega.test.JUnit.tracer;


public class ConstructorTestTracer extends TestTracer {

	public boolean injectBeforeConstructorCall(String classname) {return true;}
	public boolean injectAfterConstructorCall(String classname) {return true;}

}

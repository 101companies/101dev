package mega.test;

import java.lang.reflect.InvocationTargetException;
import mega.trace.core.Tracer;

public class TestCommon {

	private static Class<?> tracedClass;

	protected void setUpTracer(Tracer tracer) throws Exception {

		
		tracedClass = tracer.loadClass("mega.test.data.SampleClass", true);
		

	}
	
	protected void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		System.out.println("Executing...");
		tracedClass.getMethod("main").invoke(0);
		System.out.println("\nDone executing.");
	}


	

}

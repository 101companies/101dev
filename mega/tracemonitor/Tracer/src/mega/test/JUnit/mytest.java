package mega.test.JUnit;

import java.lang.reflect.InvocationTargetException;

import mega.test.JAXB.JAXBTestTracer;
import mega.test.JUnit.tracer.FullTestTracer;
import mega.trace.core.TraceConfiguration;


public class mytest {
	private static Class<?> tracedClass;
	private static JAXBTestTracer tracer; 
	public static void main(String args[]){
		
		tracer=new JAXBTestTracer();
		TraceConfiguration.registerTracer(tracer);
		
		try {
			tracedClass = TraceConfiguration.loadClass("mega.test.data.SampleClass2", true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tracedClass.getMethod("go").invoke(0);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

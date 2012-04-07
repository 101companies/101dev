package mega.trace.core;

import java.util.LinkedList;

import mega.test.JAXB.JAXBTestTracer;

/*
 * Just register all tracer below
 * 
 * Currently mega.test.JAXB.JAXBTestTracer is used 
 * 
 * 
 */



public class TraceConfiguration {
	
	private static LinkedList<Tracer> tracerlist= new LinkedList<Tracer>();
	private static TraceClassLoader classloader= new TraceClassLoader(TraceConfiguration.class.getClassLoader());

	
	static{
		
		/*
		 * TRACE CONFIGURATION GOES HERE
		 *
		
			registerTracer(new MyTracer());
			...
			
		*/
		
		registerTracer(new JAXBTestTracer());
		
		
	}

	public static void registerTracer(Tracer t){
		tracerlist.add(t);
	}
	
	public static LinkedList<Tracer> getTracerList(){
		return tracerlist;
	}
	
	public static synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException
	{
		
		return classloader.loadClass(name,resolve);

	}
	
	public static boolean prohibitedPackage(String name){

		return (name.replace('.','/').startsWith("java/") || name.replace('.','/').startsWith("javax/") ||name.replace('.','/').startsWith("sun/") || name.replace('.','/').startsWith("mega/trace/"));
	}

}

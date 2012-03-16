package mega.test.JAXB;


/*
 * See mega.test.core.TraceConfiguration
 *  * 
 */



import java.util.LinkedList;

import mega.trace.core.Tracer;
import mega.trace.event.TraceEvent;

public class JAXBTestTracer extends Tracer{
	
	LinkedList<TraceEvent> trace;
	LinkedList<LinkedList<Object>> tracestack;
	LinkedList<Object[]> tracecall;
	LinkedList<String[]> tracecallclass;
	
	JAXBMapper mapper;
	
	public JAXBTestTracer(){
		super();
		trace=new LinkedList<TraceEvent>();	
		tracecall=new LinkedList<Object[]>();
		tracecallclass=new LinkedList<String[]>();
		tracestack=new LinkedList<LinkedList<Object>>();	
		
		mapper=new JAXBMapper(this);
		
		Runtime.getRuntime().addShutdownHook(mapper);
		
	}
	
	public boolean traceClass(String name) {
		return (name.startsWith("org/softlang/"));
	}
	
	/*inject everywhere to get all events.. could be optimized*/
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return true;}
	public boolean injectBeforeMethodCall(String classname,String methodname) {return true;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return true;}
	public boolean injectBeforeConstructorCall(String classname) {return true;}
	public boolean injectAfterConstructorCall(String classname) {return true;}
	public boolean injectBeforeInterfaceCall(String classname,String methodname) {return false;}
	public boolean injectAfterInterfaceCall(String classname,String methodname) {return false;}
	
	

	public LinkedList<TraceEvent> getEvents() {return trace;}
	public LinkedList<LinkedList<Object>> getStacks() {return tracestack;}
	
	public LinkedList<Object[]> getCalls() {return tracecall;}
	public LinkedList<String[]> getCallClasses() {return tracecallclass;}
	
	@Override
	public void anyEvent(TraceEvent e){
	
		//this method of storing events is not nice because it prevents the garbage collector from deleting any traced object.

		
		trace.addLast(e);	
		tracestack.addLast(cloneStack());
		
		Object[] o={getCaller(),getCallee()};
		String[] s={getCallerClassname(),getCalleeClassname()};		
		
		tracecall.addLast(o);
		tracecallclass.addLast(s);
	}
	
}

package mega.test.JUnit.tracer;

import java.util.LinkedList;
import mega.trace.core.Tracer;
import mega.trace.event.*;

public class TestTracer extends Tracer{	
	LinkedList<TraceEvent> trace;
	LinkedList<LinkedList<Object>> tracestack;
	LinkedList<Object[]> tracecall;
	LinkedList<String[]> tracecallclass;
	
	public TestTracer(){
		super();
		trace=new LinkedList<TraceEvent>();	
		tracecall=new LinkedList<Object[]>();
		tracecallclass=new LinkedList<String[]>();
		tracestack=new LinkedList<LinkedList<Object>>();	
	}
	
	public boolean traceClass(String name) {
		return (name.equals("mega.test.JUnit.SampleClass"));
	}
	
	public LinkedList<TraceEvent> getEvents() {return trace;}
	public LinkedList<LinkedList<Object>> getStacks() {return tracestack;}
	
	public LinkedList<Object[]> getCalls() {return tracecall;}
	public LinkedList<String[]> getCallClasses() {return tracecallclass;}
	
	@Override
	public void anyEvent(TraceEvent e){
	
		//this method of storing events isn't nice because it prevents the garbage collector from deleting any traced object.
		//please see mega.trace.core.Tracer for more detail
		
		trace.addLast(e);	
		tracestack.addLast(cloneStack());
		
		Object[] o={getCaller(),getCallee()};
		String[] s={getCallerClassname(),getCalleeClassname()};		
		
		tracecall.addLast(o);
		tracecallclass.addLast(s);
	
		
//===========================================		
//==== Everything below is just for fun! ====
//===========================================
		
		
			
		System.out.println("\nTrace Event.");
		System.out.println("Eventclass: "+e.getClass().getName());
		
		
		
		int i=getStackSize();
		System.out.println("Printing stack(#"+i+"):");
		
		
		pstack("\t");
		
		pcall();
		
		System.out.println("Additional Information of this event(if available):");

	}
	
	@Override
	public void beforeConstructorCallEvent(BeforeConstructorCallEvent e){
		System.out.println("\tcalled before a constructor method.");
		displmethod(e);
	}
	@Override
	public void afterConstructorCallEvent(AfterConstructorCallEvent e){
		System.out.println("\tcalled after a constructor method.");
		displmethod(e);
	}	
	@Override
	public void beforeMethodCallEvent(BeforeMethodCallEvent e){
		System.out.println("\tcalled before a method.");
		displmethod(e);
	
	}
	@Override
	public void afterMethodCallEvent(AfterMethodCallEvent e){
		System.out.println("\tcalled after a method.");
		displmethod(e);
	
	}
	
	@Override
	public void variableAssignmentEvent(VariableAssignmentEvent e){
	
	}
	
	@Override
	public void memberVariableAssignmentEvent(MemberVariableAssignmentEvent e){
		System.out.println("\tassignment: member field");
		System.out.println("\tfield name :"+e.getName());
		System.out.println("\tstatic field:"+e.getStatic());
		displvar(e);
	}	
	@Override
	public void localVariableAssignmentEvent(LocalVariableAssignmentEvent e){
		System.out.println("\tassignment: local var");
		System.out.println("\tindex:"+e.getName());
		displvar(e);
	}	
	
	private void displvar(VariableAssignmentEvent e){
		System.out.println("\tsignature:"+e.getSignature());
		System.out.println("\tprinting assignment stack:");
		pstack("\t\t");
		
	}
	
	private void displmethod(MethodCallEvent e){
		System.out.println("\tMethod:"+e.getMethodName());
		System.out.println("\tOwner:"+e.getOwner());
		System.out.println("\tDescript:"+e.getDesc());
		System.out.println("\tstatic:"+e.isStatic());
		
	}
	
	private void pstack(String pre){
		int j;
		for(j=0;j<getStackSize();j++)
			System.out.println(pre+"instance of class: "+getStackValue(j).getClass().getName());
	}
	
	private void pcall(){
		Object o=getCallee();
		System.out.println("Callee class:"+getCalleeClassname());
		
		System.out.println("Callee reference set:"+(o!=null));
		
		
		o=getCaller();
		System.out.println("Caller class:"+getCallerClassname());
		System.out.println("Caller reference set:"+(o!=null));
	}
}

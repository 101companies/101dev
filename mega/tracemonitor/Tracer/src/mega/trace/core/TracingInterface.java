package mega.trace.core;

import java.util.LinkedList;

import mega.trace.event.*;


public interface TracingInterface {
	
	/*Callbacks*/
	
	
	public void anyEvent(TraceEvent e);
	
	//super: TraceEvent
	public void variableAssignmentEvent(VariableAssignmentEvent e);

	//super: TraceEvent,VariableAssignmentEvent
	public void memberVariableAssignmentEvent(MemberVariableAssignmentEvent e);
	public void localVariableAssignmentEvent(LocalVariableAssignmentEvent e);
		
	//super: TraceEvent,MethodCallEvent
	public void beforeMethodCallEvent(BeforeMethodCallEvent e) ;
	public void afterMethodCallEvent(AfterMethodCallEvent e) ;
		
	//super: TraceEvent,ConstructorCallEvent
	public void beforeConstructorCallEvent(BeforeConstructorCallEvent e);
	public void afterConstructorCallEvent(AfterConstructorCallEvent e);
	

	/*
	You must ONLY call the following methods to obtain information in addition to events/event methods.
	These values are only set if they are available and may be null/empty.
	The methods must be called before the next event. The stacklist values are "popped" back to the stack of
	the class/class instance which caused the event so DO NOT manipulate any object in the stacklist or
	the list itself.
	The type of the arguments on stack is available through the getMethodDesc() method of those events 
	who support the stack.
	Please keep in mind that keeping a reference to an object prevents the garbage collector to free
	this object!
	*/
	
	/*returns a reference to the object whose method/constructor/... was called.
	  may be null for various reasons including if the target method is static*/
	public Object getCallee();
	public Object getCalleeClassname();
	//returns a reference to the object which called the method/...
	//may also be null
	public Object getCaller();
	public Object getCallerClassname();
	
	
	public Object getStackValue(int index);
	public int getStackSize();
	public LinkedList<Object> cloneStack();
	
	

}

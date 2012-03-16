package mega.trace.core;

/* 
 * Just for testing, without any optimization!
 * Extend Tracer class to implement your own tracer
 * Take a look at TracingInterface to see which callbacks are available.
 * Implement those you like. Then change options for code injection because of performance:
 * 
 * (1) Enable code injection of wanted callbacks by overloading inject* methods. At default, no class will
 *     be modified/traced!
 * (2) If you only use one Tracer call setLinkMethod(TracerLink.TLINK_STATIC); (much faster than default).
 * (3) Then instantiate your Tracer. Do not forget to call super()!
 * 
 * After that there are several ways to (load and) transform classes:
 * (1) At runtime via yourTracerInstance.loadClass(CLASS) - transforms, loads and returns class CLASS
 * (2) using java agent. See mega.agent.* 
 * (3) replace java's default classloader(also by passing an extra argument to the JVM). NOT IMPLEMENTED YET 
 *
 * Please note that (nearly) every trace event holds a reference to its corresponding object thus preventing
 * the garbage collector to free the object while the reference exists! 
 * 
 * The Tracer Class also offers Caller and Callee methods where caller returns the object/class which caused the event and
 * callee returns the object/class that was affected by the event.
 *   
 *   Speed:
 *   Optimization is, of course, possible. This is just a test.
 *   
 *   Other ideas:
 *   Maybe allow to keep stack when popping to provide argument support for After*Call events? 
 *   Would be possible to also include WithinMethodCallEvent, WithinConstructorCallEvent,.. which gets created
 *   whenever a method gets entered.
 *   
 *   TODO: currently missing type information of local vars. Can be achieved by checking visitLocalVariable()
 *   but this is called after visitMethod... => apply two MethodVisitors?
*/



import java.util.HashMap;
import java.util.LinkedList;


import mega.trace.event.*;

import mega.trace.transformation.ClassBytecodeTransformer;



public abstract class Tracer implements TracingInterface{
	
	
	
	
	
	public static final HashMap<Integer,Tracer> map=new HashMap<Integer,Tracer>();
	protected static int uidcount=0;
	protected int uid=0;

	//Argument stack
	protected LinkedList<Object> stack;
	//current object
	private Object currentRef;
	private Object currentSource;	
	private String currentRefClass;
	private String currentSourceClass;		

	public Tracer(){
		uidcount++;
		uid			=	uidcount;
		stack		=	new LinkedList<Object>();
		currentRef	=	null;
		map.put(new Integer(uid), this);
	}
	
	
	
	
	//=================================================================================================
	//You may overload the following methods:
	
	//exclude classes from tracing. false=do not trace at all
	public abstract boolean traceClass(String name);
	
	//Trace variable assignments? If the member flag is set it is a member field and it may be static when isStatic is set.
	//if the member flag is not set it is a local variable and isStatic is always false, too.
	public boolean injectVariableCall(String varname,boolean member,boolean isStatic) {return false;}
	
	public boolean injectBeforeMethodCall(String classname,String methodname) {return false;}
	public boolean injectAfterMethodCall(String classname,String methodname) {return false;}
	
	public boolean injectBeforeConstructorCall(String classname) {return false;}
	public boolean injectAfterConstructorCall(String classname) {return false;}
	
	public boolean injectBeforeInterfaceCall(String classname,String methodname) {return false;}
	public boolean injectAfterInterfaceCall(String classname,String methodname) {return false;}
	
	//Callback:
	
	public void anyEvent(TraceEvent e){}
	public void variableAssignmentEvent(VariableAssignmentEvent e){}
	public void memberVariableAssignmentEvent(MemberVariableAssignmentEvent e){}
	public void localVariableAssignmentEvent(LocalVariableAssignmentEvent e){}
	
	public void beforeMethodCallEvent(BeforeMethodCallEvent e) {}
	public void afterMethodCallEvent(AfterMethodCallEvent e) {}
	public void beforeConstructorCallEvent(BeforeConstructorCallEvent e){}
	public void afterConstructorCallEvent(AfterConstructorCallEvent e){}
	
	
	//=================================================================================================
	
	public boolean ignoreClass(String pname){
	/*//pname.startsWith("javax/")/*
		if(pname.startsWith("java/") || pname.startsWith("javax/") || pname.startsWith("sun/")) //ignoring java.* is essential...
			return true;
		if(pname.startsWith("org/eclipse/jdt/internal/") || pname.startsWith("org/junit/") || pname.startsWith("junit/"))
			return true;	*/	
		return false;
	}
	
	
		public final Object getCallee() {
			return currentRef;
		}
		
	
		public final Object getCaller() {
			return currentSource;
		}
		
		public final String getCalleeClassname() {
			return currentRefClass;
		}
		
	
		public final String getCallerClassname() {
			return currentSourceClass;
		}
		
		public final Object getStackValue(int index){
			return stack.get(index);
		}
	
		public final int getStackSize(){
			return stack.size();
		}
	
		
		@SuppressWarnings("unchecked")
		public final LinkedList<Object> cloneStack(){
			return (LinkedList<Object>)stack.clone();
		}
		
	//==================================================================================================
	

	
	
	public int getID() {return uid;}

	

/*	
	public Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException{
	
		if(classloader==null)
			{
			 classloader=new TraceClassLoader(this.getClass().getClassLoader(),this);
			}
		
		return classloader.loadClass(name, resolve);
		
	}
	*/
	
	public byte[] transformClassBytecode(byte[] b){
		return new ClassBytecodeTransformer(this).transformClassBytecode(b);
	}
	
	public final boolean checkTransform(String name){

		if(ignoreClass(name))
			return false;
		return traceClass(name);
	}
	
	public static Tracer getTracer(int tracerID){
		return ((Tracer)map.get(new Integer(tracerID)));
	}
	
	public void dispatchEvent(TraceEvent e){
		anyEvent(e);

		if(e instanceof VariableAssignmentEvent){
			variableAssignmentEvent((VariableAssignmentEvent)e);
		
		
		if(e instanceof MemberVariableAssignmentEvent){
			memberVariableAssignmentEvent((MemberVariableAssignmentEvent)e);
			return;
		}
		if(e instanceof LocalVariableAssignmentEvent){
			localVariableAssignmentEvent((LocalVariableAssignmentEvent)e);
			return;
		}		
		}
		
		if(e instanceof BeforeMethodCallEvent)
		{
			
			beforeMethodCallEvent((BeforeMethodCallEvent)e);
			return;
		}
		if(e instanceof AfterMethodCallEvent)
		{

			afterMethodCallEvent((AfterMethodCallEvent)e);
			return;
		}
		
		if(e instanceof ConstructorCallEvent)
		{
			if(e instanceof BeforeConstructorCallEvent)
			{beforeConstructorCallEvent((BeforeConstructorCallEvent) e); return;}
			if(e instanceof AfterConstructorCallEvent)
			{afterConstructorCallEvent((AfterConstructorCallEvent) e); return;}
		}
			
	}
	
	


	public final void clearCallee(){

		this.currentRef=null;
	}
	
	public final void clearCaller(){

		this.currentSource=null;
	}

	public final void setCallee(Object o){
		this.currentRef=o;
	}
	public final void setCaller(Object o){
		this.currentSource=o;
	}	
	
	public final void setCallerClass(String classname){
		this.currentSourceClass=classname;
	}
	public final void setCalleeClass(String classname){
		this.currentRefClass=classname.replace('/', '.');
	}
	
	//internal stack
 
	
	public void pushB(byte i){
		stack.addLast(new Byte(i));

	}
	public void pushC(char i){
		stack.addLast(new Character(i));

	}
	public void pushD(double i){
		stack.addLast(new Double(i));

	}
	public void pushF(float i){
		stack.addLast(new Float(i));

	}	
	public void pushI(int i){
		stack.addLast(new Integer(i));

	}
	public void pushJ(long i){
		stack.addLast(new Long(i));

	}
	public void pushL(Object i){
		stack.addLast(i);

	}
	public void pushS(short i){
		stack.addLast(new Short(i));

	}	
	public void pushZ(boolean i){
		stack.addLast(new Boolean(i));	

	}

	public final byte popB(){
		return (((Byte)stack.removeLast()).byteValue());
	}
	public final char popC(){
		return (((Character)stack.removeLast()).charValue());
	}
	public final double popD(){
		return (((Double)stack.removeLast()).doubleValue());
	}
	public final float popF(){
		return (((Float)stack.removeLast()).floatValue());
	}	
	public final int popI(){
		return (((Integer)stack.removeLast()).intValue());
	}
	public final long popJ(){
		return (((Long)stack.removeLast()).longValue());
	}
	public final Object popL(){
		return stack.removeLast();
	}
	public final short popS(){
		return (((Short)stack.removeLast()).shortValue());
	}	
	public final boolean popZ(){
		return (((Boolean)stack.removeLast()).booleanValue());
	}	

	

}

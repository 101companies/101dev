package mega.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import mega.test.data.MethodTestTracer;
import mega.trace.event.*;


import org.junit.Before;
import org.junit.Test;

public class JUnitMethodTest extends TestCommon{
	
	private static MethodTestTracer tracer;



	@Before
	public void setUp() throws Exception {
		tracer=new MethodTestTracer();
		
		setUpTracer(tracer);
		
		execute();
	}


	@Test
	public void methodTest() {
		LinkedList<TraceEvent> events=tracer.getEvents();
		
		LinkedList<LinkedList<Object>> stacks=tracer.getStacks();
		LinkedList<Object> stack;
		
		LinkedList<Object[]> calls=tracer.getCalls();
		LinkedList<String[]> callclasses=tracer.getCallClasses();
		
		String[] callclass;
		Object[] call;
		
		Object o;
		
		Object old=null;
		
		int count=1;
		
		for(TraceEvent e : events){
			
			stack = stacks.removeFirst();
			
			callclass=callclasses.removeFirst();
			call=calls.removeFirst();
			
			
			switch(count){
			
			case 1:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeMethodCallEvent");
				assertTrue(stack.size()==1);
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"mega.test.data.SampleClass");
				assertTrue(call[0]==null);
				assertTrue(call[1]!=null);
				assertEquals(((BeforeMethodCallEvent)e).getMethodName(),"mult");
				assertEquals(((BeforeMethodCallEvent)e).getOwner(),"mega/test/data/SampleClass");
				assertEquals(((BeforeMethodCallEvent)e).getDesc(),"(I)V");
				assertTrue(!((BeforeMethodCallEvent)e).isStatic());
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 2);
				
				old=call[1];
			break;
			case 2:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeMethodCallEvent");
				assertTrue(stack.size()==1);
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"mega.test.data.SampleClass");
				assertTrue(call[0]!=null);
				assertTrue(call[1]!=null);
				assertTrue(call[0].equals(call[1]));
				assertTrue(call[0].equals(old));
				assertEquals(((BeforeMethodCallEvent)e).getMethodName(),"addone");
				assertEquals(((BeforeMethodCallEvent)e).getOwner(),"mega/test/data/SampleClass");
				assertEquals(((BeforeMethodCallEvent)e).getDesc(),"(I)I");
				assertTrue(!((BeforeMethodCallEvent)e).isStatic());
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 10);
			break;	
			case 3:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterMethodCallEvent");
				assertTrue(stack.size()==0);
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"mega.test.data.SampleClass");
				assertTrue(call[0]!=null);
				assertTrue(call[0].equals(old));
				assertEquals(((AfterMethodCallEvent)e).getMethodName(),"addone");
				assertEquals(((AfterMethodCallEvent)e).getOwner(),"mega/test/data/SampleClass");
				assertEquals(((AfterMethodCallEvent)e).getDesc(),"(I)I");
				assertTrue(!((AfterMethodCallEvent)e).isStatic());
			break;
			case 4:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.lang.StringBuilder");
				assertEquals(((BeforeMethodCallEvent)e).getMethodName(),"append");
			break;		
			case 5:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.lang.StringBuilder");
				assertEquals(((AfterMethodCallEvent)e).getMethodName(),"append");
			break;	
			case 6:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.lang.StringBuilder");
				assertEquals(((BeforeMethodCallEvent)e).getMethodName(),"toString");
			break;		
			case 7:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.lang.StringBuilder");
				assertEquals(((AfterMethodCallEvent)e).getMethodName(),"toString");
			break;	
			case 8:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.io.PrintStream");
				assertEquals(call[0],old);
				assertEquals(((BeforeMethodCallEvent)e).getMethodName(),"println");
				assertEquals((String)stack.removeFirst(),"Result:11");
			break;	
			case 9:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.io.PrintStream");
				assertEquals(((AfterMethodCallEvent)e).getMethodName(),"println");
	
			break;	
			
			case 10:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterMethodCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(((AfterMethodCallEvent)e).getMethodName(),"mult");
	
			break;	
			default:
				fail();
			
			}
			
			
			
			count++;
			
		}
		
	}

}

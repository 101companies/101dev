package mega.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import mega.test.data.ConstructorTestTracer;
import mega.trace.event.*;


import org.junit.Before;
import org.junit.Test;

public class JUnitConstructorTest extends TestCommon{
	private static ConstructorTestTracer tracer;

	@Before
	public void setUp() throws Exception {
		tracer=new ConstructorTestTracer();
		
		setUpTracer(tracer);
		
		execute();
	}

	@Test
	public void constructorTest() {
		LinkedList<TraceEvent> events=tracer.getEvents();
		
		LinkedList<LinkedList<Object>> stacks=tracer.getStacks();
		LinkedList<Object> stack;
		
		LinkedList<Object[]> calls=tracer.getCalls();
		LinkedList<String[]> callclasses=tracer.getCallClasses();
		
		String[] callclass; //0 is caller, 1 is callee
		Object[] call; //same as above
		
		Object o;

		
		int count=1;
		
		for(TraceEvent e : events){
			
			stack = stacks.removeFirst();
			
			callclass=callclasses.removeFirst();
			call=calls.removeFirst();
			
			
			switch(count){
			
			case 1:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeConstructorCallEvent");
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"mega.test.data.SampleClass");
			
				assertEquals(((BeforeConstructorCallEvent)e).getOwner(),"mega/test/data/SampleClass");
				assertEquals(((BeforeConstructorCallEvent)e).getDesc(),"(I)V");
				assertTrue(!((BeforeConstructorCallEvent)e).isStatic());
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 5);
				
				
				
			break;
			case 2:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterConstructorCallEvent");
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertTrue(call[1]!=null);
				assertTrue(call[1].getClass().getName().equals("mega.test.data.SampleClass"));
		
			break;	
			
			case 3:
				assertEquals(e.getClass().getName(),"mega.trace.event.BeforeConstructorCallEvent");
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertEquals(callclass[1],"java.lang.StringBuilder");
			
				
				assertEquals(((BeforeConstructorCallEvent)e).getDesc(),"(Ljava/lang/String;)V");
				assertTrue(!((BeforeConstructorCallEvent)e).isStatic());
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.String"));
				assertTrue(((String)o).equals("Result:"));
				
				
				
			break;
			case 4:
				assertEquals(e.getClass().getName(),"mega.trace.event.AfterConstructorCallEvent");
				assertEquals(callclass[1],"java.lang.StringBuilder");
				assertTrue(call[1]!=null);
				assertTrue(call[1].getClass().getName().equals("java.lang.StringBuilder"));
		
			break;	
			default:
				fail();
			
			}
			
			
			
			count++;
			
		}
		
	}
}

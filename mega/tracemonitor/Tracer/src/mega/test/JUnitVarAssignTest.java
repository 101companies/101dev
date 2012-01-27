package mega.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import mega.test.data.VarTestTracer;
import mega.trace.event.*;

import org.junit.Before;
import org.junit.Test;

public class JUnitVarAssignTest extends TestCommon{
	
	private static VarTestTracer tracer;



	@Before
	public void setUp() throws Exception {
		tracer=new VarTestTracer();
		
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
		Object old=null;
		
		int count=1;
		
		for(TraceEvent e : events){
			
			stack = stacks.removeFirst();
			
			callclass=callclasses.removeFirst();
			call=calls.removeFirst();
			
			
			switch(count){
			
			case 1:
				assertEquals(e.getClass().getName(),"mega.trace.event.MemberVariableAssignmentEvent");
				
				
					
				
				assertEquals(((MemberVariableAssignmentEvent)e).getSignature(),"I");
				assertTrue(((MemberVariableAssignmentEvent)e).getStatic());
				assertTrue(((MemberVariableAssignmentEvent)e).getName().equals("c"));
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 5);
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertTrue(call[0]==null); //from static method
				assertEquals(callclass[1],"mega.test.data.SampleClass");
				assertTrue(call[1]==null); //to static field..
				
			break;
			case 2:
				assertEquals(e.getClass().getName(),"mega.trace.event.MemberVariableAssignmentEvent");
				
			
					
				
				assertEquals(((MemberVariableAssignmentEvent)e).getSignature(),"I");
				assertTrue(!((MemberVariableAssignmentEvent)e).getStatic());
				assertTrue(((MemberVariableAssignmentEvent)e).getName().equals("x"));
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 5);
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertTrue(call[0]!=null); //within object(constructor)
				assertEquals(callclass[1],"mega.test.data.SampleClass");
				assertTrue(call[1]!=null); //to non static member field..
				assertTrue(call[0]==call[1]); //within the same object
				
				old=call[0];
				
			break;
			case 3:
				assertEquals(e.getClass().getName(),"mega.trace.event.LocalVariableAssignmentEvent");
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertTrue(call[0]!=null);
				assertTrue(call[0]==old); //same object...
				
				assertEquals(((LocalVariableAssignmentEvent)e).getSignature(),"I");
				
			
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 10); //x*y = 5*2
				
				
				
			break;
			case 4:
				assertEquals(e.getClass().getName(),"mega.trace.event.LocalVariableAssignmentEvent");
				
				assertEquals(callclass[0],"mega.test.data.SampleClass");
				assertTrue(call[0]!=null);
				assertTrue(call[0]==old); //same object again...
				
				assertEquals(((LocalVariableAssignmentEvent)e).getSignature(),"I");
				
			
				assertTrue(stack.size()==1);
				o=stack.removeFirst();
				assertTrue(o.getClass().getName().equals("java.lang.Integer"));
				assertTrue(((Integer)o).intValue() == 11); //x = x+1
				
				
				
			break;
			default:
				fail();
			
			}
			
			
			
			count++;
			
		}
		
	}

}

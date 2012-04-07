package mega.test.JAXB;

import java.util.LinkedList;

import mega.trace.event.TraceEvent;

public class TraceSet {
	TraceEvent event;
	LinkedList<Object> stack;
	Object[] references;
	String[] classes;
	
	public TraceSet(TraceEvent e,LinkedList<Object> stack,Object[] references,String[] classes)
	{
		this.references=references;
		this.classes=classes;
		this.event=e;
		this.stack=stack;
	}
		
}

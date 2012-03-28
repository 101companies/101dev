package mega.test.JAXB;

import mega.trace.event.*;

/*
 * The pattern matching is done after the program has ended.
Because of this the runtime-state of ooin/oout is lost. Cloning them at runtime to access them later seems not like an option, see:
http://docs.oracle.com/javase/1.4.2/docs/api/java/lang/Object.html#clone%28%29 
http://www.artima.com/objectsandjava/webuscript/ClonCollInner1.html

To get the real state of these objects pattern matching has to be done at runtime.
 */

public class JAXBMapper extends Thread implements Runnable{

	private JAXBTestTracer tracer;
	
	public final static int lookeventcount=18; 
	
	
	public JAXBMapper(JAXBTestTracer tracer){
		this.tracer=tracer;
	}
	
	
	public MatchingSet lookfor(int looknum,int eventnum,MatchingSet s){
		
			
		if(looknum==lookeventcount)
			return s;
	
		
		if(eventnum==tracer.getEventCount())
			return null; 
		
		TraceSet e = tracer.getWholeEvent(eventnum);
		
		eventnum++;
		
		
		if(match(looknum,e,s))
			{
				MatchingSet result=lookfor(looknum++,eventnum,s.clone());
				if(result!=null)
					return result;
			}
		return lookfor(looknum,eventnum,s);
	}
	
	public void outputfail() {System.out.println("Pattern not found.");}
	
	public void outputMatching(MatchingSet s){System.out.println("Pattern found.\n"+s);}
	
	public boolean match(int looknum,TraceSet e,MatchingSet s)
	{
		switch (looknum){
		
		
		//deserialization
		
		
		
		//1&2: event(bind(sin,new(’FileStream’,[xin,fmin])))
		//sin is hidden within javax.*, but however we now the file corresponding to sin
		case 1:
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeConstructorCallEvent")){
				if(e.classes[1].equals("java.io.File")){
					s.xin=(String)e.stack.get(0); //take xin
					return true;
				}
				
			}
			return false;
			
			
		case 2:
			if(e.event.getClass().getName().equals("mega.trace.event.AfterConstructorCallEvent")){
				if(e.classes[1].equals("java.io.File")){
					s.sinfile=e.references[1]; //bind sinfile instead of sin
					return true;
				}
				
			}	
			return false;
		
			
			
		//3&4 => event(bind(xs,new(’XmlSerializer’,[type])))
		case 3:
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeMethodCallEvent")){
				
				if(e.classes[1].equals("javax.xml.bind.JAXBContext")){
					
					if( ((BeforeMethodCallEvent) e.event).getMethodName().equals("newInstance")){
						
					s.type=(String)e.stack.get(0); //check [type]
					return true;}
				}
				
			}
			return false;
		case 4:
			if(e.event.getClass().getName().equals("mega.trace.event.AfterMethodCallEvent")){
				if(e.classes[1].equals("javax.xml.bind.JAXBContext")){
					
					if( ((AfterMethodCallEvent) e.event).getMethodName().equals("newInstance"))
					{
					s.xs=e.stack.get(0); //bind xs
					return true;
					}
				}
				
			}
			return false;			
			
			
			
			
		//5,6,7,8 =>  event(bind(obj,icall(xs,’Deserialize’,[sin]))) 
		
			// icall(xs,’Deserialize’,[sin]) => xs.createUnmarshaller().unmarshal(sin);

			
		case 5:
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeMethodCallEvent"))
				if(e.classes[1].equals("javax.xml.bind.JAXBContext"))
					if( ((BeforeMethodCallEvent) e.event).getMethodName().equals("createUnmarshaller"))
						if(e.references[1] == s.xs) //xs.createUnmarshaller()
							return true;
					
			return false;
			
		case 6:
			if(e.event.getClass().getName().equals("mega.trace.event.AfterMethodCallEvent"))
				if(e.classes[1].equals("javax.xml.bind.JAXBContext"))
					
					if( ((AfterMethodCallEvent) e.event).getMethodName().equals("createUnmarshaller"))
					{
					s.temp=e.stack.get(0); //take unmarshaller
					return true;
					}
				
			return false;	
			
	 	
		case 7:
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeInterfaceCallEvent")){
				if(e.classes[1].equals("javax.xml.bind.Unmarshaller")){
					
					if( ((BeforeInterfaceCallEvent) e.event).getMethodName().equals("unmarshal"))
						if(e.stack.size()==1){
							if(e.stack.get(0).getClass().getName().equals("java.io.File"))
								if(e.stack.get(0) == s.sinfile) //check [sin]
									if(s.temp == e.references[1]) //has to be the same unmarshaller of course
										return true;
				}}
				
			}
			return false;
			
		case 8:
			if(e.event.getClass().getName().equals("mega.trace.event.AfterInterfaceCallEvent"))
					if( ((AfterInterfaceCallEvent) e.event).getMethodName().equals("unmarshal"))
					{
						
						s.obj = (e.stack.get(0)); //bind obj and perform action(copy(obj,oin))

						return true;
					}
			return false;
		
		//deserialization done.
			
		//transformation(we dont care)	
			
			//group(otransform,[])
			
		//transformation done	
		
		//serialization:
		
			
		//9&12: event(bind(sout,new(’FileStream’,[xout,fmout]))	
		//9&10 are the file for the filestream
		case 9:
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeConstructorCallEvent")){
				if(e.classes[1].equals("java.io.File")){
					s.xout=(String)e.stack.get(0); //check [xout]
					return true;
				}
				
			}
			return false;	
		case 10:	
			if(e.event.getClass().getName().equals("mega.trace.event.AfterConstructorCallEvent")){
				if(e.classes[1].equals("java.io.File")){
					s.soutfile=e.references[1]; //bind the file itself
					return true;
				}
				
			}	
			return false;
	
		case 11:	
			if(e.event.getClass().getName().equals("mega.trace.event.BeforeConstructorCallEvent")){
				if(e.classes[1].equals("java.io.FileOutputStream")){
					if(e.stack.get(0).equals(s.soutfile)) //new FileStream on the file
						return true;
				}
				
			}	
			return false;

		case 12:	
			if(e.event.getClass().getName().equals("mega.trace.event.AfterConstructorCallEvent")){
				if(e.classes[1].equals("java.io.FileOutputStream")){
					s.sout=e.references[1]; //bind sout
						return true;
				}
				
			}	
			return false;
			
	//13-17: event(icall(xs,’Serialize’,[sout,obj])),
		
	case 13:
		if(e.event.getClass().getName().equals("mega.trace.event.BeforeMethodCallEvent")){
			if(e.classes[1].equals("javax.xml.bind.JAXBContext")){
				if(e.references[1] == s.xs) //match xs
				if( ((BeforeMethodCallEvent) e.event).getMethodName().equals("createMarshaller"))
					return true;
			}
			
		}
		return false;
		
	case 14:
		if(e.event.getClass().getName().equals("mega.trace.event.AfterMethodCallEvent")){
			if(e.classes[1].equals("javax.xml.bind.JAXBContext")){
				if( ((AfterMethodCallEvent) e.event).getMethodName().equals("createMarshaller"))
				{
				s.temp=e.stack.get(0); //take marshaller
				return true;
				}
					
			}
		}
	case 15:
		if(e.event.getClass().getName().equals("mega.trace.event.BeforeMethodCallEvent")){
			if(e.classes[1].equals("javax.xml.stream.XMLOutputFactory")){
				if( ((BeforeMethodCallEvent) e.event).getMethodName().equals("createXMLStreamWriter"))
					if(e.stack.size()==1)
						if(e.stack.get(0)==s.sout) //check [sout]
							return true;
			}
			
		}
		return false;
		
	case 16:
		if(e.event.getClass().getName().equals("mega.trace.event.AfterMethodCallEvent")){
			if(e.classes[1].equals("javax.xml.stream.XMLOutputFactory")){
				if( ((AfterMethodCallEvent) e.event).getMethodName().equals("createXMLStreamWriter"))
				{
				s.sout_writer=e.stack.get(0); //take streamwriter
				return true;
				}
					
			}
		}
		
	case 17:
		if(e.event.getClass().getName().equals("mega.trace.event.BeforeInterfaceCallEvent")){
			if(e.classes[1].equals("javax.xml.bind.Marshaller")){
				
				if( ((BeforeInterfaceCallEvent) e.event).getMethodName().equals("marshal"))
					if(e.stack.size()==2){
						//check streamwriter and [obj]
						if(e.stack.get(1) == s.obj && e.stack.get(0)==s.sout_writer )
						{
							return true;
						}
			}}
			
		}
		return false;
		
		
		}
		
		return false;
	}

	public void run(){
		
		System.out.println("\nshutdown hook\nJAXB Mapper now running..");
		MatchingSet result=lookfor(1,0,new MatchingSet());
		if(result!=null){
			System.out.println("Matching pattern found!\n\n"+result);
		}else{
			System.out.println("Matching pattern not found.");
		}

		
	}
	
	
}

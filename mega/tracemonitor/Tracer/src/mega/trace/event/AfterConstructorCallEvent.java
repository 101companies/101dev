package mega.trace.event;


public class AfterConstructorCallEvent extends ConstructorCallEvent  {
	
	//return value in callee-reference
	

	public AfterConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
			super(name,owner,isstatic,desc);
	}

}

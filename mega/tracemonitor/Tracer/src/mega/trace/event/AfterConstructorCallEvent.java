package mega.trace.event;


public class AfterConstructorCallEvent extends ConstructorCallEvent  {
	


	public AfterConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
			super(name,owner,isstatic,desc);
	}

}

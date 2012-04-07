package mega.trace.event;

public class BeforeConstructorCallEvent extends ConstructorCallEvent {

	//arguments on stack, no target object reference set because it was not initialized yet.
	
	public BeforeConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
		super(name,owner,isstatic,desc);
		
	}

}

package mega.trace.event;

public class BeforeConstructorCallEvent extends ConstructorCallEvent {

	public BeforeConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
		super(name,owner,isstatic,desc);
		
	}

}

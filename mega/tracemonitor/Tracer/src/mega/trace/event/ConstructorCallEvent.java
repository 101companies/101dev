package mega.trace.event;

public class ConstructorCallEvent extends MethodCallEvent {
	
	public ConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
		super(name,owner,isstatic,desc);

	}

}

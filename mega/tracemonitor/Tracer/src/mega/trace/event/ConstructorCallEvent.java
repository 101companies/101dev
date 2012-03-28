package mega.trace.event;

public abstract class ConstructorCallEvent extends MethodCallEvent {
	
	public ConstructorCallEvent(String name,String owner,boolean isstatic,String desc) {
		super(name,owner,isstatic,desc);

	}

}

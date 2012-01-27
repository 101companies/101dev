package mega.trace.event;

public class BeforeInterfaceCallEvent extends InterfaceCallEvent{

	public BeforeInterfaceCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

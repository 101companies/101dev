package mega.trace.event;

public class BeforeInterfaceCallEvent extends InterfaceCallEvent{
	
	//arguments on stack

	public BeforeInterfaceCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

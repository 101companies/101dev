package mega.trace.event;

public class AfterInterfaceCallEvent extends InterfaceCallEvent{

	//return value on stack
	
	public AfterInterfaceCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

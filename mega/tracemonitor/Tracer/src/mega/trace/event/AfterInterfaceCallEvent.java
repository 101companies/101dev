package mega.trace.event;

public class AfterInterfaceCallEvent extends InterfaceCallEvent{

	public AfterInterfaceCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

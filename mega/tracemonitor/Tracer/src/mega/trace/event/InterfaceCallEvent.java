package mega.trace.event;

public class InterfaceCallEvent extends MethodCallEvent{

	public InterfaceCallEvent(String methodName,String owner,boolean isStatic,String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

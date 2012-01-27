package mega.trace.event;

public class BeforeMethodCallEvent extends MethodCallEvent{

	public BeforeMethodCallEvent(String methodName,String owner,boolean isStatic,String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

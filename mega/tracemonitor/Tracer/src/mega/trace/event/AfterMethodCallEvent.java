package mega.trace.event;

public class AfterMethodCallEvent extends MethodCallEvent{

	//return value on stack(if any)
	
	public AfterMethodCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

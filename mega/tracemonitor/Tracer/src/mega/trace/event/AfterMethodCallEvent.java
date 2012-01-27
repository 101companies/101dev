package mega.trace.event;

public class AfterMethodCallEvent extends MethodCallEvent{

	public AfterMethodCallEvent(String methodName,String owner,boolean isStatic, String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

package mega.trace.event;

public class BeforeMethodCallEvent extends MethodCallEvent{

	//arguments on stack
	
	public BeforeMethodCallEvent(String methodName,String owner,boolean isStatic,String desc) {
		super(methodName,owner,isStatic,desc);
	}

}

package mega.trace.event;

public abstract class MethodCallEvent extends TraceEvent{

	private final String methodName;
	private final boolean isStatic;
	private final String desc;
	private final String owner;

	public MethodCallEvent(String methodName,String owner,boolean isStatic,String desc){
		super();
		this.methodName=methodName;
		this.isStatic=isStatic;
		this.desc=desc;
		this.owner=owner;
	}

	public String getMethodName() {
		return methodName;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public String getDesc() {
		return desc;
	}
	
	
	public String getOwner() {
		return owner;
	}



	
	
	
	
}

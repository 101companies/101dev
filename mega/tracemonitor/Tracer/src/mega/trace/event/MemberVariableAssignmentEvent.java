package mega.trace.event;

public class MemberVariableAssignmentEvent extends VariableAssignmentEvent {
	
	private final boolean isStatic;

	public MemberVariableAssignmentEvent(String varname,String signature,boolean isStatic) {
		super(varname,signature);
		this.isStatic=isStatic;
	}
	
	public boolean getStatic() {return isStatic;}
	
	//public String getSignature() {
	//	throw new Error("not yet implemented");
	//}	

}

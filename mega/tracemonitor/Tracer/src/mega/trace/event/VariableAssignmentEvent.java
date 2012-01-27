package mega.trace.event;

public class VariableAssignmentEvent extends TraceEvent {

	private final String varname;
	private final String signature;
	
	public VariableAssignmentEvent(String varname,String signature) {
		super();
		this.varname=varname;
		this.signature=signature;
	}

	public String getName() {
		return varname;
	}

	public String getSignature() {
		return signature;
	}	
	

}

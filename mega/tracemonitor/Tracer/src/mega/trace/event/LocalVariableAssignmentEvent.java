package mega.trace.event;

public class LocalVariableAssignmentEvent extends VariableAssignmentEvent {
	
	//assigned value on stack

	public LocalVariableAssignmentEvent(String index,String signature) {
		super(index,signature);
	
	}
	
	
}

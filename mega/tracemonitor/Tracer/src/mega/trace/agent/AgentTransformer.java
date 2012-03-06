package mega.trace.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import mega.trace.core.Tracer;
import mega.trace.transformation.ClassBytecodeTransformer;

public class AgentTransformer implements ClassFileTransformer{
	
	private final Tracer tracer;
	
	public AgentTransformer(Tracer tracer){
		this.tracer=tracer;
	}
	
	
	@Override
	public byte[] transform(ClassLoader cloader, String s, Class<?> c, ProtectionDomain pdomain, byte[] bcode) throws IllegalClassFormatException {
        
        if(s.startsWith("java/") || s.startsWith("sun/"))
        	return bcode;
		
		//if(c.isAnnotationPresent(NoTrace.class))
			//return bcode; //TODO: check if "return null" also works instead.
		
		
		ClassBytecodeTransformer t = new ClassBytecodeTransformer(tracer);
	
		byte[] newcode;
		
		newcode = t.transformClassBytecode(bcode);
		
		return newcode;
	}

}

package mega.trace.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import mega.trace.core.TraceConfiguration;
import mega.trace.core.Tracer;
import mega.trace.transformation.ClassBytecodeTransformer;

public class AgentTransformer implements ClassFileTransformer{
	
	private final Tracer tracer;
	
	public AgentTransformer(Tracer tracer){
		this.tracer=tracer;
	}
	
	
	@Override
	public byte[] transform(ClassLoader cloader, String s, Class<?> c, ProtectionDomain pdomain, byte[] bcode) throws IllegalClassFormatException {

        if(TraceConfiguration.prohibitedPackage(s))
           	return bcode;
             
        if(!tracer.traceClass(s))
        	return bcode;
      
		
        ClassBytecodeTransformer t = new ClassBytecodeTransformer(tracer);
			
		return t.transformClassBytecode(bcode);
		
	}

}

package mega.trace.transformation;

import mega.trace.core.Tracer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassBytecodeTransformer {
	private final Tracer tracer;

	public ClassBytecodeTransformer(Tracer tracer){
		this.tracer=tracer;
	}
	
public byte[] transformClassBytecode(byte[] bcode){

		ClassReader cr = new ClassReader(bcode);
		ClassWriter cw = new ClassWriter(0);
		TransformerClassVisitor tcv = new TransformerClassVisitor(cw,tracer);
	
		cr.accept(tcv, 0);
		return cw.toByteArray();
	}
}

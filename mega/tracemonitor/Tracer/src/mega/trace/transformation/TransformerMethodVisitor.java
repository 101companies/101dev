package mega.trace.transformation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import mega.trace.core.Tracer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TransformerMethodVisitor extends MethodVisitor implements Opcodes{
	private final Tracer tracer;
	private final String classname;
	private final String name;
	private final String superName;

	private LinkedList<String[]> poplist;

	private boolean staticmethod;

	private static final int FLAG_STATIC=1;
	private static final int FLAG_CONSTRUCTOR=2;
	private static final int FLAG_INTERFACE=4;
	private static final int FLAG_VIRTUAL=8;

	public TransformerMethodVisitor(final MethodVisitor mv,String thisname,String superName,Tracer tracer,String classname,HashMap<String,String> fieldsigmap,boolean isStatic) {
		super(ASM4, mv);
		this.tracer=tracer;
		this.classname=classname.replace('/','.');
		this.poplist=new LinkedList<String[]>();
		this.staticmethod=isStatic;
		this.name=thisname;
		this.superName=superName;
	}


	public void visitMaxs(int maxStack, int maxLocals){ 
		super.visitMaxs(maxStack+10,maxLocals);	//need more stack memory.
	}


	public void visitFieldInsn(int opcode,String owner,String name,String desc){


		if((opcode==PUTSTATIC||opcode==PUTFIELD)&&(tracer.injectVariableCall(name,true,opcode==PUTSTATIC)))
			insertMemberVariableCall(name,opcode==PUTSTATIC,desc,owner); //member field assignment event

		super.visitFieldInsn(opcode,owner,name,desc);
	}

	public void visitVarInsn(int opcode,int var){

		String info="";
		boolean call=false;


		switch(opcode){

		case ISTORE:
			info="I";
			call=true;
			break;
		case LSTORE:
			info="J";
			call=true;
			break;
		case FSTORE:
			info="F";
			call=true;
			break;
		case DSTORE:
			info="D";
			call=true;
			break;
		case ASTORE:
			info="L?";
			call=true;
			break;


		}

		if(call && tracer.injectVariableCall(""+var,false,false))
			insertLocalVariableCall(var,info); //locale var assignment event

		super.visitVarInsn(opcode, var);

	}

	public void visitMethodInsn(int opcode,String owner,String targetname,String desc){

		
		
		switch(opcode){

		case INVOKEVIRTUAL:

			insertBeforeMethodCall(targetname,desc,owner,FLAG_VIRTUAL);
			super.visitMethodInsn(opcode,owner,targetname,desc);
			insertAfterMethodCall(targetname,desc,owner,FLAG_VIRTUAL);
			break;

		case INVOKESTATIC:
			insertBeforeMethodCall(targetname,desc,owner,FLAG_STATIC+FLAG_VIRTUAL);
			super.visitMethodInsn(opcode,owner,targetname,desc);
			insertAfterMethodCall(targetname,desc,owner,FLAG_STATIC+FLAG_VIRTUAL);
			break;

		case INVOKESPECIAL:

			if(targetname.equals("<init>")){
				insertBeforeMethodCall(targetname,desc,owner,FLAG_CONSTRUCTOR);
				super.visitMethodInsn(opcode,owner,targetname,desc);
				insertAfterMethodCall(targetname,desc,owner,FLAG_CONSTRUCTOR);


			}else{

				insertBeforeMethodCall(targetname,desc,owner,FLAG_VIRTUAL);
				super.visitMethodInsn(opcode,owner,targetname,desc);
				insertAfterMethodCall(targetname,desc,owner,FLAG_VIRTUAL);

			}
			break;

		case INVOKEINTERFACE:

			insertBeforeMethodCall(targetname,desc,owner,FLAG_INTERFACE);
			super.visitMethodInsn(opcode,owner,targetname,desc);
			insertAfterMethodCall(targetname,desc,owner,FLAG_INTERFACE);
			break;

		default:
			throw new Error("TransformerMethodVisitor, visitMethodInsn default.");			

		}

	}

	private void insertDispatcherCall(){
		//call tracer. one argument on top(class TraceEvent), then tracer reference
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "dispatchEvent", "(Lmega/trace/event/TraceEvent;)V");
	}

	private void insertLocalVariableCall(int index,String signature){

		insertLookup(); 

		if(signature.equals("L?")){ //unknown type, handle special
			insertLVarPushArgumentWorkaround();
		}else{			
			insertPushArguments("("+signature+")",true,false,classname); //use normal push
		}

		insertPushSource(staticmethod,classname); //set caller
		mv.visitInsn(DUP); 

		mv.visitTypeInsn(NEW, "mega/trace/event/LocalVariableAssignmentEvent");
		mv.visitInsn(DUP);


		mv.visitLdcInsn(""+index);
		mv.visitLdcInsn(signature);

		//create event
		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/LocalVariableAssignmentEvent", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V");
		//pass event to tracer
		insertDispatcherCall();
		
		if(signature.equals("L?")){ //this is handled special, object was not popped from the jvm stack but only copied so it has to removed from the tracer's 'virtual' stack
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "popL", "()Ljava/lang/Object;");
			mv.visitInsn(POP);
		}else{			
			insertPopArguments(true); //otherwise use default pop algorithm
		}

		

	}

	private void insertMemberVariableCall(String varname,boolean staticField,String signature,String owner){

		insertLookup();

		insertPushArguments("("+signature+")",staticField,false,owner);
		insertPushSource(staticmethod,classname);
		mv.visitInsn(DUP); 

		mv.visitTypeInsn(NEW, "mega/trace/event/MemberVariableAssignmentEvent");
		mv.visitInsn(DUP);


		mv.visitLdcInsn(varname);
		mv.visitLdcInsn(signature);
		insertBooleanValue(staticField);


		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/MemberVariableAssignmentEvent", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V");

		insertDispatcherCall();

		insertPopArguments(true);


	}

	private boolean isSuperConstructorCall(String methodName,String desc,String owner)
	{
		return(name.equals("<init>")&&methodName.equals("<init>")&&owner.equals(superName));
	}



	private void insertBeforeMethodCall(String methodName,String desc,String owner,int flags)
	{
		
		//create a before method call event
		//could be constructor, a regular method or an interface call

		String event=null;

		if(isSuperConstructorCall(methodName,desc,owner)) //ignore, we can not insert code before the initial super() of a constructor was executed
			return;

		if((flags&FLAG_CONSTRUCTOR)!=0){
			if(!tracer.injectBeforeConstructorCall(classname)){
				if(tracer.injectAfterConstructorCall(classname)){
					//if one is only interested in objects after creation but not in the initial constructor call itself we can handle it this way
					//usually the BeforeConstructorCall DUP the object so the AfterConstructorCall can take it. 
					insertLookup(); 
					insertPushArguments(desc,(flags&FLAG_STATIC)!=0,true,owner);
					insertPopArguments((flags&FLAG_STATIC)!=0);
				}
				return;
			}
			event="BeforeConstructorCallEvent";

		}else if((flags&FLAG_INTERFACE)!=0){
			if(!tracer.injectBeforeInterfaceCall(classname, methodName))
				return;
			event="BeforeInterfaceCallEvent";
		}else if((flags&FLAG_VIRTUAL)!=0){
			if(!tracer.injectBeforeMethodCall(classname, methodName))
				return;
			event="BeforeMethodCallEvent";
		}else{
			throw new Error("TransformerMethodVisitor, insertBeforeMethodCall");
		}


		insertLookup(); //get tracer reference

		insertPushArguments(desc,((flags&FLAG_STATIC)+(flags&FLAG_CONSTRUCTOR))!=0,((flags&FLAG_CONSTRUCTOR)!=0) && tracer.injectAfterConstructorCall(classname),owner);
		//push the method/constructor/interface arguments. in case of a constructor call we duplicate the reference to the new object to take it away with AfterConstructorCallEvent later 
		
		insertPushSource(staticmethod,classname); //set source class/reference
		mv.visitInsn(DUP); 

		mv.visitTypeInsn(NEW, "mega/trace/event/"+event); //create the specific event
		mv.visitInsn(DUP);



		mv.visitLdcInsn(methodName);
		mv.visitLdcInsn(owner);

		insertBooleanValue((flags&FLAG_STATIC)!=0);

		mv.visitLdcInsn(desc);

		//constructor call of the new event
		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/"+event, "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V");

		
		//pass event to tracer
		insertDispatcherCall();

		//reference to tracer still on top!
		//get arguments back to the JVM stack
		insertPopArguments((flags&FLAG_STATIC)!=0);

	}

	private void insertAfterMethodCall(String methodName,String desc,String owner,int flags)
	{
		String event=null;
		
		//nothing special, see BeforeMethodCall

		if(isSuperConstructorCall(methodName,desc,owner))
			return;

		if((flags&FLAG_CONSTRUCTOR)!=0){
			if(!tracer.injectAfterConstructorCall(classname))
				return;
			event="AfterConstructorCallEvent";

		}else if((flags&FLAG_INTERFACE)!=0){
			if(!tracer.injectAfterInterfaceCall(classname, methodName))
				return;
			event="AfterInterfaceCallEvent";
		}else if((flags&FLAG_VIRTUAL)!=0){
			if(!tracer.injectAfterMethodCall(classname, methodName))
				return;
			event="AfterMethodCallEvent";
		}else{
			throw new Error("TransformerMethodVisitor, insertAfterMethodCall");
		}
		
		insertLookup();
		
		boolean popReturnValue=false;

		if((flags&FLAG_CONSTRUCTOR)!=0){ //when a constructor is called, the object to whom the constructor belongs is saved in setCallee
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCallee", "(Ljava/lang/Object;)V");
		}else{ //a method/interface returned, so we push its return value on the tracer's stack
			String rvalue = desc.split("\\)")[1];
			if(!rvalue.equals("V")) //do not push void....
			{
				
				popReturnValue=true;
	
				if(rvalue.startsWith("L")) //handle object
					{
					mv.visitInsn(SWAP);
					mv.visitInsn(DUP_X1);
					mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "pushL", "(Ljava/lang/Object;)V");
					}
				else
					{
					if(rvalue.equals("J")||rvalue.equals("D")) //float&double have 2 stack words
					{
						mv.visitInsn(DUP_X2);	
						mv.visitInsn(POP);	
						mv.visitInsn(DUP2_X1);	
					}else{ //int only 1
						mv.visitInsn(SWAP);
						mv.visitInsn(DUP_X1);
					}
					
					mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "push"+rvalue, "("+rvalue+")V");
					
					}
				
				insertLookup();
				mv.visitInsn(DUP);
		
			}
		}

		insertPushSource(staticmethod,classname);
		
		
		
		mv.visitTypeInsn(NEW, "mega/trace/event/"+event);
		mv.visitInsn(DUP);
		mv.visitLdcInsn(methodName);
		mv.visitLdcInsn(owner);
		insertBooleanValue((flags&FLAG_STATIC)!=0);
		mv.visitLdcInsn(desc);

		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/"+event, "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V");

		insertDispatcherCall();

		if(popReturnValue){
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "drop", "()V");
		}

	}


	private void insertPushSource(boolean isStatic,String classname){
		//assumes reference to tracer on top & keeps this reference
		mv.visitInsn(DUP);
		mv.visitLdcInsn(classname);
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCallerClass", "(Ljava/lang/String;)V");
		mv.visitInsn(DUP);
		if(isStatic)
		{
			//mv.visitInsn(ACONST_NULL);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "clearCaller", "()V");
		}else{

			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCaller", "(Ljava/lang/Object;)V");
		}

	}

	private void insertLVarPushArgumentWorkaround(){
		//no local var typ information available.. so this workaround
		mv.visitInsn(DUP);	
		mv.visitLdcInsn(classname);
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCalleeClass", "(Ljava/lang/String;)V");
		mv.visitInsn(DUP_X1);
		mv.visitInsn(SWAP);
		mv.visitInsn(DUP_X1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "pushL", "(Ljava/lang/Object;)V");
		mv.visitInsn(SWAP);	
	}

	private void insertPushArguments(String desc,boolean dontPushFRef,boolean duplicateFRef,String calleeClass){
		//"pushes" all arguments of method description desc via n times tracer.pushX
		//assumes reference to tracer is on top of the stack & keeps reference
	
		int i;
		char c;


		boolean skip=true;
		boolean ref=false;

		String buff="";


		for(i=desc.length()-1;i>0;i--){	



			c=desc.charAt(i);

			if(skip){  //wait for beginning of argument list
				if(c==')')
					skip=false;
				continue;
			}
			if(c==';')
			{
				ref=true;
				continue;
			}

			if(ref){
				//if the current letter equals 'L' than position i could be the beginning
				//of the class' reference string. It could also be just a 'L' within the string.
				if(c=='L' && notWithinReference(desc,i)){
					insertPushArgument(buff,dontPushFRef,true,false);
					ref=false;
					buff="";
					continue;
				}


				buff=c+buff;



			}else{

				insertPushArgument(c+"",dontPushFRef,false,false);
			}

		}

		if(duplicateFRef){
			mv.visitInsn(SWAP);
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
		}

		if(!dontPushFRef){	

			//create copy of target object instead of pushing it away
			mv.visitInsn(SWAP);
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			insertPushArgument("java/lang/Object",dontPushFRef,true,true);

		}else{
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "clearCallee", "()V");
		}
		mv.visitInsn(DUP);	
		mv.visitLdcInsn(calleeClass.replace('.', '/'));
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCalleeClass", "(Ljava/lang/String;)V");

	}
	
	
	private boolean notWithinReference(String desc,int pos){
		//true if desc[pos] is not within a class reference
		//avoid such things as Ljava/util/LinkedList;
		
		while(--pos>=0){
			if(desc.charAt(pos) == 'L')
				return false;
			if(desc.charAt(pos) == ';')
				return true;			
		}
		
		return true;
	}

	private void insertPopArguments(boolean isStatic){
		//assumes reference to tracer on top & discards this reference!

		if(poplist.isEmpty()) //no arguments to pop?
		{
	
			mv.visitInsn(POP); //don't forget to remove reference of tracer 
			return;
		}

		Iterator<String[]> i=poplist.descendingIterator();


		while(i.hasNext()){

			String[] a=i.next();

			if(i.hasNext()) //not last element?
				mv.visitInsn(DUP); //then duplicate tracer reference...

			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", a[0], a[1]); //perform "POP"

			if(a[2]!=null)
				mv.visitTypeInsn(CHECKCAST, a[2]);

			if(i.hasNext())
			{//put tracer reference on top again
				
				if(a[3].equals("D") || a[3].equals("J")){ //"pop'ed" 2 word value? SWAP would not work...
					mv.visitInsn(DUP2_X1);
					mv.visitInsn(POP2);
				}else{
					mv.visitInsn(SWAP); //1 word value: just swap it
				}
			}


		}

		poplist.clear(); //welldone.
		
	}


	private void insertPushArgument(String type,boolean isStatic,boolean isReference,boolean pushTarget){
		//"pushes" one argument via tracer.pushX & keeps reference
		//assumes reference to tracer is on top of the stack and argument is below

		if((!pushTarget)||(pushTarget&&!isStatic)) 
		{
			//duplicate tracer reference and pull argument on top of the stack
			//top -> low : argument, tracer, tracer
			
			//double and long have to be handled different(2 words on stack)
			if(type.equals("D") || type.equals("J")){
				mv.visitInsn(DUP_X2);
				mv.visitInsn(DUP_X2);
				mv.visitInsn(POP);
			}else{
 				mv.visitInsn(DUP_X1); 
				mv.visitInsn(SWAP);
			}
			
		}else{
			//no more arguments available. just duplicate tracer ref.
			mv.visitInsn(DUP);
		}

		if(pushTarget){
			if(isStatic){
				//static methods don't have a reference they get invoked on
				mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "clearCallee", "()V");
			}else{

				mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCallee", "(Ljava/lang/Object;)V");
			}


			return;
		}


		if(isReference)
		{

			if(!type.equals("java/lang/Object"))
			{
				addToPoplist("popL","()Ljava/lang/Object;",type,"L");
			}else{
				addToPoplist("popL","()Ljava/lang/Object;",null,"L");
			}

			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "pushL", "(Ljava/lang/Object;)V");
		}else{
			addToPoplist("pop"+type,"()"+type,null,type);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "push"+type, "("+type+")V");
		}
	}

	private void addToPoplist(String f,String t,String cast,String desc){
		String[] a={f,t,cast,desc};
		poplist.addLast(a);	
	}



	private void insertLookup(){
		//lookup the corresponding tracer by its id
		mv.visitIntInsn(SIPUSH, tracer.getID());
		mv.visitMethodInsn(INVOKESTATIC, "mega/trace/core/Tracer", "getTracer", "(I)Lmega/trace/core/Tracer;");
	}
	
	private void insertBooleanValue(boolean b){
		if(b)
		{
			mv.visitInsn(ICONST_1);
		}else{
			mv.visitInsn(ICONST_0);
		}
	}
}

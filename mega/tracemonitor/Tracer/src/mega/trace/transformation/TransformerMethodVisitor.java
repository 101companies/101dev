package mega.trace.transformation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import mega.trace.core.Tracer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TransformerMethodVisitor extends MethodVisitor implements Opcodes{
	private final Tracer tracer;
	private final String tracerpath;
	private final String classname;
	private final String name;
	private final String superName;

	private LinkedList<String[]> poplist;

	private boolean staticmethod;


	private static final int FLAG_STATIC=1;
	private static final int FLAG_CONSTRUCTOR=2;
	private static final int FLAG_INTERFACE=4;
	private static final int FLAG_VIRTUAL=8;


	private final boolean useDUPNEWMethod=false; //UPDATE: KEEP SET TO FALSE!!! IGNORE://I wasn't sure if it is secure in every case to DUP immediately after NEW to get the "callee" object for AfterConstructorCall or if it is better to do this within BeforeConstructorCall. Problem is: if BeforeConstructorCall injection is disabled it would _still_ be necessary to PUSH the whole stack. 

	public TransformerMethodVisitor(final MethodVisitor mv,String thisname,String superName,Tracer tracer,String classname,HashMap<String,String> fieldsigmap,boolean isStatic) {
		super(ASM4, mv);
		this.tracer=tracer;
		this.tracerpath=tracer.getClass().getName().replace('.', '/');
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
			insertMemberVariableCall(name,opcode==PUTSTATIC,desc,owner);

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
			insertLocalVariableCall(var,info);

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
		mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "dispatchEvent", "(Lmega/trace/event/TraceEvent;)V");
	}

	private void insertLocalVariableCall(int index,String signature){

		insertLookup();

		if(signature.equals("L?")){
			insertLVarPushArgumentWorkaround();
		}else{			
			insertPushArguments("("+signature+")",true,false,classname);
		}

		insertPushSource(staticmethod,classname);
		mv.visitInsn(DUP); 

		mv.visitTypeInsn(NEW, "mega/trace/event/LocalVariableAssignmentEvent");
		mv.visitInsn(DUP);


		mv.visitLdcInsn(""+index);
		mv.visitLdcInsn(signature);


		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/LocalVariableAssignmentEvent", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V");

		insertDispatcherCall();

		insertPopArguments(true);

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


	@SuppressWarnings("unused")
	private void insertBeforeMethodCall(String methodName,String desc,String owner,int flags)
	{

		String event=null;

		if(isSuperConstructorCall(methodName,desc,owner))
			return;

		if((flags&FLAG_CONSTRUCTOR)!=0){
			if(!tracer.injectBeforeConstructorCall(classname)){
				if(!useDUPNEWMethod && tracer.injectAfterConstructorCall(classname)){
					//ugly!
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


		insertLookup();

		insertPushArguments(desc,((flags&FLAG_STATIC)+(flags&FLAG_CONSTRUCTOR))!=0,((flags&FLAG_CONSTRUCTOR)!=0) && !useDUPNEWMethod && tracer.injectAfterConstructorCall(classname),owner);
		insertPushSource(staticmethod,classname);
		mv.visitInsn(DUP); 

		mv.visitTypeInsn(NEW, "mega/trace/event/"+event);
		mv.visitInsn(DUP);



		mv.visitLdcInsn(methodName);
		mv.visitLdcInsn(owner);

		insertBooleanValue((flags&FLAG_STATIC)!=0);

		mv.visitLdcInsn(desc);

		mv.visitMethodInsn(INVOKESPECIAL, "mega/trace/event/"+event, "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V");

		insertDispatcherCall();

		//reference to tracer still on top!

		insertPopArguments((flags&FLAG_STATIC)!=0);

	}



	@SuppressWarnings("unused")
	private void insertAfterMethodCall(String methodName,String desc,String owner,int flags)
	{
		String event=null;

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

		if((flags&FLAG_CONSTRUCTOR)!=0){
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "setCallee", "(Ljava/lang/Object;)V");
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
		// *   TODO: currently missing type information of local vars. Can be achieved by checking visitLocalVariable()
		// *   but this is called after visitMethod... => apply two MethodVisitors?
		//
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

				if(c=='L'){
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
				mv.visitInsn(SWAP); //put tracer reference on top again


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
			mv.visitInsn(DUP_X1); 
			mv.visitInsn(SWAP); 
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
				addToPoplist("popL","()Ljava/lang/Object;",type);
			}else{
				addToPoplist("popL","()Ljava/lang/Object;",null);
			}

			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "pushL", "(Ljava/lang/Object;)V");
		}else{
			addToPoplist("pop"+type,"()"+type,null);
			mv.visitMethodInsn(INVOKEVIRTUAL, "mega/trace/core/Tracer", "push"+type, "("+type+")V");
		}
	}

	private void addToPoplist(String f,String t,String cast){
		String[] a={f,t,cast};
		poplist.addLast(a);	
	}



	private void insertLookup(){
		switch(Tracer.getLinkMethod()){
		case TLINK_STATIC:
			insertStaticLookup();
			break;
		case TLINK_IDMAP:
			insertIDMAPLookup();
			break;
		}
	}

	private void insertIDMAPLookup(){
		mv.visitFieldInsn(GETSTATIC, "mega/trace/core/Tracer", "map", "Ljava/util/HashMap;");
		mv.visitTypeInsn(NEW, "java/lang/Integer");
		mv.visitInsn(DUP);
		mv.visitIntInsn(BIPUSH, tracer.getuid());
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitTypeInsn(CHECKCAST, tracerpath);

	}

	private void insertStaticLookup(){
		mv.visitFieldInsn(GETSTATIC, "mega/trace/core/Tracer", "staticTracer", "Lmega/trace/core/Tracer;");
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

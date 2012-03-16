package mega.trace.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import mega.trace.transformation.ClassBytecodeTransformer;

public class TraceClassLoader extends ClassLoader{

	private Hashtable<String,Class<?>> table = new Hashtable<String,Class<?>>();


	public TraceClassLoader(ClassLoader parent)
	{
		super(parent);
	}	


	public synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException
	{
		
		return loadClassByPath(name,name.replace('.', File.separatorChar) + ".class",resolve);

	} 

	public synchronized Class<?> loadClassByPath(final String name, final String resource, final boolean resolve) throws ClassNotFoundException
	{
		
	
		
		//System.out.println("loadClass: "+name);	

		Class<?> c = (Class<?>)table.get(name);
		if(c!=null)
			return c;

		c=super.findLoadedClass(name);
		if(c!=null){
			//	System.out.println("Class already loaded by parent.");
				if (resolve) {
					resolveClass(c);
					} 
			return c;
		}
	
		if(TraceConfiguration.prohibitedPackage(name))
			{
			
			//	System.out.println("prohibited package found, using findSystemClass()..\n");
				c=super.findSystemClass(name);
				if(c!=null)
				{
					return c;
				}else{
					throw new ClassNotFoundException();
				}
			}
			
	//	System.out.println("Class new! Name="+name);

		InputStream is = getResourceAsStream(resource);
		c = this.loadClassFromStream(name, is);

		if (resolve) { 
			resolveClass(c); 
		} 


		table.put(name, c);

		//System.out.println("Class done. Name="+name+"\n");
		
		return c;

	}

	private synchronized Class<?> loadClassFromStream(final String name, InputStream is) throws ClassNotFoundException
	{
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try{
			do{
				int next = is.read();
				if(next==-1)
					break;
				bos.write(next);
			}while(true);
			bos.flush();

		} catch (IOException e) {throw new ClassNotFoundException();}

		byte[] bcode=bos.toByteArray();
	
		
		for(Tracer t:TraceConfiguration.getTracerList()){
			
			if(t.checkTransform(name)){//System.out.println("TRANSFORM "+name);
				bcode=new ClassBytecodeTransformer(t).transformClassBytecode(bcode);}
		}
	
		return defineClass(name, bcode, 0, bcode.length);
	}
	
	
}



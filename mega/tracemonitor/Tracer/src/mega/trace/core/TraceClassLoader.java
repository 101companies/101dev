package mega.trace.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import mega.trace.transformation.ClassBytecodeTransformer;

public class TraceClassLoader extends ClassLoader{

	private Hashtable<String,Class<?>> table = new Hashtable<String,Class<?>>();
	private ClassBytecodeTransformer classbct;
	private final Tracer tracer;


	public TraceClassLoader(ClassLoader parent,Tracer tracer)
	{
		super(parent);
		classbct=new ClassBytecodeTransformer(tracer);
		this.tracer=tracer;
	}	


	public synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException
	{

		return loadClassByPath(name,name.replace('.', File.separatorChar) + ".class",resolve,tracer.checkTransform(name));

	} 

	public synchronized Class<?> loadClassByPath(final String name, final String resource, final boolean resolve,final boolean inject) throws ClassNotFoundException
	{

		//System.out.println("loadClass: "+name);	

		Class<?> c = (Class<?>)table.get(name);
		if(c!=null)
			return c;


		//c=super.findSystemClass(name);
		//System.out.println("System class found.");
		c=super.findLoadedClass(name);
		if(c!=null){
			//	System.out.println("Class already loaded by parent.");
			return c;
		}

		if(name.startsWith("java.") || !inject) //do not transform java.* classes
		{
			//System.out.println("java.* class found / or donotinject==true. Passing to parent...");

			c=super.findSystemClass(name);
			if(c!=null)
			{
				return c;
			}else{
				throw new ClassNotFoundException();
			}
		}
		// System.out.println("Class new! Transforming...");

		InputStream is = getResourceAsStream(resource);
		c = this.loadClassFromStream(name, is);

		if (resolve) { 
			resolveClass(c); 
		} 


		table.put(name, c);

		//  System.out.println("\nClass successfully loaded and transformed using custom Class Loader. Class Information:");
		//  System.out.println("Name: "+c.getName());

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

		byte[] bcode=classbct.transformClassBytecode(bos.toByteArray());
		return defineClass(name, bcode, 0, bcode.length);
	}
}



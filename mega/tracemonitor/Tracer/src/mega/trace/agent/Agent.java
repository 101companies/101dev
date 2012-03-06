package mega.trace.agent;


import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

import mega.test.data.FullTestTracer;
import mega.trace.core.Tracer;

/*
 * support for javaagent
 * usage: 
 *   1. edit the 'Agent' class source code and apply one or more Tracer; see below within premain()
 *   2. export the project's src folder as a jar with a special manifest file:
 *   Open src -> export and click java -> JAR File.
 *   Call the file something like tracer.jar
 *   Go next until the JAR Manifest Specification page.
 *   Choose 'Use existing manifest from workspace'. Click the browse button and choose
 *   the file called 'manifest' which comes with the project.
 *   
 *   3. to apply the created tracer.jar to a program place asm-all-4.0.jar in the same
 *   folder as tracer.jar and execute the target program(e.g. program.jar):
 *   java -javaagent:tracer.jar -jar program.jar
 */


public class Agent {

	private static final String asmlib="asm-all-4.0.jar";

	public static void premain(String args, Instrumentation i) {
		
		try {
			i.appendToSystemClassLoaderSearch(new JarFile(asmlib));
		} catch (IOException e) {
			System.out.println("Error while adding '"+asmlib+"' to SystemClassLoaderSearch");
			System.out.println(e.toString());
			System.exit(1);
		}
		
		/*apply tracer:	
		* 	Tracer mytracer=new YOUR_TRACER_GOES_HERE();
		* 	i.addTransformer(mytracer);
		*/
		Tracer tracer=new FullTestTracer();
		i.addTransformer(new AgentTransformer(tracer));

	}
	
	
}

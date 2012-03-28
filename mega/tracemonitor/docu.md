# Trace Monitor for the Practinar *Modeling Programming Technologies* 11/12 #
## Introduction ##
Within the scope of the project pratical of the _Practinar_ _Modeling_ _Programming_ _Technologies_ in WS11/12 this simple trace monitor for the [101implementation for jaxbComposition](http://101companies.org/index.php/101implementation:jaxbComposition) emerged. As a first milestone two technologies were examined and evaluated [here](https://github.com/101companies/101dev/blob/master/mega/tracemonitor/Evaluation.md). [ASM](http://asm.ow2.org/) was chosen. The second and third(final) milestones are embedded in the content of this documentation.
## Usage ##
Applying the tracer using JavaAgent in eclipse:
To apply the tracer to a program it must be registered within the static trace.core.TraceConfiguration class.
Then the whole source folder of the Tracer project must be exported into a .jar file(now referenced as tracer.jar) using a custom manifest file containing the Premain-Class attribute as in the current manifest file. This file must be chosen during the export configuration.
The created tracer.jar and the asm-all-4.0.jar have to be put into the same folder as the target program.
This can also be done by using eclipse's import->file system.
Within the program's properties the launch configuration has to be changed to pass an additional argument to the virtual machine:
"-javaagent:tracer.jar"
The program can then be executed as usual.

*Hint: Passing "-javaagent:tracer.jar=verbose" will additionally also apply the mega.test.JUnit.tracer.FullTestTracer, which will output event information to System.out.*

Without using eclipse, the command line for tracing a program would look like:
*java -javaagent:tracer.jar -jar someprogram.jar*
Always make sure asm-all-4.0.jar is in the same folder as tracer.jar
## Implementation ##
Structure:
![structure.png](https://github.com/101companies/101dev/raw/master/mega/tracemonitor/structure.png)
Flow:
![flow.png](https://github.com/101companies/101dev/raw/master/mega/tracemonitor/flow.png)
## Utilization ##
ASM is a bytecode engineering framework. Therefore, additional (tracing-)code is to be injected at load time to pass information to the trace monitor. At this point, a [java agent](http://docs.oracle.com/javase/6/docs/api/java/lang/instrument/package-summary.html) was used to add a transformer (containing the tracer) to the instrumentation using the premain (see mega.trace.agent.Agent). At this point ASM libraries are imported.
The AgentTransformer class ensures that transformations are done only on permitted packages (since java's standard libraries are protected) using the static method TraceConfiguration.prohibitedPackage. In addition, custom packages can be excluded from tracing via mega.trace.core.Tracer.traceClass.
## Transformation ##
ASM's transformation is implemented in the mega.trace.tranformation.ClassByteTransformer class using its default pattern including ClassReader, ClassWriter and the actual transformer mega.trace.transformation.TransformerClassVisitor.
The TransformerClassVisitor implements necessary methods for visiting a class, its fields and its methods to transform it. Class name and super class name, field names and field types are held here and used to feed a mega.trace.transformation.TransformerMethodVisitor.
This visitor implements all bytecode transformations for tracing support using ASM's bytecode injection by case discrimination. Depending on what type of event (constructor/method call, variable assignment) occurs, relevant information (such as arguments or type) is duplicated and - as well as a mega.trace.event - passed to the trace monitor using its mega.trace.core.Tracer.dispatchEvent method. At this point the execution of the program is interrupted and handling code within the tracer can be executed.
## Tracing ##
The mega.trace.core.Tracer class is used to handle dispatchEvent calls by passing the event to specific methods. These methods can be overloaded by extending the Tracer class for individual purposes and are called appropriately. At this point the argument stack has to be cloned in order to keep it if needed (since it is emptied after dispatchEvent).
## Trace Monitoring ##
In the current implementation, the trace monitoring is added as a ShutdownHook at the end of execution (see mega.test.JAXB.JAXBTestTracer) and recursively checks for conformance step-wise (see mega.test.JAXB.JAXBMapper) based on the resulting trace. The pattern from the [old megamodeling paper](http://softlang.uni-koblenz.de/mega/csmr12.pdf) was used. Object identification is realized using references (since object transformation is omitted). The result of this process is a mega.test.JAXB.MatchingSet containing all bindings.
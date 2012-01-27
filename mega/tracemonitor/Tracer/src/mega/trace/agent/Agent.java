package mega.trace.agent;

import java.lang.instrument.Instrumentation;

import mega.trace.core.Tracer;

/*
 * support for java agent
 * 
 * >>>NOT TESTED!<<<
 * 
 * 
 * manifest file needed. manifest file content:
	Manifest-Version: 1.0
	Premain-Class: mega.trace.agent.Agent
	Boot-Class-Path: asm-all-4.0.jar
*/

//TODO: testing
//TODO: create and add manifest

public class Agent {
	public static void premain(String args, Instrumentation i) {
		
		Tracer tracer=null; //YOUR TRACER GOES HERE
		
		i.addTransformer(new AgentTransformer(tracer));
	}
}

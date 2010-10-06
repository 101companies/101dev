package org.softlang.swing;


import org.softlang.serialization.SerializationTool;

public class Test {

	public static void main(String[] args) {
		SerializationTool SerializationTool = new SerializationTool();
		new GUI(SerializationTool.load("sampleCompany"));
	}
}
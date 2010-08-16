package org.softlang.code;

import java.io.File;

public class Constants {

	// Common prefix for xml data
	public final static String xmlData =
		"org" + File.separatorChar + 
		"softlang" + File.separatorChar + 
		"data" + File.separatorChar + 
		"xml" + File.separatorChar;

	// Documents with company data
	public final static String Year2008 = xmlData + "Year2008.xml";
	public final static String Year2009 = xmlData + "Year2009.xml";
	public final static String Year2010 = xmlData + "Year2010.xml";

	// Reference to schema relative to XML files
	public final static String Company =
		".." + File.separatorChar + "xsd" + File.separatorChar + "company.xsd";
	
}

package org.softlang.jaxb;

import org.softlang.company.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

import javax.xml.bind.*;
import javax.xml.stream.*;

public class Tests {

	private static JAXBContext jaxbContext;
	
	public static void initializeJaxbContext()
	throws JAXBException
	{
		if (jaxbContext==null)
			jaxbContext =
				JAXBContext.newInstance("org.softlang.company");
	}
		
	public static Company readFile(String file)
	throws JAXBException 
	{
		initializeJaxbContext();
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		return (Company) unMarshaller.unmarshal(new File(file));		
	}

	public static void writeFile(String file, Company c)
	throws 	JAXBException,
			FileNotFoundException,
			XMLStreamException 
	{
		initializeJaxbContext();
	    OutputStream output = new FileOutputStream("Year2009.xml");
		Marshaller marshaller = jaxbContext.createMarshaller();
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(output);
		marshaller.marshal(c, writer); // need a stream writer that does indentation		
	}
	
	@Test
	public void testAll() 
	throws 
		JAXBException,
		FileNotFoundException,
		XMLStreamException 
	{
		Company c;
		double total;
		c = readFile("Year2008.xml");		
	    total = Total.aggregate(c);
	    assertEquals(399747, total, 0);
	    Cut.transform(c);
		writeFile("Year2009.xml", c);		
		c = readFile("Year2009.xml");		
	    total = Total.aggregate(c);
	    assertEquals(199873.5, total, 0);
	}
}

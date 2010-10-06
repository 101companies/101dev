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
		
	public static Company readFile(File input)
	throws JAXBException 
	{
		initializeJaxbContext();
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		return (Company) unMarshaller.unmarshal(input);		
	}

	public static void writeFile(File output, Company c)
	throws 	JAXBException,
			FileNotFoundException,
			XMLStreamException 
	{
		initializeJaxbContext();
	    OutputStream os = new FileOutputStream(output);
		Marshaller marshaller = jaxbContext.createMarshaller();
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(os);
		marshaller.marshal(c, writer); // need a stream writer that does indentation		
	}
	
	@Test
	public void testAll() 
	throws 
		JAXBException,
		FileNotFoundException,
		XMLStreamException 
	{
		File sample = new File("sampleCompany.xml");
		File tmp = new File("sampleCompany.tmp");
		Company c = readFile(sample);		
		double total = Total.getTotal(c);
	    assertEquals(399747, total, 0);
	    Cut.transform(c);
		writeFile(tmp, c);		
		c = readFile(tmp);		
	    total = Total.getTotal(c);
	    assertEquals(199873.5, total, 0);
	    tmp.delete();
	}
}

package org.softlang.jaxb;

import org.softlang.company.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.junit.Test;
import static org.junit.Assert.*;

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
		marshaller.marshal(c, writer); // TODO: need a stream writer that does indentation		
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
		double total = Total.total(c);
	    assertEquals(399747, total, 0);
	    Cut.cut(c);
		writeFile(tmp, c);		
		c = readFile(tmp);		
	    total = Total.total(c);
	    assertEquals(199873.5, total, 0);
	    tmp.delete();
	}
}

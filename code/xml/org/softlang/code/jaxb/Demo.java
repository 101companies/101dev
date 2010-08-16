package org.softlang.code.jaxb;

import org.softlang.company.*;
import static org.junit.Assert.*;
import static org.softlang.code.Constants.*;

import java.io.*;
import javax.xml.bind.*;
import javax.xml.stream.*;

public class Demo {
	
	public static void main (String[] args) throws Exception {

		// Unmarshal company 
		JAXBContext jaxbContext = JAXBContext.newInstance("org.softlang.company");
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		Company c = (Company) unMarshaller.unmarshal(new File(Year2008));
	
		// Total salaries, and cut them
	    double total = Total.compute(c);
	    assertEquals(399747, total, 0);
	    Cut.visit(c);
	    total = Total.compute(c);
	    assertEquals(199873.5, total, 0);
		
		// Marshal company
		OutputStream output = new FileOutputStream(Year2009);
		Marshaller marshaller = jaxbContext.createMarshaller();
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(output);
		marshaller.marshal(c, writer); // need a stream writer that does indentation
	
    }
}

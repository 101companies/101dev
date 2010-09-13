package org.softlang.code.sax;

import static org.softlang.code.Constants.*;

import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Cut extends DefaultHandler {

	private static XMLOutputFactory factory = XMLOutputFactory.newInstance();
	private static XMLStreamWriter writer;
	private boolean isSalary = false;

	@Override
	public void startDocument() throws SAXException {
		try {
			writer.writeStartDocument();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			writer.writeEndDocument();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void startElement(String uri, String name, String qName,
			Attributes atts) {

		try {
			isSalary = (uri.equals("http://www.company.com") && name
					.equals("salary"));
			writer.writeStartElement(name);
			for (int i = 0; i < atts.getLength(); i++) {
				writer.writeAttribute(atts.getLocalName(i), atts.getValue(i));
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		isSalary = false;
		try {
			writer.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		try {
			if (isSalary) {
				String str = String.valueOf(ch, start, length);
				double salary = Double.parseDouble(str);
				writer.writeCharacters(String.valueOf(salary / 2));
			} else {
				writer.writeCharacters(String.valueOf(ch, start, length));
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		Cut handler = new Cut();
		writer = factory.createXMLStreamWriter(new FileOutputStream(Year2009));
		SAXUtilities.parse(handler, Year2008);
	}
}
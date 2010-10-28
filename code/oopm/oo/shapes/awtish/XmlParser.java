// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser extends DefaultHandler
{
	private Figure figure = new Figure();
	private StringBuffer text = null;
	private Integer x = null;
	private Integer y = null;
	private Integer width = null;
	private Integer height = null;
	private Integer radius = null;

	public static Figure parse(String pathname)
	{
		File file = new File(pathname);
		XmlParser parser = new XmlParser();
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(file, parser);
		} catch( Throwable t ) {
			t.printStackTrace();
		}
		return parser.figure;
	}

	public void startElement(
			String namespaceURI,
            String localName,
            String qName,
            Attributes attrs ) {
		text = null;
	}

	public void endElement( 
			String namespaceURI,
	        String localName,
	        String qName ) {

		if (qName.equals("rectangle")) {
			figure.createRectangle(x,y,width,height);
			clean();
			return;
		}

		if (qName.equals("circle")) {
			figure.createCircle(x,y,radius);
			clean();
			return;
		}
		
		if (qName.equals("x")) {
			x = Integer.parseInt(text.toString());
			text = null;
			return;
		}

		if (qName.equals("y")) {
			y = Integer.parseInt(text.toString());
			text = null;
			return;
		}

		if (qName.equals("width")) {
			width = Integer.parseInt(text.toString());
			text = null;
			return;
		}
		
		if (qName.equals("height")) {
			height = Integer.parseInt(text.toString());
			text = null;
			return;
		}

		if (qName.equals("radius")) {
			radius = Integer.parseInt(text.toString());
			text = null;
			return;
		}	
		
	}
	
	public void characters( char[] buf, int offset, int len) {
		String s = new String( buf, offset, len );
	    if( text == null )
	    	text = new StringBuffer( s );
	    else
	    	text.append( s );
	}
	
	private void clean() {
		x = null;
		y = null;
		width = null;
		height = null;
		radius = null;
	}
}

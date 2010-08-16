package org.softlang.code.sax;

import static org.junit.Assert.*;
import static org.softlang.code.Constants.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Compute the total all salaries of a company
 */
public class Total extends DefaultHandler
{
	
	private boolean isSalary = false;
	private double total = 0;
		
	/**
	 * Handle "start element"
	 */
    public void startElement(
    		String uri, 
    		String name,
			String qName,
			Attributes atts)
    {
    	isSalary = 
    		(uri.equals("http://www.company.com") 
    				&& name.equals("salary"));
    }
	
    /**
     * Handle "end element"
     */
    public void endElement(
    		String uri,
    		String name,
    		String qName)
    {
    	isSalary = false;
    }
    
	/**
	 * Handle "characters"
	 */
    public void characters(
    		char ch[],
    		int start,
    		int length)
    {
    	if (isSalary) {
    		String str = String.valueOf(ch, start, length);
    		double salary = Double.parseDouble(str);
    		total += salary;
	    }
    }

	public static void main (String args[]) throws Exception
	{
		Total handler = new Total();
		SAXUtilities.parse(handler, Year2008);
	    assertEquals(399747, handler.total, 0);		
	}
}
// (C) 2008 Ralf Laemmel
// Read content from a URL and echo it to standard output.
// The URL is to be passed via the command line.
// This example is inspired by the Java Tutorial.
// See: http://java.sun.com/docs/books/tutorial/networking/urls/readingURL.html

package oo.file;

import java.io.*;
import java.net.*;

public class Wget {

	public static void main(String[] args) {
        try {
        	
        	// Obtain URL from command line
        	URL url = new URL(args[0]); 

        	// Construct a buffered reader for the input
        	BufferedReader br = 
            	new BufferedReader(					// Make the reader a buffered reader
            		new InputStreamReader(			// Create a reader from the stream
            				url.openStream())); 	// Create an input stream from URL
            
            // Read from the input line by line and echo
            String line; 
            while ((line = br.readLine()) != null) { 
                System.out.println(line); 
            } 
            
            // Done
            br.close(); 
    
        } catch (MalformedURLException me) { 
            System.out.println(me); 
    
        } catch (IOException ioe) { 
            System.out.println(ioe); 
        } 
	}
}

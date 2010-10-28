// (C) 2009 Ralf LŠmmel

package oo.file;

import java.io.*;

/**
 * Split up an input file into pieces each 10 lines long.
 * The program takes several command-line arguments.
 * 1. The name of the input file.
 * 2. The prefix of names of all output files.
 * 3. The extension of names of all output files.
 */
public class Split {
	
	public static void main(String[] args) {
		
		File input = new File(args[0]);

		try {
			
        	// Construct a buffered reader for the input
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
						new FileInputStream(input)));

            // Process the input line by line
			int linecount = 0;
			int filecount = 0;
            String line; 
            File output = null;
            BufferedWriter bw = null;
            while ((line = br.readLine()) != null) {
            	if (linecount==0) {
            		filecount++;
                	output = new File(args[1] + filecount + "." + args[2]);
        			bw = new BufferedWriter(
        					new OutputStreamWriter(
        						new FileOutputStream(output)));            		
            	}
            	bw.write(line + "\n");
            	linecount++;
            	if (linecount==10) {
            		bw.close();
            		linecount=0;
            	}
            }
            if (linecount!=0)
            	bw.close();

    	    // Done with the input
	    	br.close();
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("Input file missing");
		}
		catch (IOException ioe) {
			System.err.println("Some I/O error");
		}
	}
}

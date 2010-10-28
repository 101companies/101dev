// (C) 2008 Ralf Laemmel

package oo.file;

import java.io.*;

/**
 * Read content from a file and echo it to standard output. 
 * The file name is to be passed via the command line.
 */
public class More {

	public static void main(String[] args) 
						throws IOException {

		// Obtain file name from command line
		File file = new File(args[0]);

		// Construct a buffered reader for the input
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(file)));

		// Read from the input line by line and echo
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		// Done
		br.close();
	}
}

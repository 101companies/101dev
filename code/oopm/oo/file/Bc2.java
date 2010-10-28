// (C) 2009 Ralf LŠmmel

package oo.file;

import java.io.*;

/**
 * Read two ints from a file and write the sum to another file
 */
public class Bc2 {

	public static void main(String[] args) {

		// Compose file names for input and output
		File input = new File("oo" + File.separatorChar + "file"
				+ File.separatorChar + "input-bc.txt");
		File output = new File("oo" + File.separatorChar + "file"
				+ File.separatorChar + "output-bc.txt");

		try {
			// Read the input file with a buffered reader
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(input)));

			// Read two lines and convert them into ints
			int value1 = Integer.parseInt(br.readLine());
			int value2 = Integer.parseInt(br.readLine());

			// Done with the input
			br.close();

			// Compute result
			int sum = value1 + value2;

			// Write the output file with a buffered writer
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(output)));

			// Write result
			bw.write(value1 + " + " + value2 + " = " + sum);

			// Done with the output
			bw.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Input file missing");
		} catch (IOException ioe) {
			System.err.println("Some I/O error");
		} catch (NumberFormatException nfe) {
			System.err.println("Content of input file cannot be converted");
		}
	}
}

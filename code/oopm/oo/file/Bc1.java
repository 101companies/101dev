package oo.file;

import java.io.*;

/**
 * Read two values from an input file, add them, and print the sum.
 */
public class Bc1 {

	public static void main(String[] args) throws IOException {

		File input = new File(args[0]);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(input)));

		// Read two lines and convert them into ints
		int value1 = Integer.parseInt(br.readLine());
		int value2 = Integer.parseInt(br.readLine());
		
		br.close();

		// Compute and output result
		int sum = value1 + value2;
		System.out.println(value1 + " + " + value2 + " = " + sum);			
	}
}
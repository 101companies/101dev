// (C) 2009 Ralf Laemmel

package data.array;

/**
 * Provide a String type that provides efficient append.
 * Please note: this implementation is a bit naive. 
 */
public class StringBuilder {
	
	// Use an extensible array underneath
	private Array<String> strings = new Array<String>();
	
	/**
	 *  Append another string efficiently
	 */
	public void append(String s) {
		strings.append(s);
	}
	
	/**
	 *  Convert StringBuilder to String
	 */
	public String toString() {
		
		// Determine cummulative length of result
		int l = 0;
		for (int i = 0; i < strings.getLength(); i++)
			l += ((String)strings.getAt(i)).length();
		
		// Allocate array for result
		char[] chars = new char[l];
		
		// Copy array items into resulting array
		int k = 0;
		for (int i = 0; i < strings.getLength(); i++) {
			String s = (String)strings.getAt(i);
			for (int j = 0; j < s.length(); j++)
				chars[k++] = s.charAt(j);
		}
		
		return new String(chars);
	}
		
}

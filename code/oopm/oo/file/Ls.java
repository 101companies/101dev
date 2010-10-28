// (C) 2008 Ralf Laemmel

package oo.file;

import java.io.*;


/**
 * List the directories recursively.
 * The root directory is to be passed via the command line.
 */
public class Ls {

	public static void listDirectories(File dir) {
	    File[] children = dir.listFiles(new NonHiddenFilter());	    
	    if (children != null) {
	        for (int i=0; i<children.length; i++) {
	            File file = children[i];
	            System.out.println(file.toString());
	            if (file.isDirectory())
	            	listDirectories(file);	            
	        }
	    }	    	    		
	}
	
	public static void main(String[] args) {

		File root = new File(args.length == 0 ? "." : args[0]);
		listDirectories(root);
		
	}

}

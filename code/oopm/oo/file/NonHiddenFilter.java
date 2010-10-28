package oo.file;

import java.io.*;

/** 
 * A filter that only accepts non-hidden files.
 * A more sophisticated programmer would use an inner class.
 * The use of the singleton pattern also comes to mind.
 */
public class NonHiddenFilter implements FileFilter {
    public boolean accept(File f) {
        return !f.isHidden();
    }
}

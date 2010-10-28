// (C) 2009 Ralf Laemmel

package structo.processor.virtual;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.HashMap;
import org.antlr.runtime.*;
import structo.types.virtual.*;

public class Test {
    public static void main(String[] args) throws Exception {
    	
    	// Associate the scanner for reading
    	Read.scanner = new Scanner(System.in);

    	// Set up parser and parse
    	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(new File(args[0])));
        StructoLexer lexer = new StructoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StructoParser parser = new StructoParser(tokens);
        Statement s = parser.program();
        
        // Initialize memory and run interpreter
        HashMap<String, Integer> memory = new HashMap<String, Integer>();
        s.execute(memory);
    }
}

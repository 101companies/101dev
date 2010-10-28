// (C) 2009 Ralf Laemmel

package structo.processor.visitor;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import org.antlr.runtime.*;
import structo.types.visitor.*;

public class Test {
    public static void main(String[] args) throws Exception {

    	// Set up parser and parse
    	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(new File(args[0])));
        StructoLexer lexer = new StructoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StructoParser parser = new StructoParser(tokens);
        Statement s = parser.program();

        // Perform checks
        Checker c = new Checker();
        s.accept(c);
        
        // Set up and run interpreter 
        Interpreter i = new Interpreter();
        i.scanner = new Scanner(System.in);
        s.accept(i);
    }
}

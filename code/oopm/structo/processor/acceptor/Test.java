// (C) 2009 Ralf Laemmel

package structo.processor.acceptor;

import java.io.File;
import java.io.FileInputStream;

import org.antlr.runtime.*;

public class Test {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(new File(args[0])));
        StructoLexer lexer = new StructoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StructoParser parser = new StructoParser(tokens);
        parser.program();
    }
}

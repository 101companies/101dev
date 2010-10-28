// $ANTLR 3.0.1 Structo.g 2010-02-09 05:18:21

package structo.processor.acceptor;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"unused", "cast"})
public class StructoParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "WS", "COMMENT", "'{'", "'}'", "'int'", "';'", "'read'", "'write'", "'='", "'if'", "'else'", "'while'", "'('", "')'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", "'*'", "'/'"
    };
    public static final int WS=6;
    public static final int INT=5;
    public static final int ID=4;
    public static final int COMMENT=7;
    public static final int EOF=-1;

        public StructoParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Structo.g"; }



    // $ANTLR start program
    // Structo.g:14:1: program : statements ;
    public final void program() throws RecognitionException {
        try {
            // Structo.g:14:8: ( statements )
            // Structo.g:14:10: statements
            {
            pushFollow(FOLLOW_statements_in_program25);
            statements();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end program


    // $ANTLR start statements
    // Structo.g:16:1: statements : ( statement | '{' ( statement )* '}' );
    public final void statements() throws RecognitionException {
        try {
            // Structo.g:16:12: ( statement | '{' ( statement )* '}' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ID||LA2_0==10||(LA2_0>=12 && LA2_0<=13)||LA2_0==15||LA2_0==17) ) {
                alt2=1;
            }
            else if ( (LA2_0==8) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("16:1: statements : ( statement | '{' ( statement )* '}' );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Structo.g:16:14: statement
                    {
                    pushFollow(FOLLOW_statement_in_statements33);
                    statement();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Structo.g:16:26: '{' ( statement )* '}'
                    {
                    match(input,8,FOLLOW_8_in_statements37); 
                    // Structo.g:16:30: ( statement )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==ID||LA1_0==10||(LA1_0>=12 && LA1_0<=13)||LA1_0==15||LA1_0==17) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Structo.g:16:30: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_statements39);
                    	    statement();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    match(input,9,FOLLOW_9_in_statements42); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end statements


    // $ANTLR start statement
    // Structo.g:18:1: statement : ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cexpression statements ( 'else' statements )? | 'while' cexpression statements );
    public final void statement() throws RecognitionException {
        try {
            // Structo.g:18:10: ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cexpression statements ( 'else' statements )? | 'while' cexpression statements )
            int alt4=6;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt4=1;
                }
                break;
            case 12:
                {
                alt4=2;
                }
                break;
            case 13:
                {
                alt4=3;
                }
                break;
            case ID:
                {
                alt4=4;
                }
                break;
            case 15:
                {
                alt4=5;
                }
                break;
            case 17:
                {
                alt4=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("18:1: statement : ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cexpression statements ( 'else' statements )? | 'while' cexpression statements );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Structo.g:19:3: 'int' ID ';'
                    {
                    match(input,10,FOLLOW_10_in_statement70); 
                    match(input,ID,FOLLOW_ID_in_statement72); 
                    match(input,11,FOLLOW_11_in_statement74); 

                    }
                    break;
                case 2 :
                    // Structo.g:20:4: 'read' ID ';'
                    {
                    match(input,12,FOLLOW_12_in_statement79); 
                    match(input,ID,FOLLOW_ID_in_statement81); 
                    match(input,11,FOLLOW_11_in_statement83); 

                    }
                    break;
                case 3 :
                    // Structo.g:21:4: 'write' aexpression ';'
                    {
                    match(input,13,FOLLOW_13_in_statement88); 
                    pushFollow(FOLLOW_aexpression_in_statement90);
                    aexpression();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_statement92); 

                    }
                    break;
                case 4 :
                    // Structo.g:22:6: ID '=' aexpression ';'
                    {
                    match(input,ID,FOLLOW_ID_in_statement99); 
                    match(input,14,FOLLOW_14_in_statement101); 
                    pushFollow(FOLLOW_aexpression_in_statement103);
                    aexpression();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_statement105); 

                    }
                    break;
                case 5 :
                    // Structo.g:23:4: 'if' cexpression statements ( 'else' statements )?
                    {
                    match(input,15,FOLLOW_15_in_statement110); 
                    pushFollow(FOLLOW_cexpression_in_statement112);
                    cexpression();
                    _fsp--;

                    pushFollow(FOLLOW_statements_in_statement114);
                    statements();
                    _fsp--;

                    // Structo.g:23:32: ( 'else' statements )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==16) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // Structo.g:23:33: 'else' statements
                            {
                            match(input,16,FOLLOW_16_in_statement117); 
                            pushFollow(FOLLOW_statements_in_statement119);
                            statements();
                            _fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 6 :
                    // Structo.g:24:6: 'while' cexpression statements
                    {
                    match(input,17,FOLLOW_17_in_statement128); 
                    pushFollow(FOLLOW_cexpression_in_statement130);
                    cexpression();
                    _fsp--;

                    pushFollow(FOLLOW_statements_in_statement132);
                    statements();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end statement


    // $ANTLR start cexpression
    // Structo.g:30:1: cexpression : '(' aexpression rop aexpression ')' ;
    public final void cexpression() throws RecognitionException {
        try {
            // Structo.g:30:13: ( '(' aexpression rop aexpression ')' )
            // Structo.g:30:15: '(' aexpression rop aexpression ')'
            {
            match(input,18,FOLLOW_18_in_cexpression145); 
            pushFollow(FOLLOW_aexpression_in_cexpression147);
            aexpression();
            _fsp--;

            pushFollow(FOLLOW_rop_in_cexpression149);
            rop();
            _fsp--;

            pushFollow(FOLLOW_aexpression_in_cexpression151);
            aexpression();
            _fsp--;

            match(input,19,FOLLOW_19_in_cexpression153); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end cexpression


    // $ANTLR start rop
    // Structo.g:35:1: rop : ( '==' | '!=' | '<' | '>' | '<=' | '>=' );
    public final void rop() throws RecognitionException {
        try {
            // Structo.g:35:5: ( '==' | '!=' | '<' | '>' | '<=' | '>=' )
            // Structo.g:
            {
            if ( (input.LA(1)>=20 && input.LA(1)<=25) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_rop0);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end rop


    // $ANTLR start aexpression
    // Structo.g:40:1: aexpression : term ( ( '+' | '-' ) term )* ;
    public final void aexpression() throws RecognitionException {
        try {
            // Structo.g:40:13: ( term ( ( '+' | '-' ) term )* )
            // Structo.g:40:15: term ( ( '+' | '-' ) term )*
            {
            pushFollow(FOLLOW_term_in_aexpression195);
            term();
            _fsp--;

            // Structo.g:40:20: ( ( '+' | '-' ) term )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=26 && LA5_0<=27)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Structo.g:40:21: ( '+' | '-' ) term
            	    {
            	    if ( (input.LA(1)>=26 && input.LA(1)<=27) ) {
            	        input.consume();
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_aexpression198);    throw mse;
            	    }

            	    pushFollow(FOLLOW_term_in_aexpression208);
            	    term();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end aexpression


    // $ANTLR start term
    // Structo.g:41:1: term : factor ( ( '*' | '/' ) factor )* ;
    public final void term() throws RecognitionException {
        try {
            // Structo.g:41:8: ( factor ( ( '*' | '/' ) factor )* )
            // Structo.g:41:10: factor ( ( '*' | '/' ) factor )*
            {
            pushFollow(FOLLOW_factor_in_term219);
            factor();
            _fsp--;

            // Structo.g:41:17: ( ( '*' | '/' ) factor )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=28 && LA6_0<=29)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Structo.g:41:18: ( '*' | '/' ) factor
            	    {
            	    if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
            	        input.consume();
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_term222);    throw mse;
            	    }

            	    pushFollow(FOLLOW_factor_in_term231);
            	    factor();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end term


    // $ANTLR start factor
    // Structo.g:42:1: factor : ( ID | INT | '(' aexpression ')' );
    public final void factor() throws RecognitionException {
        try {
            // Structo.g:42:10: ( ID | INT | '(' aexpression ')' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt7=1;
                }
                break;
            case INT:
                {
                alt7=2;
                }
                break;
            case 18:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("42:1: factor : ( ID | INT | '(' aexpression ')' );", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // Structo.g:42:12: ID
                    {
                    match(input,ID,FOLLOW_ID_in_factor242); 

                    }
                    break;
                case 2 :
                    // Structo.g:42:17: INT
                    {
                    match(input,INT,FOLLOW_INT_in_factor246); 

                    }
                    break;
                case 3 :
                    // Structo.g:42:23: '(' aexpression ')'
                    {
                    match(input,18,FOLLOW_18_in_factor250); 
                    pushFollow(FOLLOW_aexpression_in_factor252);
                    aexpression();
                    _fsp--;

                    match(input,19,FOLLOW_19_in_factor254); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end factor


 

    public static final BitSet FOLLOW_statements_in_program25 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_statements37 = new BitSet(new long[]{0x000000000002B610L});
    public static final BitSet FOLLOW_statement_in_statements39 = new BitSet(new long[]{0x000000000002B610L});
    public static final BitSet FOLLOW_9_in_statements42 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_statement70 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_statement72 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_statement79 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_statement81 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_statement88 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_statement90 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_statement99 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_statement101 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_statement103 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_statement110 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_cexpression_in_statement112 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement114 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_statement117 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_statement128 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_cexpression_in_statement130 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_cexpression145 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_cexpression147 = new BitSet(new long[]{0x0000000003F00000L});
    public static final BitSet FOLLOW_rop_in_cexpression149 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_cexpression151 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_cexpression153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_rop0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_aexpression195 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_set_in_aexpression198 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_term_in_aexpression208 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_factor_in_term219 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_set_in_term222 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_factor_in_term231 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_ID_in_factor242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_factor246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_factor250 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_factor252 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_factor254 = new BitSet(new long[]{0x0000000000000002L});

}
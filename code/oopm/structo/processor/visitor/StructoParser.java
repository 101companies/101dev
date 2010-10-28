// $ANTLR 3.0.1 Structo.g 2010-02-09 05:13:34

package structo.processor.visitor;
import structo.types.visitor.*;


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
    // Structo.g:13:1: program returns [ Statement value ] : statements ;
    public final Statement program() throws RecognitionException {
        Statement value = null;

        Statement statements1 = null;


        try {
            // Structo.g:14:2: ( statements )
            // Structo.g:15:3: statements
            {
            pushFollow(FOLLOW_statements_in_program33);
            statements1=statements();
            _fsp--;

             value = statements1; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end program


    // $ANTLR start statements
    // Structo.g:19:1: statements returns [ Statement value ] : ( statement | '{' ( statement )* '}' );
    public final Statement statements() throws RecognitionException {
        Statement value = null;

        Statement statement2 = null;

        Statement statement3 = null;


        try {
            // Structo.g:20:2: ( statement | '{' ( statement )* '}' )
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
                    new NoViableAltException("19:1: statements returns [ Statement value ] : ( statement | '{' ( statement )* '}' );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Structo.g:21:3: statement
                    {
                    pushFollow(FOLLOW_statement_in_statements55);
                    statement2=statement();
                    _fsp--;

                     value = statement2; 

                    }
                    break;
                case 2 :
                    // Structo.g:24:3: '{' ( statement )* '}'
                    {
                     
                    			Sequence s = new Sequence(); 
                    			value = s;
                    		
                    match(input,8,FOLLOW_8_in_statements72); 
                    // Structo.g:28:8: ( statement )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==ID||LA1_0==10||(LA1_0>=12 && LA1_0<=13)||LA1_0==15||LA1_0==17) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Structo.g:28:11: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_statements77);
                    	    statement3=statement();
                    	    _fsp--;

                    	     s.getStatements().add(statement3); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    match(input,9,FOLLOW_9_in_statements94); 

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
        return value;
    }
    // $ANTLR end statements


    // $ANTLR start statement
    // Structo.g:34:1: statement returns [ Statement value ] : ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cond= cexpression thenBranch= statements ( 'else' elseBranch= statements )? | 'while' cond= cexpression statements );
    public final Statement statement() throws RecognitionException {
        Statement value = null;

        Token ID4=null;
        Token ID5=null;
        Token ID7=null;
        Expression cond = null;

        Statement thenBranch = null;

        Statement elseBranch = null;

        Expression aexpression6 = null;

        Expression aexpression8 = null;

        Statement statements9 = null;


        try {
            // Structo.g:35:2: ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cond= cexpression thenBranch= statements ( 'else' elseBranch= statements )? | 'while' cond= cexpression statements )
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
                    new NoViableAltException("34:1: statement returns [ Statement value ] : ( 'int' ID ';' | 'read' ID ';' | 'write' aexpression ';' | ID '=' aexpression ';' | 'if' cond= cexpression thenBranch= statements ( 'else' elseBranch= statements )? | 'while' cond= cexpression statements );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Structo.g:36:3: 'int' ID ';'
                    {
                    match(input,10,FOLLOW_10_in_statement127); 
                    ID4=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_statement132); 
                    match(input,11,FOLLOW_11_in_statement137); 
                     value = new Declare(ID4.getText()); 

                    }
                    break;
                case 2 :
                    // Structo.g:41:3: 'read' ID ';'
                    {
                    match(input,12,FOLLOW_12_in_statement149); 
                    ID5=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_statement154); 
                    match(input,11,FOLLOW_11_in_statement159); 
                     value = new Read(ID5.getText()); 

                    }
                    break;
                case 3 :
                    // Structo.g:46:3: 'write' aexpression ';'
                    {
                    match(input,13,FOLLOW_13_in_statement171); 
                    pushFollow(FOLLOW_aexpression_in_statement175);
                    aexpression6=aexpression();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_statement180); 
                     value = new Write(aexpression6); 

                    }
                    break;
                case 4 :
                    // Structo.g:51:3: ID '=' aexpression ';'
                    {
                    ID7=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_statement194); 
                    match(input,14,FOLLOW_14_in_statement199); 
                    pushFollow(FOLLOW_aexpression_in_statement204);
                    aexpression8=aexpression();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_statement209); 
                     value = new Assign(ID7.getText(), aexpression8); 

                    }
                    break;
                case 5 :
                    // Structo.g:57:3: 'if' cond= cexpression thenBranch= statements ( 'else' elseBranch= statements )?
                    {
                     Statement s = new Skip(); 
                    match(input,15,FOLLOW_15_in_statement225); 
                    pushFollow(FOLLOW_cexpression_in_statement234);
                    cond=cexpression();
                    _fsp--;

                    pushFollow(FOLLOW_statements_in_statement242);
                    thenBranch=statements();
                    _fsp--;

                    // Structo.g:61:3: ( 'else' elseBranch= statements )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==16) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // Structo.g:61:4: 'else' elseBranch= statements
                            {
                            match(input,16,FOLLOW_16_in_statement248); 
                            pushFollow(FOLLOW_statements_in_statement254);
                            elseBranch=statements();
                            _fsp--;

                             s = elseBranch; 

                            }
                            break;

                    }

                     value = new If(cond, thenBranch, s); 

                    }
                    break;
                case 6 :
                    // Structo.g:64:3: 'while' cond= cexpression statements
                    {
                    match(input,17,FOLLOW_17_in_statement272); 
                    pushFollow(FOLLOW_cexpression_in_statement280);
                    cond=cexpression();
                    _fsp--;

                    pushFollow(FOLLOW_statements_in_statement284);
                    statements9=statements();
                    _fsp--;

                     value = new While(cond, statements9); 

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
        return value;
    }
    // $ANTLR end statement


    // $ANTLR start cexpression
    // Structo.g:73:1: cexpression returns [ Expression value ] : '(' left= aexpression rop right= aexpression ')' ;
    public final Expression cexpression() throws RecognitionException {
        Expression value = null;

        Expression left = null;

        Expression right = null;

        Op rop10 = null;


        try {
            // Structo.g:74:2: ( '(' left= aexpression rop right= aexpression ')' )
            // Structo.g:75:3: '(' left= aexpression rop right= aexpression ')'
            {
            match(input,18,FOLLOW_18_in_cexpression309); 
            pushFollow(FOLLOW_aexpression_in_cexpression318);
            left=aexpression();
            _fsp--;

            pushFollow(FOLLOW_rop_in_cexpression323);
            rop10=rop();
            _fsp--;

            pushFollow(FOLLOW_aexpression_in_cexpression332);
            right=aexpression();
            _fsp--;

            match(input,19,FOLLOW_19_in_cexpression337); 
             value = new Binary(rop10, left, right); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end cexpression


    // $ANTLR start rop
    // Structo.g:86:1: rop returns [ Op value ] : ( '==' | '!=' | '<' | '>' | '<=' | '>=' );
    public final Op rop() throws RecognitionException {
        Op value = null;

        try {
            // Structo.g:87:2: ( '==' | '!=' | '<' | '>' | '<=' | '>=' )
            int alt5=6;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt5=1;
                }
                break;
            case 21:
                {
                alt5=2;
                }
                break;
            case 22:
                {
                alt5=3;
                }
                break;
            case 23:
                {
                alt5=4;
                }
                break;
            case 24:
                {
                alt5=5;
                }
                break;
            case 25:
                {
                alt5=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("86:1: rop returns [ Op value ] : ( '==' | '!=' | '<' | '>' | '<=' | '>=' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Structo.g:88:3: '=='
                    {
                    match(input,20,FOLLOW_20_in_rop363); 
                     value = Op.Equal; 

                    }
                    break;
                case 2 :
                    // Structo.g:91:4: '!='
                    {
                    match(input,21,FOLLOW_21_in_rop375); 
                     value = Op.NotEqual; 

                    }
                    break;
                case 3 :
                    // Structo.g:94:4: '<'
                    {
                    match(input,22,FOLLOW_22_in_rop389); 
                     value = Op.Below; 

                    }
                    break;
                case 4 :
                    // Structo.g:97:4: '>'
                    {
                    match(input,23,FOLLOW_23_in_rop403); 
                     value = Op.Greater; 

                    }
                    break;
                case 5 :
                    // Structo.g:100:4: '<='
                    {
                    match(input,24,FOLLOW_24_in_rop416); 
                     value = Op.BelowEq; 

                    }
                    break;
                case 6 :
                    // Structo.g:103:4: '>='
                    {
                    match(input,25,FOLLOW_25_in_rop430); 
                     value = Op.GreaterEq; 

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
        return value;
    }
    // $ANTLR end rop


    // $ANTLR start aexpression
    // Structo.g:110:1: aexpression returns [ Expression value ] : left= term ( ( '+' | '-' ) right= term )* ;
    public final Expression aexpression() throws RecognitionException {
        Expression value = null;

        Expression left = null;

        Expression right = null;


        try {
            // Structo.g:111:2: (left= term ( ( '+' | '-' ) right= term )* )
            // Structo.g:112:3: left= term ( ( '+' | '-' ) right= term )*
            {
            pushFollow(FOLLOW_term_in_aexpression460);
            left=term();
            _fsp--;

             value = left; 
            // Structo.g:114:3: ( ( '+' | '-' ) right= term )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=26 && LA7_0<=27)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Structo.g:115:4: ( '+' | '-' ) right= term
            	    {
            	     Op op = null; 
            	    // Structo.g:116:4: ( '+' | '-' )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==26) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==27) ) {
            	        alt6=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("116:4: ( '+' | '-' )", 6, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // Structo.g:117:5: '+'
            	            {
            	            match(input,26,FOLLOW_26_in_aexpression485); 
            	             op = Op.Plus; 

            	            }
            	            break;
            	        case 2 :
            	            // Structo.g:120:5: '-'
            	            {
            	            match(input,27,FOLLOW_27_in_aexpression505); 
            	             op = Op.Minus; 

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_term_in_aexpression527);
            	    right=term();
            	    _fsp--;

            	     value = new Binary(op,value,right); 

            	    }
            	    break;

            	default :
            	    break loop7;
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
        return value;
    }
    // $ANTLR end aexpression


    // $ANTLR start term
    // Structo.g:128:1: term returns [ Expression value ] : left= factor ( ( '*' | '/' ) right= factor )* ;
    public final Expression term() throws RecognitionException {
        Expression value = null;

        Expression left = null;

        Expression right = null;


        try {
            // Structo.g:129:2: (left= factor ( ( '*' | '/' ) right= factor )* )
            // Structo.g:130:3: left= factor ( ( '*' | '/' ) right= factor )*
            {
            pushFollow(FOLLOW_factor_in_term562);
            left=factor();
            _fsp--;

             value = left; 
            // Structo.g:132:3: ( ( '*' | '/' ) right= factor )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=28 && LA9_0<=29)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Structo.g:133:4: ( '*' | '/' ) right= factor
            	    {
            	     Op op = null; 
            	    // Structo.g:134:4: ( '*' | '/' )
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==28) ) {
            	        alt8=1;
            	    }
            	    else if ( (LA8_0==29) ) {
            	        alt8=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("134:4: ( '*' | '/' )", 8, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // Structo.g:135:5: '*'
            	            {
            	            match(input,28,FOLLOW_28_in_term589); 
            	             op = Op.Times; 

            	            }
            	            break;
            	        case 2 :
            	            // Structo.g:138:5: '/'
            	            {
            	            match(input,29,FOLLOW_29_in_term609); 
            	             op = Op.Div; 

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_factor_in_term630);
            	    right=factor();
            	    _fsp--;

            	     value = new Binary(op,value,right); 

            	    }
            	    break;

            	default :
            	    break loop9;
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
        return value;
    }
    // $ANTLR end term


    // $ANTLR start factor
    // Structo.g:146:1: factor returns [ Expression value ] : ( ID | INT | '(' aexpression ')' );
    public final Expression factor() throws RecognitionException {
        Expression value = null;

        Token ID11=null;
        Token INT12=null;
        Expression aexpression13 = null;


        try {
            // Structo.g:147:2: ( ID | INT | '(' aexpression ')' )
            int alt10=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt10=1;
                }
                break;
            case INT:
                {
                alt10=2;
                }
                break;
            case 18:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("146:1: factor returns [ Expression value ] : ( ID | INT | '(' aexpression ')' );", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // Structo.g:148:3: ID
                    {
                    ID11=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_factor659); 
                     value = new Id(ID11.getText()); 

                    }
                    break;
                case 2 :
                    // Structo.g:151:4: INT
                    {
                    INT12=(Token)input.LT(1);
                    match(input,INT,FOLLOW_INT_in_factor674); 
                     value = new Int(Integer.parseInt(INT12.getText())); 

                    }
                    break;
                case 3 :
                    // Structo.g:154:3: '(' aexpression ')'
                    {
                    match(input,18,FOLLOW_18_in_factor689); 
                    pushFollow(FOLLOW_aexpression_in_factor691);
                    aexpression13=aexpression();
                    _fsp--;

                    match(input,19,FOLLOW_19_in_factor693); 
                     value = aexpression13; 

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
        return value;
    }
    // $ANTLR end factor


 

    public static final BitSet FOLLOW_statements_in_program33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_statements72 = new BitSet(new long[]{0x000000000002B610L});
    public static final BitSet FOLLOW_statement_in_statements77 = new BitSet(new long[]{0x000000000002B610L});
    public static final BitSet FOLLOW_9_in_statements94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_statement127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_statement132 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_statement149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_statement154 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_statement171 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_statement175 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_statement194 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_statement199 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_statement204 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_statement225 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_cexpression_in_statement234 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement242 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_statement248 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_statement272 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_cexpression_in_statement280 = new BitSet(new long[]{0x000000000002B510L});
    public static final BitSet FOLLOW_statements_in_statement284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_cexpression309 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_cexpression318 = new BitSet(new long[]{0x0000000003F00000L});
    public static final BitSet FOLLOW_rop_in_cexpression323 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_cexpression332 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_cexpression337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rop363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rop375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rop389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rop403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rop416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rop430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_aexpression460 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_26_in_aexpression485 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_27_in_aexpression505 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_term_in_aexpression527 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_factor_in_term562 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_28_in_term589 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_29_in_term609 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_factor_in_term630 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_ID_in_factor659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_factor674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_factor689 = new BitSet(new long[]{0x0000000000040030L});
    public static final BitSet FOLLOW_aexpression_in_factor691 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_factor693 = new BitSet(new long[]{0x0000000000000002L});

}
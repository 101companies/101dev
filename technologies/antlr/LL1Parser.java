// $ANTLR 3.2 Sep 23, 2009 12:02:23 LL1.g 2011-04-29 23:02:38

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LL1Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "WS", "'a'", "'x'", "'b'"
    };
    public static final int WS=4;
    public static final int EOF=-1;
    public static final int T__7=7;
    public static final int T__6=6;
    public static final int T__5=5;

    // delegates
    // delegators


        public LL1Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public LL1Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return LL1Parser.tokenNames; }
    public String getGrammarFileName() { return "LL1.g"; }



    // $ANTLR start "s"
    // LL1.g:3:1: s : ( 'a' 'x' | 'b' 'x' );
    public final void s() throws RecognitionException {
        try {
            // LL1.g:3:3: ( 'a' 'x' | 'b' 'x' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==5) ) {
                alt1=1;
            }
            else if ( (LA1_0==7) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // LL1.g:3:5: 'a' 'x'
                    {
                    match(input,5,FOLLOW_5_in_s20); 
                    match(input,6,FOLLOW_6_in_s22); 

                    }
                    break;
                case 2 :
                    // LL1.g:3:15: 'b' 'x'
                    {
                    match(input,7,FOLLOW_7_in_s26); 
                    match(input,6,FOLLOW_6_in_s28); 

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
    // $ANTLR end "s"

    // Delegated rules


 

    public static final BitSet FOLLOW_5_in_s20 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_s22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_s26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_s28 = new BitSet(new long[]{0x0000000000000002L});

}
// $ANTLR 3.2 Sep 23, 2009 12:02:23 LL2.g 2011-04-29 23:02:38

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LL2Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'a'", "'x'", "'y'"
    };
    public static final int EOF=-1;
    public static final int T__6=6;
    public static final int T__5=5;
    public static final int T__4=4;

    // delegates
    // delegators


        public LL2Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public LL2Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return LL2Parser.tokenNames; }
    public String getGrammarFileName() { return "LL2.g"; }



    // $ANTLR start "s"
    // LL2.g:3:1: s : ( 'a' 'x' | 'a' 'y' );
    public final void s() throws RecognitionException {
        try {
            // LL2.g:3:3: ( 'a' 'x' | 'a' 'y' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==4) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==5) ) {
                    alt1=1;
                }
                else if ( (LA1_1==6) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // LL2.g:3:5: 'a' 'x'
                    {
                    match(input,4,FOLLOW_4_in_s20); 
                    match(input,5,FOLLOW_5_in_s22); 

                    }
                    break;
                case 2 :
                    // LL2.g:3:15: 'a' 'y'
                    {
                    match(input,4,FOLLOW_4_in_s26); 
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


 

    public static final BitSet FOLLOW_4_in_s20 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_s22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_s26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_s28 = new BitSet(new long[]{0x0000000000000002L});

}
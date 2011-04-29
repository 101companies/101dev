// $ANTLR 3.2 Sep 23, 2009 12:02:23 Factoring.g 2011-04-29 23:02:37

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class FactoringParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'x'", "'y'", "'a'", "'b'"
    };
    public static final int EOF=-1;
    public static final int T__7=7;
    public static final int T__6=6;
    public static final int T__5=5;
    public static final int T__4=4;

    // delegates
    // delegators


        public FactoringParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public FactoringParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return FactoringParser.tokenNames; }
    public String getGrammarFileName() { return "Factoring.g"; }



    // $ANTLR start "s"
    // Factoring.g:3:1: s : p ( 'x' | 'y' ) ;
    public final void s() throws RecognitionException {
        try {
            // Factoring.g:3:3: ( p ( 'x' | 'y' ) )
            // Factoring.g:3:5: p ( 'x' | 'y' )
            {
            pushFollow(FOLLOW_p_in_s20);
            p();

            state._fsp--;

            if ( (input.LA(1)>=4 && input.LA(1)<=5) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "s"


    // $ANTLR start "p"
    // Factoring.g:4:1: p : ( 'a' p 'b' | );
    public final void p() throws RecognitionException {
        try {
            // Factoring.g:4:3: ( 'a' p 'b' | )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==6) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=4 && LA1_0<=5)||LA1_0==7) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Factoring.g:4:5: 'a' p 'b'
                    {
                    match(input,6,FOLLOW_6_in_p35); 
                    pushFollow(FOLLOW_p_in_p37);
                    p();

                    state._fsp--;

                    match(input,7,FOLLOW_7_in_p39); 

                    }
                    break;
                case 2 :
                    // Factoring.g:4:17: 
                    {
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
    // $ANTLR end "p"

    // Delegated rules


 

    public static final BitSet FOLLOW_p_in_s20 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_s22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_6_in_p35 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_p_in_p37 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_p39 = new BitSet(new long[]{0x0000000000000002L});

}
// $ANTLR 3.2 Sep 23, 2009 12:02:23 Delay.g 2011-04-29 23:02:35

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class DelayParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'a'", "'b'", "'x'", "'y'"
    };
    public static final int EOF=-1;
    public static final int T__7=7;
    public static final int T__6=6;
    public static final int T__5=5;
    public static final int T__4=4;

    // delegates
    // delegators


        public DelayParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DelayParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return DelayParser.tokenNames; }
    public String getGrammarFileName() { return "Delay.g"; }



    // $ANTLR start "s"
    // Delay.g:3:1: s : ( 'a' 'b' 'x' | 'a' 'b' 'y' );
    public final void s() throws RecognitionException {
        try {
            // Delay.g:3:3: ( 'a' 'b' 'x' | 'a' 'b' 'y' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==4) ) {
                int LA1_1 = input.LA(2);

                if ( (synpred1_Delay()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Delay.g:3:5: 'a' 'b' 'x'
                    {
                    match(input,4,FOLLOW_4_in_s27); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       System.out.println(42); 
                    }
                    match(input,5,FOLLOW_5_in_s31); if (state.failed) return ;
                    match(input,6,FOLLOW_6_in_s33); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // Delay.g:3:47: 'a' 'b' 'y'
                    {
                    match(input,4,FOLLOW_4_in_s37); if (state.failed) return ;
                    match(input,5,FOLLOW_5_in_s39); if (state.failed) return ;
                    match(input,7,FOLLOW_7_in_s41); if (state.failed) return ;

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

    // $ANTLR start synpred1_Delay
    public final void synpred1_Delay_fragment() throws RecognitionException {   
        // Delay.g:3:5: ( 'a' 'b' 'x' )
        // Delay.g:3:5: 'a' 'b' 'x'
        {
        match(input,4,FOLLOW_4_in_synpred1_Delay27); if (state.failed) return ;
        match(input,5,FOLLOW_5_in_synpred1_Delay31); if (state.failed) return ;
        match(input,6,FOLLOW_6_in_synpred1_Delay33); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Delay

    // Delegated rules

    public final boolean synpred1_Delay() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Delay_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_4_in_s27 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_s31 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_s33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_s37 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_s39 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_s41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_synpred1_Delay27 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_synpred1_Delay31 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_synpred1_Delay33 = new BitSet(new long[]{0x0000000000000002L});

}
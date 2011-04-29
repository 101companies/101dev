// $ANTLR 3.2 Sep 23, 2009 12:02:23 Backtracking.g 2011-04-29 23:02:34

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class BacktrackingParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'a'", "'x'", "'y'"
    };
    public static final int EOF=-1;
    public static final int T__6=6;
    public static final int T__5=5;
    public static final int T__4=4;

    // delegates
    // delegators


        public BacktrackingParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public BacktrackingParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return BacktrackingParser.tokenNames; }
    public String getGrammarFileName() { return "Backtracking.g"; }



    // $ANTLR start "s"
    // Backtracking.g:3:1: s : ( 'a' 'x' | 'a' 'y' );
    public final void s() throws RecognitionException {
        try {
            // Backtracking.g:3:3: ( 'a' 'x' | 'a' 'y' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==4) ) {
                int LA1_1 = input.LA(2);

                if ( (synpred1_Backtracking()) ) {
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
                    // Backtracking.g:3:5: 'a' 'x'
                    {
                    match(input,4,FOLLOW_4_in_s27); if (state.failed) return ;
                    match(input,5,FOLLOW_5_in_s29); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // Backtracking.g:3:15: 'a' 'y'
                    {
                    match(input,4,FOLLOW_4_in_s33); if (state.failed) return ;
                    match(input,6,FOLLOW_6_in_s35); if (state.failed) return ;

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

    // $ANTLR start synpred1_Backtracking
    public final void synpred1_Backtracking_fragment() throws RecognitionException {   
        // Backtracking.g:3:5: ( 'a' 'x' )
        // Backtracking.g:3:5: 'a' 'x'
        {
        match(input,4,FOLLOW_4_in_synpred1_Backtracking27); if (state.failed) return ;
        match(input,5,FOLLOW_5_in_synpred1_Backtracking29); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Backtracking

    // Delegated rules

    public final boolean synpred1_Backtracking() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Backtracking_fragment(); // can never throw exception
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
    public static final BitSet FOLLOW_5_in_s29 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_s33 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_s35 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_synpred1_Backtracking27 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_synpred1_Backtracking29 = new BitSet(new long[]{0x0000000000000002L});

}
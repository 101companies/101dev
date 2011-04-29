// $ANTLR 3.2 Sep 23, 2009 12:02:23 SynPreds.g 2011-04-29 23:02:42

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class SynPredsParser extends Parser {
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


        public SynPredsParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public SynPredsParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return SynPredsParser.tokenNames; }
    public String getGrammarFileName() { return "SynPreds.g"; }



    // $ANTLR start "s"
    // SynPreds.g:2:1: s : ( ( p 'x' )=> p 'x' | ( p 'y' )=> p 'y' );
    public final void s() throws RecognitionException {
        try {
            // SynPreds.g:2:3: ( ( p 'x' )=> p 'x' | ( p 'y' )=> p 'y' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==6) ) {
                int LA1_1 = input.LA(2);

                if ( (synpred1_SynPreds()) ) {
                    alt1=1;
                }
                else if ( (synpred2_SynPreds()) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA1_0==4) && (synpred1_SynPreds())) {
                alt1=1;
            }
            else if ( (LA1_0==5) && (synpred2_SynPreds())) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // SynPreds.g:2:5: ( p 'x' )=> p 'x'
                    {
                    pushFollow(FOLLOW_p_in_s17);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,4,FOLLOW_4_in_s19); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // SynPreds.g:3:5: ( p 'y' )=> p 'y'
                    {
                    pushFollow(FOLLOW_p_in_s34);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,5,FOLLOW_5_in_s36); if (state.failed) return ;

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


    // $ANTLR start "p"
    // SynPreds.g:4:1: p : ( 'a' p 'b' | );
    public final void p() throws RecognitionException {
        try {
            // SynPreds.g:4:3: ( 'a' p 'b' | )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==6) ) {
                alt2=1;
            }
            else if ( ((LA2_0>=4 && LA2_0<=5)||LA2_0==7) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // SynPreds.g:4:5: 'a' p 'b'
                    {
                    match(input,6,FOLLOW_6_in_p43); if (state.failed) return ;
                    pushFollow(FOLLOW_p_in_p45);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,7,FOLLOW_7_in_p47); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // SynPreds.g:4:17: 
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

    // $ANTLR start synpred1_SynPreds
    public final void synpred1_SynPreds_fragment() throws RecognitionException {   
        // SynPreds.g:2:5: ( p 'x' )
        // SynPreds.g:2:6: p 'x'
        {
        pushFollow(FOLLOW_p_in_synpred1_SynPreds10);
        p();

        state._fsp--;
        if (state.failed) return ;
        match(input,4,FOLLOW_4_in_synpred1_SynPreds12); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_SynPreds

    // $ANTLR start synpred2_SynPreds
    public final void synpred2_SynPreds_fragment() throws RecognitionException {   
        // SynPreds.g:3:5: ( p 'y' )
        // SynPreds.g:3:6: p 'y'
        {
        pushFollow(FOLLOW_p_in_synpred2_SynPreds27);
        p();

        state._fsp--;
        if (state.failed) return ;
        match(input,5,FOLLOW_5_in_synpred2_SynPreds29); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_SynPreds

    // Delegated rules

    public final boolean synpred1_SynPreds() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_SynPreds_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_SynPreds() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_SynPreds_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_p_in_s17 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4_in_s19 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_p_in_s34 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_s36 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_6_in_p43 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_p_in_p45 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_p47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_p_in_synpred1_SynPreds10 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4_in_synpred1_SynPreds12 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_p_in_synpred2_SynPreds27 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_synpred2_SynPreds29 = new BitSet(new long[]{0x0000000000000002L});

}
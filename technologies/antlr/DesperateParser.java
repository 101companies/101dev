// $ANTLR 3.2 Sep 23, 2009 12:02:23 Desperate.g 2011-04-29 23:02:36

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class DesperateParser extends Parser {
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


        public DesperateParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DesperateParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return DesperateParser.tokenNames; }
    public String getGrammarFileName() { return "Desperate.g"; }



    // $ANTLR start "s"
    // Desperate.g:3:1: s : ( p 'x' | p 'y' );
    public final void s() throws RecognitionException {
        try {
            // Desperate.g:3:3: ( p 'x' | p 'y' )
            int alt1=2;
            switch ( input.LA(1) ) {
            case 6:
                {
                int LA1_1 = input.LA(2);

                if ( (synpred1_Desperate()) ) {
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
                break;
            case 4:
                {
                alt1=1;
                }
                break;
            case 5:
                {
                alt1=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Desperate.g:3:5: p 'x'
                    {
                    pushFollow(FOLLOW_p_in_s20);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,4,FOLLOW_4_in_s22); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // Desperate.g:3:13: p 'y'
                    {
                    pushFollow(FOLLOW_p_in_s26);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,5,FOLLOW_5_in_s28); if (state.failed) return ;

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
    // Desperate.g:4:1: p : ( 'a' p 'b' | );
    public final void p() throws RecognitionException {
        try {
            // Desperate.g:4:3: ( 'a' p 'b' | )
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
                    // Desperate.g:4:5: 'a' p 'b'
                    {
                    match(input,6,FOLLOW_6_in_p35); if (state.failed) return ;
                    pushFollow(FOLLOW_p_in_p37);
                    p();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,7,FOLLOW_7_in_p39); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // Desperate.g:4:17: 
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

    // $ANTLR start synpred1_Desperate
    public final void synpred1_Desperate_fragment() throws RecognitionException {   
        // Desperate.g:3:5: ( p 'x' )
        // Desperate.g:3:5: p 'x'
        {
        pushFollow(FOLLOW_p_in_synpred1_Desperate20);
        p();

        state._fsp--;
        if (state.failed) return ;
        match(input,4,FOLLOW_4_in_synpred1_Desperate22); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Desperate

    // Delegated rules

    public final boolean synpred1_Desperate() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Desperate_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_p_in_s20 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4_in_s22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_p_in_s26 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_s28 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_6_in_p35 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_p_in_p37 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_p39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_p_in_synpred1_Desperate20 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4_in_synpred1_Desperate22 = new BitSet(new long[]{0x0000000000000002L});

}
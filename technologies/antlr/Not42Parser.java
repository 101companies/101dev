// $ANTLR 3.2 Sep 23, 2009 12:02:23 Not42.g 2011-04-29 23:02:40

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Not42Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'a'", "'x'", "'y'"
    };
    public static final int EOF=-1;
    public static final int T__6=6;
    public static final int T__5=5;
    public static final int T__4=4;

    // delegates
    // delegators


        public Not42Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public Not42Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return Not42Parser.tokenNames; }
    public String getGrammarFileName() { return "Not42.g"; }



    // $ANTLR start "s"
    // Not42.g:3:1: s : ( ( 'a' )* 'x' | ( 'a' )* 'y' );
    public final void s() throws RecognitionException {
        try {
            // Not42.g:3:3: ( ( 'a' )* 'x' | ( 'a' )* 'y' )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // Not42.g:3:5: ( 'a' )* 'x'
                    {
                    // Not42.g:3:5: ( 'a' )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==4) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Not42.g:3:5: 'a'
                    	    {
                    	    match(input,4,FOLLOW_4_in_s20); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    match(input,5,FOLLOW_5_in_s23); 

                    }
                    break;
                case 2 :
                    // Not42.g:3:16: ( 'a' )* 'y'
                    {
                    // Not42.g:3:16: ( 'a' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==4) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // Not42.g:3:16: 'a'
                    	    {
                    	    match(input,4,FOLLOW_4_in_s27); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    match(input,6,FOLLOW_6_in_s30); 

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


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\177\uffff";
    static final String DFA3_eofS =
        "\177\uffff";
    static final String DFA3_minS =
        "\2\4\3\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4"+
        "\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff"+
        "\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4"+
        "\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff"+
        "\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4"+
        "\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff"+
        "\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4\2\uffff\1\4"+
        "\2\uffff\1\4\2\uffff\1\4\4\uffff";
    static final String DFA3_maxS =
        "\2\6\3\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6"+
        "\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff"+
        "\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6"+
        "\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff"+
        "\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6"+
        "\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff"+
        "\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6\2\uffff\1\6"+
        "\2\uffff\1\6\2\uffff\1\6\4\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\1\1\2\171\uffff\1\1\1\uffff";
    static final String DFA3_specialS =
        "\177\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\2\1\3",
            "\1\5\1\2\1\3",
            "",
            "",
            "",
            "\1\10\1\2\1\3",
            "",
            "",
            "\1\13\1\2\1\3",
            "",
            "",
            "\1\16\1\2\1\3",
            "",
            "",
            "\1\21\1\2\1\3",
            "",
            "",
            "\1\24\1\2\1\3",
            "",
            "",
            "\1\27\1\2\1\3",
            "",
            "",
            "\1\32\1\2\1\3",
            "",
            "",
            "\1\35\1\2\1\3",
            "",
            "",
            "\1\40\1\2\1\3",
            "",
            "",
            "\1\43\1\2\1\3",
            "",
            "",
            "\1\46\1\2\1\3",
            "",
            "",
            "\1\51\1\2\1\3",
            "",
            "",
            "\1\54\1\2\1\3",
            "",
            "",
            "\1\57\1\2\1\3",
            "",
            "",
            "\1\62\1\2\1\3",
            "",
            "",
            "\1\65\1\2\1\3",
            "",
            "",
            "\1\70\1\2\1\3",
            "",
            "",
            "\1\73\1\2\1\3",
            "",
            "",
            "\1\76\1\2\1\3",
            "",
            "",
            "\1\101\1\2\1\3",
            "",
            "",
            "\1\104\1\2\1\3",
            "",
            "",
            "\1\107\1\2\1\3",
            "",
            "",
            "\1\112\1\2\1\3",
            "",
            "",
            "\1\115\1\2\1\3",
            "",
            "",
            "\1\120\1\2\1\3",
            "",
            "",
            "\1\123\1\2\1\3",
            "",
            "",
            "\1\126\1\2\1\3",
            "",
            "",
            "\1\131\1\2\1\3",
            "",
            "",
            "\1\134\1\2\1\3",
            "",
            "",
            "\1\137\1\2\1\3",
            "",
            "",
            "\1\142\1\2\1\3",
            "",
            "",
            "\1\145\1\2\1\3",
            "",
            "",
            "\1\150\1\2\1\3",
            "",
            "",
            "\1\153\1\2\1\3",
            "",
            "",
            "\1\156\1\2\1\3",
            "",
            "",
            "\1\161\1\2\1\3",
            "",
            "",
            "\1\164\1\2\1\3",
            "",
            "",
            "\1\167\1\2\1\3",
            "",
            "",
            "\1\172\1\2\1\3",
            "",
            "",
            "\1\175\1\2\1\3",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "3:1: s : ( ( 'a' )* 'x' | ( 'a' )* 'y' );";
        }
    }
 

    public static final BitSet FOLLOW_4_in_s20 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_5_in_s23 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_4_in_s27 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_6_in_s30 = new BitSet(new long[]{0x0000000000000002L});

}
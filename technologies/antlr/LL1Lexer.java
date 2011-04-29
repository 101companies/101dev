// $ANTLR 3.2 Sep 23, 2009 12:02:23 LL1.g 2011-04-29 23:02:38

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LL1Lexer extends Lexer {
    public static final int WS=4;
    public static final int EOF=-1;
    public static final int T__7=7;
    public static final int T__6=6;
    public static final int T__5=5;

    // delegates
    // delegators

    public LL1Lexer() {;} 
    public LL1Lexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public LL1Lexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "LL1.g"; }

    // $ANTLR start "T__5"
    public final void mT__5() throws RecognitionException {
        try {
            int _type = T__5;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LL1.g:3:6: ( 'a' )
            // LL1.g:3:8: 'a'
            {
            match('a'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__5"

    // $ANTLR start "T__6"
    public final void mT__6() throws RecognitionException {
        try {
            int _type = T__6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LL1.g:4:6: ( 'x' )
            // LL1.g:4:8: 'x'
            {
            match('x'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__6"

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LL1.g:5:6: ( 'b' )
            // LL1.g:5:8: 'b'
            {
            match('b'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // LL1.g:7:4: ( ( ' ' | ( '\\r' )? '\\n' | '\\t' )+ )
            // LL1.g:7:6: ( ' ' | ( '\\r' )? '\\n' | '\\t' )+
            {
            // LL1.g:7:6: ( ' ' | ( '\\r' )? '\\n' | '\\t' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt2=1;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt2=2;
                    }
                    break;
                case '\t':
                    {
                    alt2=3;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // LL1.g:7:7: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // LL1.g:7:11: ( '\\r' )? '\\n'
            	    {
            	    // LL1.g:7:11: ( '\\r' )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0=='\r') ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // LL1.g:7:11: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;
            	case 3 :
            	    // LL1.g:7:22: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // LL1.g:1:8: ( T__5 | T__6 | T__7 | WS )
        int alt3=4;
        switch ( input.LA(1) ) {
        case 'a':
            {
            alt3=1;
            }
            break;
        case 'x':
            {
            alt3=2;
            }
            break;
        case 'b':
            {
            alt3=3;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt3=4;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 3, 0, input);

            throw nvae;
        }

        switch (alt3) {
            case 1 :
                // LL1.g:1:10: T__5
                {
                mT__5(); 

                }
                break;
            case 2 :
                // LL1.g:1:15: T__6
                {
                mT__6(); 

                }
                break;
            case 3 :
                // LL1.g:1:20: T__7
                {
                mT__7(); 

                }
                break;
            case 4 :
                // LL1.g:1:25: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}
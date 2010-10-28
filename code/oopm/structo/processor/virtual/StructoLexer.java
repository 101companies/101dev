// $ANTLR 3.0.1 Structo.g 2010-02-09 05:11:38

package structo.processor.virtual;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"unused", "cast"})
public class StructoLexer extends Lexer {
    public static final int T29=29;
    public static final int INT=5;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T8=8;
    public static final int T26=26;
    public static final int T9=9;
    public static final int T25=25;
    public static final int ID=4;
    public static final int Tokens=30;
    public static final int T24=24;
    public static final int EOF=-1;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int WS=6;
    public static final int T10=10;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int COMMENT=7;
    public static final int T15=15;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;
    public StructoLexer() {;} 
    public StructoLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Structo.g"; }

    // $ANTLR start T8
    public final void mT8() throws RecognitionException {
        try {
            int _type = T8;
            // Structo.g:6:4: ( '{' )
            // Structo.g:6:6: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T8

    // $ANTLR start T9
    public final void mT9() throws RecognitionException {
        try {
            int _type = T9;
            // Structo.g:7:4: ( '}' )
            // Structo.g:7:6: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T9

    // $ANTLR start T10
    public final void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // Structo.g:8:5: ( 'int' )
            // Structo.g:8:7: 'int'
            {
            match("int"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // Structo.g:9:5: ( ';' )
            // Structo.g:9:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // Structo.g:10:5: ( 'read' )
            // Structo.g:10:7: 'read'
            {
            match("read"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // Structo.g:11:5: ( 'write' )
            // Structo.g:11:7: 'write'
            {
            match("write"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // Structo.g:12:5: ( '=' )
            // Structo.g:12:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // Structo.g:13:5: ( 'if' )
            // Structo.g:13:7: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // Structo.g:14:5: ( 'else' )
            // Structo.g:14:7: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // Structo.g:15:5: ( 'while' )
            // Structo.g:15:7: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // Structo.g:16:5: ( '(' )
            // Structo.g:16:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // Structo.g:17:5: ( ')' )
            // Structo.g:17:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // Structo.g:18:5: ( '==' )
            // Structo.g:18:7: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // Structo.g:19:5: ( '!=' )
            // Structo.g:19:7: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // Structo.g:20:5: ( '<' )
            // Structo.g:20:7: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // Structo.g:21:5: ( '>' )
            // Structo.g:21:7: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // Structo.g:22:5: ( '<=' )
            // Structo.g:22:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // Structo.g:23:5: ( '>=' )
            // Structo.g:23:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // Structo.g:24:5: ( '+' )
            // Structo.g:24:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // Structo.g:25:5: ( '-' )
            // Structo.g:25:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // Structo.g:26:5: ( '*' )
            // Structo.g:26:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // Structo.g:27:5: ( '/' )
            // Structo.g:27:7: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // Structo.g:161:6: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // Structo.g:161:10: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // Structo.g:161:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Structo.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // Structo.g:162:6: ( ( '0' .. '9' )+ )
            // Structo.g:162:10: ( '0' .. '9' )+
            {
            // Structo.g:162:10: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Structo.g:162:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // Structo.g:163:6: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // Structo.g:163:10: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // Structo.g:163:10: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||LA3_0=='\r'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Structo.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

             skip(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            // Structo.g:164:9: ( '//' (~ ( '\\n' | '\\r' ) )* )
            // Structo.g:164:13: '//' (~ ( '\\n' | '\\r' ) )*
            {
            match("//"); 

            // Structo.g:164:18: (~ ( '\\n' | '\\r' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Structo.g:164:19: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             skip(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    public void mTokens() throws RecognitionException {
        // Structo.g:1:8: ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | ID | INT | WS | COMMENT )
        int alt5=26;
        switch ( input.LA(1) ) {
        case '{':
            {
            alt5=1;
            }
            break;
        case '}':
            {
            alt5=2;
            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'f':
                {
                int LA5_21 = input.LA(3);

                if ( ((LA5_21>='0' && LA5_21<='9')||(LA5_21>='A' && LA5_21<='Z')||(LA5_21>='a' && LA5_21<='z')) ) {
                    alt5=23;
                }
                else {
                    alt5=8;}
                }
                break;
            case 'n':
                {
                int LA5_22 = input.LA(3);

                if ( (LA5_22=='t') ) {
                    int LA5_36 = input.LA(4);

                    if ( ((LA5_36>='0' && LA5_36<='9')||(LA5_36>='A' && LA5_36<='Z')||(LA5_36>='a' && LA5_36<='z')) ) {
                        alt5=23;
                    }
                    else {
                        alt5=3;}
                }
                else {
                    alt5=23;}
                }
                break;
            default:
                alt5=23;}

            }
            break;
        case ';':
            {
            alt5=4;
            }
            break;
        case 'r':
            {
            int LA5_5 = input.LA(2);

            if ( (LA5_5=='e') ) {
                int LA5_23 = input.LA(3);

                if ( (LA5_23=='a') ) {
                    int LA5_37 = input.LA(4);

                    if ( (LA5_37=='d') ) {
                        int LA5_42 = input.LA(5);

                        if ( ((LA5_42>='0' && LA5_42<='9')||(LA5_42>='A' && LA5_42<='Z')||(LA5_42>='a' && LA5_42<='z')) ) {
                            alt5=23;
                        }
                        else {
                            alt5=5;}
                    }
                    else {
                        alt5=23;}
                }
                else {
                    alt5=23;}
            }
            else {
                alt5=23;}
            }
            break;
        case 'w':
            {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA5_24 = input.LA(3);

                if ( (LA5_24=='i') ) {
                    int LA5_38 = input.LA(4);

                    if ( (LA5_38=='t') ) {
                        int LA5_43 = input.LA(5);

                        if ( (LA5_43=='e') ) {
                            int LA5_47 = input.LA(6);

                            if ( ((LA5_47>='0' && LA5_47<='9')||(LA5_47>='A' && LA5_47<='Z')||(LA5_47>='a' && LA5_47<='z')) ) {
                                alt5=23;
                            }
                            else {
                                alt5=6;}
                        }
                        else {
                            alt5=23;}
                    }
                    else {
                        alt5=23;}
                }
                else {
                    alt5=23;}
                }
                break;
            case 'h':
                {
                int LA5_25 = input.LA(3);

                if ( (LA5_25=='i') ) {
                    int LA5_39 = input.LA(4);

                    if ( (LA5_39=='l') ) {
                        int LA5_44 = input.LA(5);

                        if ( (LA5_44=='e') ) {
                            int LA5_48 = input.LA(6);

                            if ( ((LA5_48>='0' && LA5_48<='9')||(LA5_48>='A' && LA5_48<='Z')||(LA5_48>='a' && LA5_48<='z')) ) {
                                alt5=23;
                            }
                            else {
                                alt5=10;}
                        }
                        else {
                            alt5=23;}
                    }
                    else {
                        alt5=23;}
                }
                else {
                    alt5=23;}
                }
                break;
            default:
                alt5=23;}

            }
            break;
        case '=':
            {
            int LA5_7 = input.LA(2);

            if ( (LA5_7=='=') ) {
                alt5=13;
            }
            else {
                alt5=7;}
            }
            break;
        case 'e':
            {
            int LA5_8 = input.LA(2);

            if ( (LA5_8=='l') ) {
                int LA5_28 = input.LA(3);

                if ( (LA5_28=='s') ) {
                    int LA5_40 = input.LA(4);

                    if ( (LA5_40=='e') ) {
                        int LA5_45 = input.LA(5);

                        if ( ((LA5_45>='0' && LA5_45<='9')||(LA5_45>='A' && LA5_45<='Z')||(LA5_45>='a' && LA5_45<='z')) ) {
                            alt5=23;
                        }
                        else {
                            alt5=9;}
                    }
                    else {
                        alt5=23;}
                }
                else {
                    alt5=23;}
            }
            else {
                alt5=23;}
            }
            break;
        case '(':
            {
            alt5=11;
            }
            break;
        case ')':
            {
            alt5=12;
            }
            break;
        case '!':
            {
            alt5=14;
            }
            break;
        case '<':
            {
            int LA5_12 = input.LA(2);

            if ( (LA5_12=='=') ) {
                alt5=17;
            }
            else {
                alt5=15;}
            }
            break;
        case '>':
            {
            int LA5_13 = input.LA(2);

            if ( (LA5_13=='=') ) {
                alt5=18;
            }
            else {
                alt5=16;}
            }
            break;
        case '+':
            {
            alt5=19;
            }
            break;
        case '-':
            {
            alt5=20;
            }
            break;
        case '*':
            {
            alt5=21;
            }
            break;
        case '/':
            {
            int LA5_17 = input.LA(2);

            if ( (LA5_17=='/') ) {
                alt5=26;
            }
            else {
                alt5=22;}
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=23;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=24;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt5=25;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | ID | INT | WS | COMMENT );", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // Structo.g:1:10: T8
                {
                mT8(); 

                }
                break;
            case 2 :
                // Structo.g:1:13: T9
                {
                mT9(); 

                }
                break;
            case 3 :
                // Structo.g:1:16: T10
                {
                mT10(); 

                }
                break;
            case 4 :
                // Structo.g:1:20: T11
                {
                mT11(); 

                }
                break;
            case 5 :
                // Structo.g:1:24: T12
                {
                mT12(); 

                }
                break;
            case 6 :
                // Structo.g:1:28: T13
                {
                mT13(); 

                }
                break;
            case 7 :
                // Structo.g:1:32: T14
                {
                mT14(); 

                }
                break;
            case 8 :
                // Structo.g:1:36: T15
                {
                mT15(); 

                }
                break;
            case 9 :
                // Structo.g:1:40: T16
                {
                mT16(); 

                }
                break;
            case 10 :
                // Structo.g:1:44: T17
                {
                mT17(); 

                }
                break;
            case 11 :
                // Structo.g:1:48: T18
                {
                mT18(); 

                }
                break;
            case 12 :
                // Structo.g:1:52: T19
                {
                mT19(); 

                }
                break;
            case 13 :
                // Structo.g:1:56: T20
                {
                mT20(); 

                }
                break;
            case 14 :
                // Structo.g:1:60: T21
                {
                mT21(); 

                }
                break;
            case 15 :
                // Structo.g:1:64: T22
                {
                mT22(); 

                }
                break;
            case 16 :
                // Structo.g:1:68: T23
                {
                mT23(); 

                }
                break;
            case 17 :
                // Structo.g:1:72: T24
                {
                mT24(); 

                }
                break;
            case 18 :
                // Structo.g:1:76: T25
                {
                mT25(); 

                }
                break;
            case 19 :
                // Structo.g:1:80: T26
                {
                mT26(); 

                }
                break;
            case 20 :
                // Structo.g:1:84: T27
                {
                mT27(); 

                }
                break;
            case 21 :
                // Structo.g:1:88: T28
                {
                mT28(); 

                }
                break;
            case 22 :
                // Structo.g:1:92: T29
                {
                mT29(); 

                }
                break;
            case 23 :
                // Structo.g:1:96: ID
                {
                mID(); 

                }
                break;
            case 24 :
                // Structo.g:1:99: INT
                {
                mINT(); 

                }
                break;
            case 25 :
                // Structo.g:1:103: WS
                {
                mWS(); 

                }
                break;
            case 26 :
                // Structo.g:1:106: COMMENT
                {
                mCOMMENT(); 

                }
                break;

        }

    }


 

}
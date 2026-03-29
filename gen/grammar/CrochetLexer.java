// Generated from C:/Users/jk123/Desktop/pliki_studia/CrochetLingo/grammar/src/grammar/Crochet.g4 by ANTLR 4.13.2
package grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CrochetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, STITCH=8, FO=9, 
		MR=10, IN_NEXT_STITCH=11, IN_SAME_STITCH=12, SKIP_STICHES=13, TURN=14, 
		NUMBER=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "STITCH", "FO", 
			"MR", "IN_NEXT_STITCH", "IN_SAME_STITCH", "SKIP_STICHES", "TURN", "NUMBER", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'rnd'", "':'", "';'", "','", "'('", "')'", "'x'", null, "'fo'", 
			"'mr'", "'in_next_st'", "'in_same_st'", "'skip'", "'turn'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "STITCH", "FO", "MR", 
			"IN_NEXT_STITCH", "IN_SAME_STITCH", "SKIP_STICHES", "TURN", "NUMBER", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CrochetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Crochet.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0010~\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007K\b\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\u000e\u0004\u000et\b\u000e\u000b\u000e\f\u000e"+
		"u\u0001\u000f\u0004\u000fy\b\u000f\u000b\u000f\f\u000fz\u0001\u000f\u0001"+
		"\u000f\u0000\u0000\u0010\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010\u0001\u0000\u0002\u0001"+
		"\u000009\u0003\u0000\t\n\r\r  \u0087\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0001!\u0001\u0000\u0000\u0000\u0003%\u0001\u0000"+
		"\u0000\u0000\u0005\'\u0001\u0000\u0000\u0000\u0007)\u0001\u0000\u0000"+
		"\u0000\t+\u0001\u0000\u0000\u0000\u000b-\u0001\u0000\u0000\u0000\r/\u0001"+
		"\u0000\u0000\u0000\u000fJ\u0001\u0000\u0000\u0000\u0011L\u0001\u0000\u0000"+
		"\u0000\u0013O\u0001\u0000\u0000\u0000\u0015R\u0001\u0000\u0000\u0000\u0017"+
		"]\u0001\u0000\u0000\u0000\u0019h\u0001\u0000\u0000\u0000\u001bm\u0001"+
		"\u0000\u0000\u0000\u001ds\u0001\u0000\u0000\u0000\u001fx\u0001\u0000\u0000"+
		"\u0000!\"\u0005r\u0000\u0000\"#\u0005n\u0000\u0000#$\u0005d\u0000\u0000"+
		"$\u0002\u0001\u0000\u0000\u0000%&\u0005:\u0000\u0000&\u0004\u0001\u0000"+
		"\u0000\u0000\'(\u0005;\u0000\u0000(\u0006\u0001\u0000\u0000\u0000)*\u0005"+
		",\u0000\u0000*\b\u0001\u0000\u0000\u0000+,\u0005(\u0000\u0000,\n\u0001"+
		"\u0000\u0000\u0000-.\u0005)\u0000\u0000.\f\u0001\u0000\u0000\u0000/0\u0005"+
		"x\u0000\u00000\u000e\u0001\u0000\u0000\u000012\u0005s\u0000\u00002K\u0005"+
		"c\u0000\u000034\u0005h\u0000\u000045\u0005d\u0000\u00005K\u0005c\u0000"+
		"\u000067\u0005d\u0000\u00007K\u0005c\u0000\u000089\u0005c\u0000\u0000"+
		"9K\u0005h\u0000\u0000:;\u0005s\u0000\u0000;<\u0005l\u0000\u0000<=\u0005"+
		"s\u0000\u0000=K\u0005t\u0000\u0000>?\u0005i\u0000\u0000?@\u0005n\u0000"+
		"\u0000@K\u0005c\u0000\u0000AB\u0005d\u0000\u0000BC\u0005e\u0000\u0000"+
		"CK\u0005c\u0000\u0000DE\u0005f\u0000\u0000EF\u0005l\u0000\u0000FK\u0005"+
		"o\u0000\u0000GH\u0005b\u0000\u0000HI\u0005l\u0000\u0000IK\u0005o\u0000"+
		"\u0000J1\u0001\u0000\u0000\u0000J3\u0001\u0000\u0000\u0000J6\u0001\u0000"+
		"\u0000\u0000J8\u0001\u0000\u0000\u0000J:\u0001\u0000\u0000\u0000J>\u0001"+
		"\u0000\u0000\u0000JA\u0001\u0000\u0000\u0000JD\u0001\u0000\u0000\u0000"+
		"JG\u0001\u0000\u0000\u0000K\u0010\u0001\u0000\u0000\u0000LM\u0005f\u0000"+
		"\u0000MN\u0005o\u0000\u0000N\u0012\u0001\u0000\u0000\u0000OP\u0005m\u0000"+
		"\u0000PQ\u0005r\u0000\u0000Q\u0014\u0001\u0000\u0000\u0000RS\u0005i\u0000"+
		"\u0000ST\u0005n\u0000\u0000TU\u0005_\u0000\u0000UV\u0005n\u0000\u0000"+
		"VW\u0005e\u0000\u0000WX\u0005x\u0000\u0000XY\u0005t\u0000\u0000YZ\u0005"+
		"_\u0000\u0000Z[\u0005s\u0000\u0000[\\\u0005t\u0000\u0000\\\u0016\u0001"+
		"\u0000\u0000\u0000]^\u0005i\u0000\u0000^_\u0005n\u0000\u0000_`\u0005_"+
		"\u0000\u0000`a\u0005s\u0000\u0000ab\u0005a\u0000\u0000bc\u0005m\u0000"+
		"\u0000cd\u0005e\u0000\u0000de\u0005_\u0000\u0000ef\u0005s\u0000\u0000"+
		"fg\u0005t\u0000\u0000g\u0018\u0001\u0000\u0000\u0000hi\u0005s\u0000\u0000"+
		"ij\u0005k\u0000\u0000jk\u0005i\u0000\u0000kl\u0005p\u0000\u0000l\u001a"+
		"\u0001\u0000\u0000\u0000mn\u0005t\u0000\u0000no\u0005u\u0000\u0000op\u0005"+
		"r\u0000\u0000pq\u0005n\u0000\u0000q\u001c\u0001\u0000\u0000\u0000rt\u0007"+
		"\u0000\u0000\u0000sr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u001e\u0001\u0000"+
		"\u0000\u0000wy\u0007\u0001\u0000\u0000xw\u0001\u0000\u0000\u0000yz\u0001"+
		"\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000"+
		"{|\u0001\u0000\u0000\u0000|}\u0006\u000f\u0000\u0000} \u0001\u0000\u0000"+
		"\u0000\u0004\u0000Juz\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
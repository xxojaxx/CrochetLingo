// Generated from C:/Users/jk123/Desktop/pliki_studia/CrochetLingo/backend/src/main/antlr4/com/example/crochetLingo/antlr/Crochet.g4 by ANTLR 4.13.2
package main.antlr4.com.example.crochetLingo.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CrochetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, STITCH=8, FO=9, 
		MR=10, IN_NEXT_STITCH=11, IN_SAME_STITCH=12, SKIP_STICHES=13, TURN=14, 
		NUMBER=15, WS=16;
	public static final int
		RULE_pattern = 0, RULE_round = 1, RULE_elementList = 2, RULE_element = 3, 
		RULE_stitch = 4, RULE_action = 5, RULE_repeat = 6, RULE_contextOperation = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"pattern", "round", "elementList", "element", "stitch", "action", "repeat", 
			"contextOperation"
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

	@Override
	public String getGrammarFileName() { return "Crochet.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CrochetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public List<RoundContext> round() {
			return getRuleContexts(RoundContext.class);
		}
		public RoundContext round(int i) {
			return getRuleContext(RoundContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				round();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoundContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(CrochetParser.NUMBER, 0); }
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public RoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_round; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterRound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitRound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitRound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoundContext round() throws RecognitionException {
		RoundContext _localctx = new RoundContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_round);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(T__0);
			setState(22);
			match(NUMBER);
			setState(23);
			match(T__1);
			setState(24);
			elementList();
			setState(25);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementListContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitElementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_elementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			element();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(28);
				match(T__3);
				setState(29);
				element();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementContext extends ParserRuleContext {
		public StitchContext stitch() {
			return getRuleContext(StitchContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public RepeatContext repeat() {
			return getRuleContext(RepeatContext.class,0);
		}
		public ContextOperationContext contextOperation() {
			return getRuleContext(ContextOperationContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_element);
		try {
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STITCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				stitch();
				}
				break;
			case FO:
			case MR:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				action();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				repeat();
				}
				break;
			case IN_NEXT_STITCH:
			case IN_SAME_STITCH:
			case SKIP_STICHES:
			case TURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(38);
				contextOperation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StitchContext extends ParserRuleContext {
		public TerminalNode STITCH() { return getToken(CrochetParser.STITCH, 0); }
		public TerminalNode NUMBER() { return getToken(CrochetParser.NUMBER, 0); }
		public ContextOperationContext contextOperation() {
			return getRuleContext(ContextOperationContext.class,0);
		}
		public StitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stitch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterStitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitStitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitStitch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StitchContext stitch() throws RecognitionException {
		StitchContext _localctx = new StitchContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stitch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(STITCH);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(42);
				match(NUMBER);
				}
			}

			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) {
				{
				setState(45);
				contextOperation();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public TerminalNode FO() { return getToken(CrochetParser.FO, 0); }
		public TerminalNode MR() { return getToken(CrochetParser.MR, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !(_la==FO || _la==MR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RepeatContext extends ParserRuleContext {
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(CrochetParser.NUMBER, 0); }
		public RepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatContext repeat() throws RecognitionException {
		RepeatContext _localctx = new RepeatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_repeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__4);
			setState(51);
			elementList();
			setState(52);
			match(T__5);
			setState(53);
			match(T__6);
			setState(54);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContextOperationContext extends ParserRuleContext {
		public TerminalNode IN_NEXT_STITCH() { return getToken(CrochetParser.IN_NEXT_STITCH, 0); }
		public TerminalNode IN_SAME_STITCH() { return getToken(CrochetParser.IN_SAME_STITCH, 0); }
		public TerminalNode SKIP_STICHES() { return getToken(CrochetParser.SKIP_STICHES, 0); }
		public TerminalNode NUMBER() { return getToken(CrochetParser.NUMBER, 0); }
		public TerminalNode TURN() { return getToken(CrochetParser.TURN, 0); }
		public ContextOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).enterContextOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrochetListener ) ((CrochetListener)listener).exitContextOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrochetVisitor ) return ((CrochetVisitor<? extends T>)visitor).visitContextOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextOperationContext contextOperation() throws RecognitionException {
		ContextOperationContext _localctx = new ContextOperationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_contextOperation);
		int _la;
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IN_NEXT_STITCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(IN_NEXT_STITCH);
				}
				break;
			case IN_SAME_STITCH:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(IN_SAME_STITCH);
				}
				break;
			case SKIP_STICHES:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				match(SKIP_STICHES);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NUMBER) {
					{
					setState(59);
					match(NUMBER);
					}
				}

				}
				break;
			case TURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				match(TURN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0010B\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0004\u0000\u0012\b\u0000\u000b\u0000\f\u0000\u0013\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002\u001f\b\u0002\n\u0002\f\u0002\"\t"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003(\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0003\u0004,\b\u0004\u0001\u0004\u0003"+
		"\u0004/\b\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007=\b\u0007\u0001\u0007\u0003\u0007@\b\u0007"+
		"\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0001"+
		"\u0001\u0000\t\nD\u0000\u0011\u0001\u0000\u0000\u0000\u0002\u0015\u0001"+
		"\u0000\u0000\u0000\u0004\u001b\u0001\u0000\u0000\u0000\u0006\'\u0001\u0000"+
		"\u0000\u0000\b)\u0001\u0000\u0000\u0000\n0\u0001\u0000\u0000\u0000\f2"+
		"\u0001\u0000\u0000\u0000\u000e?\u0001\u0000\u0000\u0000\u0010\u0012\u0003"+
		"\u0002\u0001\u0000\u0011\u0010\u0001\u0000\u0000\u0000\u0012\u0013\u0001"+
		"\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000\u0013\u0014\u0001"+
		"\u0000\u0000\u0000\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u0016\u0005"+
		"\u0001\u0000\u0000\u0016\u0017\u0005\u000f\u0000\u0000\u0017\u0018\u0005"+
		"\u0002\u0000\u0000\u0018\u0019\u0003\u0004\u0002\u0000\u0019\u001a\u0005"+
		"\u0003\u0000\u0000\u001a\u0003\u0001\u0000\u0000\u0000\u001b \u0003\u0006"+
		"\u0003\u0000\u001c\u001d\u0005\u0004\u0000\u0000\u001d\u001f\u0003\u0006"+
		"\u0003\u0000\u001e\u001c\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000"+
		"\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\u0005"+
		"\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#(\u0003\b\u0004\u0000"+
		"$(\u0003\n\u0005\u0000%(\u0003\f\u0006\u0000&(\u0003\u000e\u0007\u0000"+
		"\'#\u0001\u0000\u0000\u0000\'$\u0001\u0000\u0000\u0000\'%\u0001\u0000"+
		"\u0000\u0000\'&\u0001\u0000\u0000\u0000(\u0007\u0001\u0000\u0000\u0000"+
		")+\u0005\b\u0000\u0000*,\u0005\u000f\u0000\u0000+*\u0001\u0000\u0000\u0000"+
		"+,\u0001\u0000\u0000\u0000,.\u0001\u0000\u0000\u0000-/\u0003\u000e\u0007"+
		"\u0000.-\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/\t\u0001\u0000"+
		"\u0000\u000001\u0007\u0000\u0000\u00001\u000b\u0001\u0000\u0000\u0000"+
		"23\u0005\u0005\u0000\u000034\u0003\u0004\u0002\u000045\u0005\u0006\u0000"+
		"\u000056\u0005\u0007\u0000\u000067\u0005\u000f\u0000\u00007\r\u0001\u0000"+
		"\u0000\u00008@\u0005\u000b\u0000\u00009@\u0005\f\u0000\u0000:<\u0005\r"+
		"\u0000\u0000;=\u0005\u000f\u0000\u0000<;\u0001\u0000\u0000\u0000<=\u0001"+
		"\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000>@\u0005\u000e\u0000\u0000"+
		"?8\u0001\u0000\u0000\u0000?9\u0001\u0000\u0000\u0000?:\u0001\u0000\u0000"+
		"\u0000?>\u0001\u0000\u0000\u0000@\u000f\u0001\u0000\u0000\u0000\u0007"+
		"\u0013 \'+.<?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
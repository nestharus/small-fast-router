// Generated from C:/Users/xteam/IdeaProjects/small-fast-router/src/main/resources/org/nestharus/router/RouteParser.g4 by ANTLR 4.13.2
package org.nestharus.router;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RouteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WILDCARD=1, IDENTIFIER=2, COMMA=3, SLASH=4, GLOB=5, STATIC_TEXT=6, LBRACE=7, 
		LBRACKET=8, VARNAME_WS=9, RBRACE=10, LIST_WS=11, RBRACKET=12, DEFAULT_WILDCARD=13, 
		LIST_COMMA=14;
	public static final int
		RULE_main = 0, RULE_url = 1, RULE_segments = 2, RULE_segment = 3, RULE_wildcard_segment = 4, 
		RULE_wildcard = 5, RULE_glob_segment = 6, RULE_varname = 7, RULE_varname_group = 8, 
		RULE_var_list = 9, RULE_identifier_list = 10, RULE_wildcard_list = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "url", "segments", "segment", "wildcard_segment", "wildcard", 
			"glob_segment", "varname", "varname_group", "var_list", "identifier_list", 
			"wildcard_list"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'/'", "'**'", null, "'{'", "'['", null, "'}'", 
			null, "']'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WILDCARD", "IDENTIFIER", "COMMA", "SLASH", "GLOB", "STATIC_TEXT", 
			"LBRACE", "LBRACKET", "VARNAME_WS", "RBRACE", "LIST_WS", "RBRACKET", 
			"DEFAULT_WILDCARD", "LIST_COMMA"
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
	public String getGrammarFileName() { return "RouteParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RouteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MainContext extends ParserRuleContext {
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RouteParser.EOF, 0); }
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			url();
			setState(25);
			match(EOF);
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
	public static class UrlContext extends ParserRuleContext {
		public List<TerminalNode> SLASH() { return getTokens(RouteParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(RouteParser.SLASH, i);
		}
		public List<SegmentsContext> segments() {
			return getRuleContexts(SegmentsContext.class);
		}
		public SegmentsContext segments(int i) {
			return getRuleContext(SegmentsContext.class,i);
		}
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public Glob_segmentContext glob_segment() {
			return getRuleContext(Glob_segmentContext.class,0);
		}
		public UrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_url; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterUrl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitUrl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitUrl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UrlContext url() throws RecognitionException {
		UrlContext _localctx = new UrlContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_url);
		int _la;
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(SLASH);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WILDCARD || _la==STATIC_TEXT) {
					{
					setState(28);
					segment();
					}
				}

				setState(31);
				segments();
				setState(34);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(32);
					match(SLASH);
					setState(33);
					glob_segment();
					}
					break;
				}
				setState(36);
				segments();
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(37);
					match(SLASH);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				glob_segment();
				setState(41);
				segments();
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(42);
					match(SLASH);
					}
				}

				}
				break;
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
	public static class SegmentsContext extends ParserRuleContext {
		public List<TerminalNode> SLASH() { return getTokens(RouteParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(RouteParser.SLASH, i);
		}
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public SegmentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterSegments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitSegments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitSegments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentsContext segments() throws RecognitionException {
		SegmentsContext _localctx = new SegmentsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_segments);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(47);
					match(SLASH);
					setState(48);
					segment();
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
	public static class SegmentContext extends ParserRuleContext {
		public Wildcard_segmentContext wildcard_segment() {
			return getRuleContext(Wildcard_segmentContext.class,0);
		}
		public TerminalNode STATIC_TEXT() { return getToken(RouteParser.STATIC_TEXT, 0); }
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitSegment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_segment);
		try {
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				wildcard_segment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(STATIC_TEXT);
				}
				break;
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
	public static class Wildcard_segmentContext extends ParserRuleContext {
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public List<TerminalNode> STATIC_TEXT() { return getTokens(RouteParser.STATIC_TEXT); }
		public TerminalNode STATIC_TEXT(int i) {
			return getToken(RouteParser.STATIC_TEXT, i);
		}
		public Wildcard_segmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterWildcard_segment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitWildcard_segment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitWildcard_segment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Wildcard_segmentContext wildcard_segment() throws RecognitionException {
		Wildcard_segmentContext _localctx = new Wildcard_segmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_wildcard_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(58);
				match(STATIC_TEXT);
				}
			}

			setState(61);
			wildcard();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(62);
				match(STATIC_TEXT);
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
	public static class WildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(RouteParser.WILDCARD, 0); }
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public WildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitWildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitWildcard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WildcardContext wildcard() throws RecognitionException {
		WildcardContext _localctx = new WildcardContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(WILDCARD);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(66);
				varname();
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
	public static class Glob_segmentContext extends ParserRuleContext {
		public TerminalNode GLOB() { return getToken(RouteParser.GLOB, 0); }
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public Varname_groupContext varname_group() {
			return getRuleContext(Varname_groupContext.class,0);
		}
		public Glob_segmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_glob_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterGlob_segment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitGlob_segment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitGlob_segment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Glob_segmentContext glob_segment() throws RecognitionException {
		Glob_segmentContext _localctx = new Glob_segmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_glob_segment);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(GLOB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(GLOB);
				setState(71);
				varname();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(GLOB);
				setState(73);
				varname_group();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				match(GLOB);
				setState(75);
				varname();
				setState(76);
				varname_group();
				}
				break;
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
	public static class VarnameContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RouteParser.LBRACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RouteParser.IDENTIFIER, 0); }
		public TerminalNode RBRACE() { return getToken(RouteParser.RBRACE, 0); }
		public VarnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterVarname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitVarname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitVarname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarnameContext varname() throws RecognitionException {
		VarnameContext _localctx = new VarnameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(LBRACE);
			setState(81);
			match(IDENTIFIER);
			setState(82);
			match(RBRACE);
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
	public static class Varname_groupContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(RouteParser.LBRACKET, 0); }
		public Var_listContext var_list() {
			return getRuleContext(Var_listContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(RouteParser.RBRACKET, 0); }
		public Varname_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varname_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterVarname_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitVarname_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitVarname_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Varname_groupContext varname_group() throws RecognitionException {
		Varname_groupContext _localctx = new Varname_groupContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varname_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(LBRACKET);
			setState(85);
			var_list();
			setState(86);
			match(RBRACKET);
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
	public static class Var_listContext extends ParserRuleContext {
		public Wildcard_listContext wildcard_list() {
			return getRuleContext(Wildcard_listContext.class,0);
		}
		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(RouteParser.COMMA, 0); }
		public Var_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterVar_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitVar_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitVar_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_listContext var_list() throws RecognitionException {
		Var_listContext _localctx = new Var_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_var_list);
		int _la;
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WILDCARD:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				wildcard_list();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				identifier_list();
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(90);
					match(COMMA);
					setState(91);
					wildcard_list();
					}
				}

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
	public static class Identifier_listContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RouteParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RouteParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RouteParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RouteParser.COMMA, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitIdentifier_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitIdentifier_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_identifier_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(IDENTIFIER);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(97);
					match(COMMA);
					setState(98);
					match(IDENTIFIER);
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
	public static class Wildcard_listContext extends ParserRuleContext {
		public List<TerminalNode> WILDCARD() { return getTokens(RouteParser.WILDCARD); }
		public TerminalNode WILDCARD(int i) {
			return getToken(RouteParser.WILDCARD, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RouteParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RouteParser.COMMA, i);
		}
		public Wildcard_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterWildcard_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitWildcard_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitWildcard_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Wildcard_listContext wildcard_list() throws RecognitionException {
		Wildcard_listContext _localctx = new Wildcard_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_wildcard_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(WILDCARD);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(105);
				match(COMMA);
				setState(106);
				match(WILDCARD);
				}
				}
				setState(111);
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

	public static final String _serializedATN =
		"\u0004\u0001\u000eq\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001\u001e"+
		"\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001#\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001,\b\u0001\u0003\u0001.\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0005\u00022\b\u0002\n\u0002\f\u00025\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0003\u00039\b\u0003\u0001\u0004\u0003\u0004<\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004@\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005D\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006O\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t]\b\t\u0003\t_\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0005\nd\b\n\n\n\f\ng\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000bl\b\u000b\n\u000b\f\u000bo\t\u000b\u0001\u000b\u0000\u0000\f\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0000v\u0000"+
		"\u0018\u0001\u0000\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u00043\u0001"+
		"\u0000\u0000\u0000\u00068\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000"+
		"\u0000\nA\u0001\u0000\u0000\u0000\fN\u0001\u0000\u0000\u0000\u000eP\u0001"+
		"\u0000\u0000\u0000\u0010T\u0001\u0000\u0000\u0000\u0012^\u0001\u0000\u0000"+
		"\u0000\u0014`\u0001\u0000\u0000\u0000\u0016h\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0003\u0002\u0001\u0000\u0019\u001a\u0005\u0000\u0000\u0001\u001a"+
		"\u0001\u0001\u0000\u0000\u0000\u001b.\u0005\u0004\u0000\u0000\u001c\u001e"+
		"\u0003\u0006\u0003\u0000\u001d\u001c\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f\"\u0003"+
		"\u0004\u0002\u0000 !\u0005\u0004\u0000\u0000!#\u0003\f\u0006\u0000\" "+
		"\u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$&\u0003\u0004\u0002\u0000%\'\u0005\u0004\u0000\u0000&%\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\'.\u0001\u0000\u0000\u0000()\u0003"+
		"\f\u0006\u0000)+\u0003\u0004\u0002\u0000*,\u0005\u0004\u0000\u0000+*\u0001"+
		"\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000,.\u0001\u0000\u0000\u0000"+
		"-\u001b\u0001\u0000\u0000\u0000-\u001d\u0001\u0000\u0000\u0000-(\u0001"+
		"\u0000\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/0\u0005\u0004\u0000"+
		"\u000002\u0003\u0006\u0003\u00001/\u0001\u0000\u0000\u000025\u0001\u0000"+
		"\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0005"+
		"\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u000069\u0003\b\u0004\u0000"+
		"79\u0005\u0006\u0000\u000086\u0001\u0000\u0000\u000087\u0001\u0000\u0000"+
		"\u00009\u0007\u0001\u0000\u0000\u0000:<\u0005\u0006\u0000\u0000;:\u0001"+
		"\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000"+
		"=?\u0003\n\u0005\u0000>@\u0005\u0006\u0000\u0000?>\u0001\u0000\u0000\u0000"+
		"?@\u0001\u0000\u0000\u0000@\t\u0001\u0000\u0000\u0000AC\u0005\u0001\u0000"+
		"\u0000BD\u0003\u000e\u0007\u0000CB\u0001\u0000\u0000\u0000CD\u0001\u0000"+
		"\u0000\u0000D\u000b\u0001\u0000\u0000\u0000EO\u0005\u0005\u0000\u0000"+
		"FG\u0005\u0005\u0000\u0000GO\u0003\u000e\u0007\u0000HI\u0005\u0005\u0000"+
		"\u0000IO\u0003\u0010\b\u0000JK\u0005\u0005\u0000\u0000KL\u0003\u000e\u0007"+
		"\u0000LM\u0003\u0010\b\u0000MO\u0001\u0000\u0000\u0000NE\u0001\u0000\u0000"+
		"\u0000NF\u0001\u0000\u0000\u0000NH\u0001\u0000\u0000\u0000NJ\u0001\u0000"+
		"\u0000\u0000O\r\u0001\u0000\u0000\u0000PQ\u0005\u0007\u0000\u0000QR\u0005"+
		"\u0002\u0000\u0000RS\u0005\n\u0000\u0000S\u000f\u0001\u0000\u0000\u0000"+
		"TU\u0005\b\u0000\u0000UV\u0003\u0012\t\u0000VW\u0005\f\u0000\u0000W\u0011"+
		"\u0001\u0000\u0000\u0000X_\u0003\u0016\u000b\u0000Y\\\u0003\u0014\n\u0000"+
		"Z[\u0005\u0003\u0000\u0000[]\u0003\u0016\u000b\u0000\\Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000]_\u0001\u0000\u0000\u0000^X\u0001\u0000"+
		"\u0000\u0000^Y\u0001\u0000\u0000\u0000_\u0013\u0001\u0000\u0000\u0000"+
		"`e\u0005\u0002\u0000\u0000ab\u0005\u0003\u0000\u0000bd\u0005\u0002\u0000"+
		"\u0000ca\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000"+
		"\u0000\u0000ef\u0001\u0000\u0000\u0000f\u0015\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hm\u0005\u0001\u0000\u0000ij\u0005\u0003\u0000"+
		"\u0000jl\u0005\u0001\u0000\u0000ki\u0001\u0000\u0000\u0000lo\u0001\u0000"+
		"\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000n\u0017"+
		"\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000\u000f\u001d\"&+-38"+
		";?CN\\^em";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
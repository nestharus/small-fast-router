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
		RULE_var_list = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "url", "segments", "segment", "wildcard_segment", "wildcard", 
			"glob_segment", "varname", "varname_group", "var_list"
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
			setState(20);
			url();
			setState(21);
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
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				match(SLASH);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WILDCARD || _la==STATIC_TEXT) {
					{
					setState(24);
					segment();
					}
				}

				setState(27);
				segments();
				setState(30);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(28);
					match(SLASH);
					setState(29);
					glob_segment();
					}
					break;
				}
				setState(32);
				segments();
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(33);
					match(SLASH);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				glob_segment();
				setState(37);
				segments();
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(38);
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
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(43);
					match(SLASH);
					setState(44);
					segment();
					}
					} 
				}
				setState(49);
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
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				wildcard_segment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(54);
				match(STATIC_TEXT);
				}
			}

			setState(57);
			wildcard();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(58);
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
			setState(61);
			match(WILDCARD);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(62);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(GLOB);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(66);
				varname();
				}
			}

			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(69);
				varname_group();
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
			setState(72);
			match(LBRACE);
			setState(73);
			match(IDENTIFIER);
			setState(74);
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
			setState(76);
			match(LBRACKET);
			setState(77);
			var_list();
			setState(78);
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
		public List<TerminalNode> WILDCARD() { return getTokens(RouteParser.WILDCARD); }
		public TerminalNode WILDCARD(int i) {
			return getToken(RouteParser.WILDCARD, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RouteParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RouteParser.COMMA, i);
		}
		public List<VarnameContext> varname() {
			return getRuleContexts(VarnameContext.class);
		}
		public VarnameContext varname(int i) {
			return getRuleContext(VarnameContext.class,i);
		}
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
			int _alt;
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(WILDCARD);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(81);
					match(COMMA);
					setState(82);
					match(WILDCARD);
					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(WILDCARD);
				setState(89);
				varname();
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(90);
						match(COMMA);
						setState(91);
						match(WILDCARD);
						setState(92);
						varname();
						}
						} 
					}
					setState(97);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(98);
					match(COMMA);
					setState(99);
					match(WILDCARD);
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static final String _serializedATN =
		"\u0004\u0001\u000el\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u001f\b\u0001\u0001\u0001\u0001\u0001\u0003\u0001#\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001(\b\u0001\u0003\u0001"+
		"*\b\u0001\u0001\u0002\u0001\u0002\u0005\u0002.\b\u0002\n\u0002\f\u0002"+
		"1\t\u0002\u0001\u0003\u0001\u0003\u0003\u00035\b\u0003\u0001\u0004\u0003"+
		"\u00048\b\u0004\u0001\u0004\u0001\u0004\u0003\u0004<\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0003\u0005@\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006"+
		"D\b\u0006\u0001\u0006\u0003\u0006G\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0005\tT\b\t\n\t\f\tW\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005"+
		"\t^\b\t\n\t\f\ta\t\t\u0001\t\u0001\t\u0005\te\b\t\n\t\f\th\t\t\u0003\t"+
		"j\b\t\u0001\t\u0000\u0000\n\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0000\u0000r\u0000\u0014\u0001\u0000\u0000\u0000\u0002)\u0001\u0000"+
		"\u0000\u0000\u0004/\u0001\u0000\u0000\u0000\u00064\u0001\u0000\u0000\u0000"+
		"\b7\u0001\u0000\u0000\u0000\n=\u0001\u0000\u0000\u0000\fA\u0001\u0000"+
		"\u0000\u0000\u000eH\u0001\u0000\u0000\u0000\u0010L\u0001\u0000\u0000\u0000"+
		"\u0012i\u0001\u0000\u0000\u0000\u0014\u0015\u0003\u0002\u0001\u0000\u0015"+
		"\u0016\u0005\u0000\u0000\u0001\u0016\u0001\u0001\u0000\u0000\u0000\u0017"+
		"*\u0005\u0004\u0000\u0000\u0018\u001a\u0003\u0006\u0003\u0000\u0019\u0018"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b"+
		"\u0001\u0000\u0000\u0000\u001b\u001e\u0003\u0004\u0002\u0000\u001c\u001d"+
		"\u0005\u0004\u0000\u0000\u001d\u001f\u0003\f\u0006\u0000\u001e\u001c\u0001"+
		"\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f \u0001\u0000"+
		"\u0000\u0000 \"\u0003\u0004\u0002\u0000!#\u0005\u0004\u0000\u0000\"!\u0001"+
		"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#*\u0001\u0000\u0000\u0000"+
		"$%\u0003\f\u0006\u0000%\'\u0003\u0004\u0002\u0000&(\u0005\u0004\u0000"+
		"\u0000\'&\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(*\u0001\u0000"+
		"\u0000\u0000)\u0017\u0001\u0000\u0000\u0000)\u0019\u0001\u0000\u0000\u0000"+
		")$\u0001\u0000\u0000\u0000*\u0003\u0001\u0000\u0000\u0000+,\u0005\u0004"+
		"\u0000\u0000,.\u0003\u0006\u0003\u0000-+\u0001\u0000\u0000\u0000.1\u0001"+
		"\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u0000"+
		"0\u0005\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000025\u0003\b\u0004"+
		"\u000035\u0005\u0006\u0000\u000042\u0001\u0000\u0000\u000043\u0001\u0000"+
		"\u0000\u00005\u0007\u0001\u0000\u0000\u000068\u0005\u0006\u0000\u0000"+
		"76\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u000089\u0001\u0000\u0000"+
		"\u00009;\u0003\n\u0005\u0000:<\u0005\u0006\u0000\u0000;:\u0001\u0000\u0000"+
		"\u0000;<\u0001\u0000\u0000\u0000<\t\u0001\u0000\u0000\u0000=?\u0005\u0001"+
		"\u0000\u0000>@\u0003\u000e\u0007\u0000?>\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@\u000b\u0001\u0000\u0000\u0000AC\u0005\u0005\u0000"+
		"\u0000BD\u0003\u000e\u0007\u0000CB\u0001\u0000\u0000\u0000CD\u0001\u0000"+
		"\u0000\u0000DF\u0001\u0000\u0000\u0000EG\u0003\u0010\b\u0000FE\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000G\r\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0007\u0000\u0000IJ\u0005\u0002\u0000\u0000JK\u0005\n\u0000\u0000K\u000f"+
		"\u0001\u0000\u0000\u0000LM\u0005\b\u0000\u0000MN\u0003\u0012\t\u0000N"+
		"O\u0005\f\u0000\u0000O\u0011\u0001\u0000\u0000\u0000PU\u0005\u0001\u0000"+
		"\u0000QR\u0005\u0003\u0000\u0000RT\u0005\u0001\u0000\u0000SQ\u0001\u0000"+
		"\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001"+
		"\u0000\u0000\u0000Vj\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000"+
		"XY\u0005\u0001\u0000\u0000Y_\u0003\u000e\u0007\u0000Z[\u0005\u0003\u0000"+
		"\u0000[\\\u0005\u0001\u0000\u0000\\^\u0003\u000e\u0007\u0000]Z\u0001\u0000"+
		"\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`f\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"bc\u0005\u0003\u0000\u0000ce\u0005\u0001\u0000\u0000db\u0001\u0000\u0000"+
		"\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000iP\u0001"+
		"\u0000\u0000\u0000iX\u0001\u0000\u0000\u0000j\u0013\u0001\u0000\u0000"+
		"\u0000\u0010\u0019\u001e\"\')/47;?CFU_fi";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
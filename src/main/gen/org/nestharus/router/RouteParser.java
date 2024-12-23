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
		IDENTIFIER=1, COMMA=2, SLASH=3, GLOB=4, WILDCARD=5, OPTIONAL=6, STATIC_TEXT=7, 
		LBRACE=8, LBRACKET=9, VARNAME_WS=10, RBRACE=11, LIST_WS=12, RBRACKET=13, 
		LIST_COMMA=14;
	public static final int
		RULE_main = 0, RULE_url = 1, RULE_segment = 2, RULE_static_segment = 3, 
		RULE_wildcard_segment = 4, RULE_glob_segment = 5, RULE_varname = 6, RULE_wildcard = 7, 
		RULE_unnamed_wildcard = 8, RULE_named_wildcard = 9, RULE_varname_group = 10, 
		RULE_var_list = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "url", "segment", "static_segment", "wildcard_segment", "glob_segment", 
			"varname", "wildcard", "unnamed_wildcard", "named_wildcard", "varname_group", 
			"var_list"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'/'", "'**'", null, null, null, "'{'", "'['", null, 
			"'}'", null, "']'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IDENTIFIER", "COMMA", "SLASH", "GLOB", "WILDCARD", "OPTIONAL", 
			"STATIC_TEXT", "LBRACE", "LBRACKET", "VARNAME_WS", "RBRACE", "LIST_WS", 
			"RBRACKET", "LIST_COMMA"
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
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
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
			int _alt;
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
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

				setState(35);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(31);
						match(SLASH);
						setState(32);
						segment();
						}
						} 
					}
					setState(37);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(40);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(38);
					match(SLASH);
					setState(39);
					glob_segment();
					}
					break;
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(42);
						match(SLASH);
						setState(43);
						segment();
						}
						} 
					}
					setState(48);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(49);
					match(SLASH);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				glob_segment();
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(53);
						match(SLASH);
						setState(54);
						segment();
						}
						} 
					}
					setState(59);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(60);
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
	public static class SegmentContext extends ParserRuleContext {
		public Wildcard_segmentContext wildcard_segment() {
			return getRuleContext(Wildcard_segmentContext.class,0);
		}
		public Static_segmentContext static_segment() {
			return getRuleContext(Static_segmentContext.class,0);
		}
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
		enterRule(_localctx, 4, RULE_segment);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				wildcard_segment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				static_segment();
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
	public static class Static_segmentContext extends ParserRuleContext {
		public TerminalNode STATIC_TEXT() { return getToken(RouteParser.STATIC_TEXT, 0); }
		public TerminalNode OPTIONAL() { return getToken(RouteParser.OPTIONAL, 0); }
		public Static_segmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_static_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterStatic_segment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitStatic_segment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitStatic_segment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Static_segmentContext static_segment() throws RecognitionException {
		Static_segmentContext _localctx = new Static_segmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_static_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(STATIC_TEXT);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(70);
				match(OPTIONAL);
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
	public static class Wildcard_segmentContext extends ParserRuleContext {
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public List<Static_segmentContext> static_segment() {
			return getRuleContexts(Static_segmentContext.class);
		}
		public Static_segmentContext static_segment(int i) {
			return getRuleContext(Static_segmentContext.class,i);
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
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(73);
				static_segment();
				}
			}

			setState(76);
			wildcard();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(77);
				static_segment();
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
		public TerminalNode STATIC_TEXT() { return getToken(RouteParser.STATIC_TEXT, 0); }
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
		enterRule(_localctx, 10, RULE_glob_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(80);
				match(STATIC_TEXT);
				}
			}

			setState(83);
			match(GLOB);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(84);
				varname();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(87);
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
		enterRule(_localctx, 12, RULE_varname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(LBRACE);
			setState(91);
			match(IDENTIFIER);
			setState(92);
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
	public static class WildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(RouteParser.WILDCARD, 0); }
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(RouteParser.OPTIONAL, 0); }
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
		enterRule(_localctx, 14, RULE_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(WILDCARD);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(95);
				varname();
				}
			}

			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(98);
				match(OPTIONAL);
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
	public static class Unnamed_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(RouteParser.WILDCARD, 0); }
		public TerminalNode OPTIONAL() { return getToken(RouteParser.OPTIONAL, 0); }
		public Unnamed_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unnamed_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterUnnamed_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitUnnamed_wildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitUnnamed_wildcard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unnamed_wildcardContext unnamed_wildcard() throws RecognitionException {
		Unnamed_wildcardContext _localctx = new Unnamed_wildcardContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unnamed_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(WILDCARD);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(102);
				match(OPTIONAL);
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
	public static class Named_wildcardContext extends ParserRuleContext {
		public TerminalNode WILDCARD() { return getToken(RouteParser.WILDCARD, 0); }
		public VarnameContext varname() {
			return getRuleContext(VarnameContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(RouteParser.OPTIONAL, 0); }
		public Named_wildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_named_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).enterNamed_wildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteParserListener ) ((RouteParserListener)listener).exitNamed_wildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteParserVisitor ) return ((RouteParserVisitor<? extends T>)visitor).visitNamed_wildcard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Named_wildcardContext named_wildcard() throws RecognitionException {
		Named_wildcardContext _localctx = new Named_wildcardContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_named_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(WILDCARD);
			setState(106);
			varname();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(107);
				match(OPTIONAL);
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
		enterRule(_localctx, 20, RULE_varname_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(LBRACKET);
			setState(111);
			var_list();
			setState(112);
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
		public List<Unnamed_wildcardContext> unnamed_wildcard() {
			return getRuleContexts(Unnamed_wildcardContext.class);
		}
		public Unnamed_wildcardContext unnamed_wildcard(int i) {
			return getRuleContext(Unnamed_wildcardContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RouteParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RouteParser.COMMA, i);
		}
		public List<Named_wildcardContext> named_wildcard() {
			return getRuleContexts(Named_wildcardContext.class);
		}
		public Named_wildcardContext named_wildcard(int i) {
			return getRuleContext(Named_wildcardContext.class,i);
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
		enterRule(_localctx, 22, RULE_var_list);
		int _la;
		try {
			int _alt;
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				unnamed_wildcard();
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(115);
					match(COMMA);
					setState(116);
					unnamed_wildcard();
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				named_wildcard();
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(123);
						match(COMMA);
						setState(124);
						named_wildcard();
						}
						} 
					}
					setState(129);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(130);
					match(COMMA);
					setState(131);
					unnamed_wildcard();
					}
					}
					setState(136);
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
		"\u0004\u0001\u000e\u008c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001e\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001\"\b\u0001\n\u0001\f"+
		"\u0001%\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001)\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001-\b\u0001\n\u0001\f\u00010\t\u0001\u0001\u0001"+
		"\u0003\u00013\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"8\b\u0001\n\u0001\f\u0001;\t\u0001\u0001\u0001\u0003\u0001>\b\u0001\u0003"+
		"\u0001@\b\u0001\u0001\u0002\u0001\u0002\u0003\u0002D\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0003\u0003H\b\u0003\u0001\u0004\u0003\u0004K\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004O\b\u0004\u0001\u0005\u0003\u0005R\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001\u0005\u0003\u0005"+
		"Y\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0003\u0007a\b\u0007\u0001\u0007\u0003\u0007d\b\u0007\u0001"+
		"\b\u0001\b\u0003\bh\b\b\u0001\t\u0001\t\u0001\t\u0003\tm\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000bv\b"+
		"\u000b\n\u000b\f\u000by\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b~\b\u000b\n\u000b\f\u000b\u0081\t\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b\u0085\b\u000b\n\u000b\f\u000b\u0088\t\u000b\u0003\u000b\u008a"+
		"\b\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0000\u0000\u0097\u0000\u0018\u0001\u0000\u0000"+
		"\u0000\u0002?\u0001\u0000\u0000\u0000\u0004C\u0001\u0000\u0000\u0000\u0006"+
		"E\u0001\u0000\u0000\u0000\bJ\u0001\u0000\u0000\u0000\nQ\u0001\u0000\u0000"+
		"\u0000\fZ\u0001\u0000\u0000\u0000\u000e^\u0001\u0000\u0000\u0000\u0010"+
		"e\u0001\u0000\u0000\u0000\u0012i\u0001\u0000\u0000\u0000\u0014n\u0001"+
		"\u0000\u0000\u0000\u0016\u0089\u0001\u0000\u0000\u0000\u0018\u0019\u0003"+
		"\u0002\u0001\u0000\u0019\u001a\u0005\u0000\u0000\u0001\u001a\u0001\u0001"+
		"\u0000\u0000\u0000\u001b@\u0005\u0003\u0000\u0000\u001c\u001e\u0003\u0004"+
		"\u0002\u0000\u001d\u001c\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000"+
		"\u0000\u0000\u001e#\u0001\u0000\u0000\u0000\u001f \u0005\u0003\u0000\u0000"+
		" \"\u0003\u0004\u0002\u0000!\u001f\u0001\u0000\u0000\u0000\"%\u0001\u0000"+
		"\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$(\u0001"+
		"\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&\'\u0005\u0003\u0000\u0000"+
		"\')\u0003\n\u0005\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000).\u0001\u0000\u0000\u0000*+\u0005\u0003\u0000\u0000+-\u0003\u0004"+
		"\u0002\u0000,*\u0001\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001"+
		"\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u0000"+
		"0.\u0001\u0000\u0000\u000013\u0005\u0003\u0000\u000021\u0001\u0000\u0000"+
		"\u000023\u0001\u0000\u0000\u00003@\u0001\u0000\u0000\u000049\u0003\n\u0005"+
		"\u000056\u0005\u0003\u0000\u000068\u0003\u0004\u0002\u000075\u0001\u0000"+
		"\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001"+
		"\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000"+
		"<>\u0005\u0003\u0000\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000"+
		"\u0000>@\u0001\u0000\u0000\u0000?\u001b\u0001\u0000\u0000\u0000?\u001d"+
		"\u0001\u0000\u0000\u0000?4\u0001\u0000\u0000\u0000@\u0003\u0001\u0000"+
		"\u0000\u0000AD\u0003\b\u0004\u0000BD\u0003\u0006\u0003\u0000CA\u0001\u0000"+
		"\u0000\u0000CB\u0001\u0000\u0000\u0000D\u0005\u0001\u0000\u0000\u0000"+
		"EG\u0005\u0007\u0000\u0000FH\u0005\u0006\u0000\u0000GF\u0001\u0000\u0000"+
		"\u0000GH\u0001\u0000\u0000\u0000H\u0007\u0001\u0000\u0000\u0000IK\u0003"+
		"\u0006\u0003\u0000JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000"+
		"KL\u0001\u0000\u0000\u0000LN\u0003\u000e\u0007\u0000MO\u0003\u0006\u0003"+
		"\u0000NM\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000O\t\u0001\u0000"+
		"\u0000\u0000PR\u0005\u0007\u0000\u0000QP\u0001\u0000\u0000\u0000QR\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0005\u0004\u0000\u0000"+
		"TV\u0003\f\u0006\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"VX\u0001\u0000\u0000\u0000WY\u0003\u0014\n\u0000XW\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000Y\u000b\u0001\u0000\u0000\u0000Z[\u0005\b\u0000"+
		"\u0000[\\\u0005\u0001\u0000\u0000\\]\u0005\u000b\u0000\u0000]\r\u0001"+
		"\u0000\u0000\u0000^`\u0005\u0005\u0000\u0000_a\u0003\f\u0006\u0000`_\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ac\u0001\u0000\u0000\u0000"+
		"bd\u0005\u0006\u0000\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000"+
		"\u0000d\u000f\u0001\u0000\u0000\u0000eg\u0005\u0005\u0000\u0000fh\u0005"+
		"\u0006\u0000\u0000gf\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000"+
		"h\u0011\u0001\u0000\u0000\u0000ij\u0005\u0005\u0000\u0000jl\u0003\f\u0006"+
		"\u0000km\u0005\u0006\u0000\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000"+
		"\u0000\u0000m\u0013\u0001\u0000\u0000\u0000no\u0005\t\u0000\u0000op\u0003"+
		"\u0016\u000b\u0000pq\u0005\r\u0000\u0000q\u0015\u0001\u0000\u0000\u0000"+
		"rw\u0003\u0010\b\u0000st\u0005\u0002\u0000\u0000tv\u0003\u0010\b\u0000"+
		"us\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000"+
		"\u0000wx\u0001\u0000\u0000\u0000x\u008a\u0001\u0000\u0000\u0000yw\u0001"+
		"\u0000\u0000\u0000z\u007f\u0003\u0012\t\u0000{|\u0005\u0002\u0000\u0000"+
		"|~\u0003\u0012\t\u0000}{\u0001\u0000\u0000\u0000~\u0081\u0001\u0000\u0000"+
		"\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000"+
		"\u0080\u0086\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000"+
		"\u0082\u0083\u0005\u0002\u0000\u0000\u0083\u0085\u0003\u0010\b\u0000\u0084"+
		"\u0082\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086"+
		"\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087"+
		"\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089"+
		"r\u0001\u0000\u0000\u0000\u0089z\u0001\u0000\u0000\u0000\u008a\u0017\u0001"+
		"\u0000\u0000\u0000\u0017\u001d#(.29=?CGJNQUX`cglw\u007f\u0086\u0089";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
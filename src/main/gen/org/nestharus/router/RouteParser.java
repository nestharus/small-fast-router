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
		RULE_main = 0, RULE_url = 1, RULE_segment = 2, RULE_wildcard_segment = 3, 
		RULE_glob_segment = 4, RULE_varname = 5, RULE_wildcard = 6, RULE_unnamed_wildcard = 7, 
		RULE_named_wildcard = 8, RULE_varname_group = 9, RULE_var_list = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "url", "segment", "wildcard_segment", "glob_segment", "varname", 
			"wildcard", "unnamed_wildcard", "named_wildcard", "varname_group", "var_list"
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
			setState(22);
			url();
			setState(23);
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
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				match(SLASH);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WILDCARD || _la==STATIC_TEXT) {
					{
					setState(26);
					segment();
					}
				}

				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(29);
						match(SLASH);
						setState(30);
						segment();
						}
						} 
					}
					setState(35);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(38);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(36);
					match(SLASH);
					setState(37);
					glob_segment();
					}
					break;
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(40);
						match(SLASH);
						setState(41);
						segment();
						}
						} 
					}
					setState(46);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(47);
					match(SLASH);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				glob_segment();
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(51);
						match(SLASH);
						setState(52);
						segment();
						}
						} 
					}
					setState(57);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLASH) {
					{
					setState(58);
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
		enterRule(_localctx, 4, RULE_segment);
		try {
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				wildcard_segment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
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
		enterRule(_localctx, 6, RULE_wildcard_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(67);
				match(STATIC_TEXT);
				}
			}

			setState(70);
			wildcard();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC_TEXT) {
				{
				setState(71);
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
		enterRule(_localctx, 8, RULE_glob_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(GLOB);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(75);
				varname();
				}
			}

			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(78);
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
		enterRule(_localctx, 10, RULE_varname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(LBRACE);
			setState(82);
			match(IDENTIFIER);
			setState(83);
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
		enterRule(_localctx, 12, RULE_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(WILDCARD);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(86);
				varname();
				}
			}

			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(89);
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
		enterRule(_localctx, 14, RULE_unnamed_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(WILDCARD);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(93);
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
		enterRule(_localctx, 16, RULE_named_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(WILDCARD);
			setState(97);
			varname();
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
		enterRule(_localctx, 18, RULE_varname_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(LBRACKET);
			setState(102);
			var_list();
			setState(103);
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
		enterRule(_localctx, 20, RULE_var_list);
		int _la;
		try {
			int _alt;
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				unnamed_wildcard();
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(106);
					match(COMMA);
					setState(107);
					unnamed_wildcard();
					}
					}
					setState(112);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				named_wildcard();
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(114);
						match(COMMA);
						setState(115);
						named_wildcard();
						}
						} 
					}
					setState(120);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(121);
					match(COMMA);
					setState(122);
					unnamed_wildcard();
					}
					}
					setState(127);
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
		"\u0004\u0001\u000e\u0083\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001\u001c\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001 \b\u0001\n\u0001\f\u0001#\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"+\b\u0001\n\u0001\f\u0001.\t\u0001\u0001\u0001\u0003\u00011\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u00016\b\u0001\n\u0001\f\u00019\t"+
		"\u0001\u0001\u0001\u0003\u0001<\b\u0001\u0003\u0001>\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0003\u0002B\b\u0002\u0001\u0003\u0003\u0003E\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003I\b\u0003\u0001\u0004\u0001\u0004\u0003"+
		"\u0004M\b\u0004\u0001\u0004\u0003\u0004P\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0003\u0006X\b\u0006"+
		"\u0001\u0006\u0003\u0006[\b\u0006\u0001\u0007\u0001\u0007\u0003\u0007"+
		"_\b\u0007\u0001\b\u0001\b\u0001\b\u0003\bd\b\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0005\nm\b\n\n\n\f\np\t\n\u0001\n\u0001"+
		"\n\u0001\n\u0005\nu\b\n\n\n\f\nx\t\n\u0001\n\u0001\n\u0005\n|\b\n\n\n"+
		"\f\n\u007f\t\n\u0003\n\u0081\b\n\u0001\n\u0000\u0000\u000b\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0000\u008d\u0000\u0016"+
		"\u0001\u0000\u0000\u0000\u0002=\u0001\u0000\u0000\u0000\u0004A\u0001\u0000"+
		"\u0000\u0000\u0006D\u0001\u0000\u0000\u0000\bJ\u0001\u0000\u0000\u0000"+
		"\nQ\u0001\u0000\u0000\u0000\fU\u0001\u0000\u0000\u0000\u000e\\\u0001\u0000"+
		"\u0000\u0000\u0010`\u0001\u0000\u0000\u0000\u0012e\u0001\u0000\u0000\u0000"+
		"\u0014\u0080\u0001\u0000\u0000\u0000\u0016\u0017\u0003\u0002\u0001\u0000"+
		"\u0017\u0018\u0005\u0000\u0000\u0001\u0018\u0001\u0001\u0000\u0000\u0000"+
		"\u0019>\u0005\u0003\u0000\u0000\u001a\u001c\u0003\u0004\u0002\u0000\u001b"+
		"\u001a\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c"+
		"!\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0003\u0000\u0000\u001e \u0003"+
		"\u0004\u0002\u0000\u001f\u001d\u0001\u0000\u0000\u0000 #\u0001\u0000\u0000"+
		"\u0000!\u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"&\u0001"+
		"\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000$%\u0005\u0003\u0000\u0000"+
		"%\'\u0003\b\u0004\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000"+
		"\u0000\',\u0001\u0000\u0000\u0000()\u0005\u0003\u0000\u0000)+\u0003\u0004"+
		"\u0002\u0000*(\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000\u0000,*\u0001"+
		"\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000"+
		".,\u0001\u0000\u0000\u0000/1\u0005\u0003\u0000\u00000/\u0001\u0000\u0000"+
		"\u000001\u0001\u0000\u0000\u00001>\u0001\u0000\u0000\u000027\u0003\b\u0004"+
		"\u000034\u0005\u0003\u0000\u000046\u0003\u0004\u0002\u000053\u0001\u0000"+
		"\u0000\u000069\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000078\u0001"+
		"\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u0000"+
		":<\u0005\u0003\u0000\u0000;:\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<>\u0001\u0000\u0000\u0000=\u0019\u0001\u0000\u0000\u0000=\u001b"+
		"\u0001\u0000\u0000\u0000=2\u0001\u0000\u0000\u0000>\u0003\u0001\u0000"+
		"\u0000\u0000?B\u0003\u0006\u0003\u0000@B\u0005\u0007\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000B\u0005\u0001\u0000\u0000"+
		"\u0000CE\u0005\u0007\u0000\u0000DC\u0001\u0000\u0000\u0000DE\u0001\u0000"+
		"\u0000\u0000EF\u0001\u0000\u0000\u0000FH\u0003\f\u0006\u0000GI\u0005\u0007"+
		"\u0000\u0000HG\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000I\u0007"+
		"\u0001\u0000\u0000\u0000JL\u0005\u0004\u0000\u0000KM\u0003\n\u0005\u0000"+
		"LK\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MO\u0001\u0000\u0000"+
		"\u0000NP\u0003\u0012\t\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000"+
		"\u0000P\t\u0001\u0000\u0000\u0000QR\u0005\b\u0000\u0000RS\u0005\u0001"+
		"\u0000\u0000ST\u0005\u000b\u0000\u0000T\u000b\u0001\u0000\u0000\u0000"+
		"UW\u0005\u0005\u0000\u0000VX\u0003\n\u0005\u0000WV\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000\u0000Y[\u0005\u0006\u0000"+
		"\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\r\u0001\u0000"+
		"\u0000\u0000\\^\u0005\u0005\u0000\u0000]_\u0005\u0006\u0000\u0000^]\u0001"+
		"\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_\u000f\u0001\u0000\u0000"+
		"\u0000`a\u0005\u0005\u0000\u0000ac\u0003\n\u0005\u0000bd\u0005\u0006\u0000"+
		"\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000d\u0011\u0001"+
		"\u0000\u0000\u0000ef\u0005\t\u0000\u0000fg\u0003\u0014\n\u0000gh\u0005"+
		"\r\u0000\u0000h\u0013\u0001\u0000\u0000\u0000in\u0003\u000e\u0007\u0000"+
		"jk\u0005\u0002\u0000\u0000km\u0003\u000e\u0007\u0000lj\u0001\u0000\u0000"+
		"\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000"+
		"\u0000\u0000o\u0081\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000"+
		"qv\u0003\u0010\b\u0000rs\u0005\u0002\u0000\u0000su\u0003\u0010\b\u0000"+
		"tr\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000"+
		"\u0000vw\u0001\u0000\u0000\u0000w}\u0001\u0000\u0000\u0000xv\u0001\u0000"+
		"\u0000\u0000yz\u0005\u0002\u0000\u0000z|\u0003\u000e\u0007\u0000{y\u0001"+
		"\u0000\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000"+
		"\u0000}~\u0001\u0000\u0000\u0000~\u0081\u0001\u0000\u0000\u0000\u007f"+
		"}\u0001\u0000\u0000\u0000\u0080i\u0001\u0000\u0000\u0000\u0080q\u0001"+
		"\u0000\u0000\u0000\u0081\u0015\u0001\u0000\u0000\u0000\u0015\u001b!&,"+
		"07;=ADHLOWZ^cnv}\u0080";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
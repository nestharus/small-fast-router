// Generated from C:/Users/xteam/IdeaProjects/small-fast-router/src/main/resources/org/nestharus/router/RouteLexer.g4 by ANTLR 4.13.2
package org.nestharus.router;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RouteLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IDENTIFIER=1, COMMA=2, SLASH=3, GLOB=4, WILDCARD=5, OPTIONAL=6, STATIC_TEXT=7, 
		LBRACE=8, LBRACKET=9, VARNAME_WS=10, RBRACE=11, LIST_WS=12, RBRACKET=13, 
		LIST_COMMA=14;
	public static final int
		VARNAME_MODE=1, LIST_MODE=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "VARNAME_MODE", "LIST_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SLASH", "GLOB", "WILDCARD", "OPTIONAL", "STATIC_TEXT", "LBRACE", "LBRACKET", 
			"VARNAME_IDENTIFIER", "VARNAME_WS", "RBRACE", "LIST_IDENTIFIER", "LIST_COMMA", 
			"LIST_WILDCARD", "LIST_WS", "LIST_LBRACE", "LIST_OPTIONAL", "RBRACKET"
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


	public RouteLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RouteLexer.g4"; }

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
		"\u0004\u0000\u000et\u0006\uffff\uffff\u0006\uffff\uffff\u0006\uffff\uffff"+
		"\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002"+
		"\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005"+
		"\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002"+
		"\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007\f\u0002"+
		"\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002\u0010"+
		"\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0004\u0004"+
		"0\b\u0004\u000b\u0004\f\u00041\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0005\u0007>\b\u0007\n\u0007\f\u0007A\t\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0004\bF\b\b\u000b\b\f\bG\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0005\nR\b\n\n\n\f\nU\t\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0004\rb\b\r\u000b\r\f\rc\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0000\u0000\u0011\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b\u0007"+
		"\r\b\u000f\t\u0011\u0000\u0013\n\u0015\u000b\u0017\u0000\u0019\u000e\u001b"+
		"\u0000\u001d\f\u001f\u0000!\u0000#\r\u0003\u0000\u0001\u0002\u0004\t\u0000"+
		"!!$)+.0;==AZ__az~~\u0003\u0000AZ__az\u0004\u000009AZ__az\u0003\u0000\t"+
		"\n\r\r  v\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0001\u0011\u0001\u0000\u0000\u0000"+
		"\u0001\u0013\u0001\u0000\u0000\u0000\u0001\u0015\u0001\u0000\u0000\u0000"+
		"\u0002\u0017\u0001\u0000\u0000\u0000\u0002\u0019\u0001\u0000\u0000\u0000"+
		"\u0002\u001b\u0001\u0000\u0000\u0000\u0002\u001d\u0001\u0000\u0000\u0000"+
		"\u0002\u001f\u0001\u0000\u0000\u0000\u0002!\u0001\u0000\u0000\u0000\u0002"+
		"#\u0001\u0000\u0000\u0000\u0003%\u0001\u0000\u0000\u0000\u0005\'\u0001"+
		"\u0000\u0000\u0000\u0007*\u0001\u0000\u0000\u0000\t,\u0001\u0000\u0000"+
		"\u0000\u000b/\u0001\u0000\u0000\u0000\r3\u0001\u0000\u0000\u0000\u000f"+
		"7\u0001\u0000\u0000\u0000\u0011;\u0001\u0000\u0000\u0000\u0013E\u0001"+
		"\u0000\u0000\u0000\u0015K\u0001\u0000\u0000\u0000\u0017O\u0001\u0000\u0000"+
		"\u0000\u0019X\u0001\u0000\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000"+
		"\u001da\u0001\u0000\u0000\u0000\u001fg\u0001\u0000\u0000\u0000!l\u0001"+
		"\u0000\u0000\u0000#p\u0001\u0000\u0000\u0000%&\u0005/\u0000\u0000&\u0004"+
		"\u0001\u0000\u0000\u0000\'(\u0005*\u0000\u0000()\u0005*\u0000\u0000)\u0006"+
		"\u0001\u0000\u0000\u0000*+\u0005*\u0000\u0000+\b\u0001\u0000\u0000\u0000"+
		",-\u0005?\u0000\u0000-\n\u0001\u0000\u0000\u0000.0\u0007\u0000\u0000\u0000"+
		"/.\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u00001/\u0001\u0000\u0000"+
		"\u000012\u0001\u0000\u0000\u00002\f\u0001\u0000\u0000\u000034\u0005{\u0000"+
		"\u000045\u0001\u0000\u0000\u000056\u0006\u0005\u0000\u00006\u000e\u0001"+
		"\u0000\u0000\u000078\u0005[\u0000\u000089\u0001\u0000\u0000\u00009:\u0006"+
		"\u0006\u0001\u0000:\u0010\u0001\u0000\u0000\u0000;?\u0007\u0001\u0000"+
		"\u0000<>\u0007\u0002\u0000\u0000=<\u0001\u0000\u0000\u0000>A\u0001\u0000"+
		"\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@B\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BC\u0006\u0007\u0002\u0000"+
		"C\u0012\u0001\u0000\u0000\u0000DF\u0007\u0003\u0000\u0000ED\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0006\b\u0003\u0000J\u0014"+
		"\u0001\u0000\u0000\u0000KL\u0005}\u0000\u0000LM\u0001\u0000\u0000\u0000"+
		"MN\u0006\t\u0004\u0000N\u0016\u0001\u0000\u0000\u0000OS\u0007\u0001\u0000"+
		"\u0000PR\u0007\u0002\u0000\u0000QP\u0001\u0000\u0000\u0000RU\u0001\u0000"+
		"\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TV\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VW\u0006\n\u0002\u0000W\u0018"+
		"\u0001\u0000\u0000\u0000XY\u0005,\u0000\u0000YZ\u0001\u0000\u0000\u0000"+
		"Z[\u0006\u000b\u0005\u0000[\u001a\u0001\u0000\u0000\u0000\\]\u0005*\u0000"+
		"\u0000]^\u0001\u0000\u0000\u0000^_\u0006\f\u0006\u0000_\u001c\u0001\u0000"+
		"\u0000\u0000`b\u0007\u0003\u0000\u0000a`\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000ef\u0006\r\u0003\u0000f\u001e\u0001\u0000\u0000"+
		"\u0000gh\u0005{\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0006\u000e\u0000"+
		"\u0000jk\u0006\u000e\u0007\u0000k \u0001\u0000\u0000\u0000lm\u0005?\u0000"+
		"\u0000mn\u0001\u0000\u0000\u0000no\u0006\u000f\b\u0000o\"\u0001\u0000"+
		"\u0000\u0000pq\u0005]\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0006\u0010"+
		"\u0004\u0000s$\u0001\u0000\u0000\u0000\b\u0000\u0001\u00021?GSc\t\u0005"+
		"\u0001\u0000\u0005\u0002\u0000\u0007\u0001\u0000\u0006\u0000\u0000\u0004"+
		"\u0000\u0000\u0007\u0002\u0000\u0007\u0005\u0000\u0007\b\u0000\u0007\u0006"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
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
		WILDCARD=1, IDENTIFIER=2, COMMA=3, SLASH=4, GLOB=5, STATIC_TEXT=6, LBRACE=7, 
		LBRACKET=8, VARNAME_WS=9, RBRACE=10, LIST_WS=11, RBRACKET=12, DEFAULT_WILDCARD=13, 
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
			"SLASH", "GLOB", "STATIC_TEXT", "DEFAULT_WILDCARD", "LBRACE", "LBRACKET", 
			"VARNAME_IDENTIFIER", "VARNAME_WS", "RBRACE", "LIST_IDENTIFIER", "LIST_COMMA", 
			"LIST_WILDCARD", "LIST_WS", "LIST_LBRACE", "RBRACKET"
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
		"\u0004\u0000\u000el\u0006\uffff\uffff\u0006\uffff\uffff\u0006\uffff\uffff"+
		"\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002"+
		"\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005"+
		"\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002"+
		"\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007\f\u0002"+
		"\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0004\u0002(\b\u0002\u000b\u0002"+
		"\f\u0002)\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0005\u0006:\b\u0006\n\u0006\f\u0006"+
		"=\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0004\u0007B\b\u0007\u000b"+
		"\u0007\f\u0007C\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0005\tN\b\t\n\t\f\tQ\t\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0004\f^\b\f\u000b\f\f\f_\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0000\u0000"+
		"\u000f\u0003\u0004\u0005\u0005\u0007\u0006\t\r\u000b\u0007\r\b\u000f\u0000"+
		"\u0011\t\u0013\n\u0015\u0000\u0017\u000e\u0019\u0000\u001b\u000b\u001d"+
		"\u0000\u001f\f\u0003\u0000\u0001\u0002\u0004\t\u0000!!$)+.0;==AZ__az~"+
		"~\u0003\u0000AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  n\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0001\u000f\u0001\u0000"+
		"\u0000\u0000\u0001\u0011\u0001\u0000\u0000\u0000\u0001\u0013\u0001\u0000"+
		"\u0000\u0000\u0002\u0015\u0001\u0000\u0000\u0000\u0002\u0017\u0001\u0000"+
		"\u0000\u0000\u0002\u0019\u0001\u0000\u0000\u0000\u0002\u001b\u0001\u0000"+
		"\u0000\u0000\u0002\u001d\u0001\u0000\u0000\u0000\u0002\u001f\u0001\u0000"+
		"\u0000\u0000\u0003!\u0001\u0000\u0000\u0000\u0005#\u0001\u0000\u0000\u0000"+
		"\u0007\'\u0001\u0000\u0000\u0000\t+\u0001\u0000\u0000\u0000\u000b/\u0001"+
		"\u0000\u0000\u0000\r3\u0001\u0000\u0000\u0000\u000f7\u0001\u0000\u0000"+
		"\u0000\u0011A\u0001\u0000\u0000\u0000\u0013G\u0001\u0000\u0000\u0000\u0015"+
		"K\u0001\u0000\u0000\u0000\u0017T\u0001\u0000\u0000\u0000\u0019X\u0001"+
		"\u0000\u0000\u0000\u001b]\u0001\u0000\u0000\u0000\u001dc\u0001\u0000\u0000"+
		"\u0000\u001fh\u0001\u0000\u0000\u0000!\"\u0005/\u0000\u0000\"\u0004\u0001"+
		"\u0000\u0000\u0000#$\u0005*\u0000\u0000$%\u0005*\u0000\u0000%\u0006\u0001"+
		"\u0000\u0000\u0000&(\u0007\u0000\u0000\u0000\'&\u0001\u0000\u0000\u0000"+
		"()\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000"+
		"\u0000*\b\u0001\u0000\u0000\u0000+,\u0005*\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-.\u0006\u0003\u0000\u0000.\n\u0001\u0000\u0000\u0000/0\u0005{\u0000"+
		"\u000001\u0001\u0000\u0000\u000012\u0006\u0004\u0001\u00002\f\u0001\u0000"+
		"\u0000\u000034\u0005[\u0000\u000045\u0001\u0000\u0000\u000056\u0006\u0005"+
		"\u0002\u00006\u000e\u0001\u0000\u0000\u00007;\u0007\u0001\u0000\u0000"+
		"8:\u0007\u0002\u0000\u000098\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000"+
		"\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<>\u0001\u0000"+
		"\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0006\u0006\u0003\u0000?\u0010"+
		"\u0001\u0000\u0000\u0000@B\u0007\u0003\u0000\u0000A@\u0001\u0000\u0000"+
		"\u0000BC\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000"+
		"\u0000\u0000DE\u0001\u0000\u0000\u0000EF\u0006\u0007\u0004\u0000F\u0012"+
		"\u0001\u0000\u0000\u0000GH\u0005}\u0000\u0000HI\u0001\u0000\u0000\u0000"+
		"IJ\u0006\b\u0005\u0000J\u0014\u0001\u0000\u0000\u0000KO\u0007\u0001\u0000"+
		"\u0000LN\u0007\u0002\u0000\u0000ML\u0001\u0000\u0000\u0000NQ\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PR\u0001"+
		"\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000RS\u0006\t\u0003\u0000S\u0016"+
		"\u0001\u0000\u0000\u0000TU\u0005,\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"VW\u0006\n\u0006\u0000W\u0018\u0001\u0000\u0000\u0000XY\u0005*\u0000\u0000"+
		"YZ\u0001\u0000\u0000\u0000Z[\u0006\u000b\u0000\u0000[\u001a\u0001\u0000"+
		"\u0000\u0000\\^\u0007\u0003\u0000\u0000]\\\u0001\u0000\u0000\u0000^_\u0001"+
		"\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000ab\u0006\f\u0004\u0000b\u001c\u0001\u0000\u0000"+
		"\u0000cd\u0005{\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0006\r\u0001"+
		"\u0000fg\u0006\r\u0007\u0000g\u001e\u0001\u0000\u0000\u0000hi\u0005]\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jk\u0006\u000e\u0005\u0000k \u0001\u0000"+
		"\u0000\u0000\b\u0000\u0001\u0002);CO_\b\u0007\u0001\u0000\u0005\u0001"+
		"\u0000\u0005\u0002\u0000\u0007\u0002\u0000\u0006\u0000\u0000\u0004\u0000"+
		"\u0000\u0007\u0003\u0000\u0007\u0007\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
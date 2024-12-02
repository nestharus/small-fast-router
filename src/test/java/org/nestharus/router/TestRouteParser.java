package org.nestharus.router;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRouteParser {
	public class ErrorTrackingListener extends BaseErrorListener {
		private final List<String> errors = new ArrayList<>();

		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
		                        int line, int charPositionInLine, String msg, RecognitionException e) {
			errors.add("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
		}

		public List<String> getErrors() {
			return errors;
		}
	}

	private ErrorTrackingListener parseInput(final String input) {
		final var charStream = CharStreams.fromString(input);
		final var lexer = new RouteLexer(charStream);
		final var tokens = new CommonTokenStream(lexer);
		final var parser = new RouteParser(tokens);
		final var errorListener = new ErrorTrackingListener();
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		ParseTreeWalker.DEFAULT.walk(new RouteParserBaseListener(), parser.main());

		return errorListener;
	}

	@Test
	public void testValidSlash() {
		final var input = "/";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidStaticPath() {
		final var input = "/static/path/segment";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidStaticPathNoSlash() {
		final var input = "static/path/segment";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidWildcard() {
		final var input = "/path/*";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidWildcardWithVarname() {
		final var input = "/path/*{varname}";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidEndGlob() {
		final var input = "/path/**";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidStartGlob() {
		final var input = "**/hello";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidStartGlobSlash() {
		final var input = "/**/hello";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidMiddleGlob() {
		final var input = "hello/**/hello";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidGlobWithVarname() {
		final var input = "/path/**{varname}";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidGlobWithVarnameAndList() {
		final var input = "/path/**{varname}[foo, bar, *]";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidStaticTextWithWildcard() {
		final var input = "/path/static*";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidWildcardFollowedByStaticText() {
		final var input = "/path/*static";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidWildcardInStaticText() {
		final var input = "/path/static*static";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidListWithOnlyWildcards() {
		final var input = "/path/**[*, *, *]";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidListWithIdentifiersAndWildcards() {
		final var input = "/path/**[foo, bar, *]";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidEmptySegment() {
		final var input = "/";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testValidRootPathWithTrailingSlash() {
		final var input = "/path/";
		final var errors = parseInput(input);
		assertEquals(errors.getErrors(), Collections.emptyList());
	}

	@Test
	public void testInvalidMismatchedBraces() {
		final var input = "/path/{varname";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidMismatchedBrackets() {
		final var input = "/path/**[foo, bar, *";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidStandaloneVarname() {
		final var input = "/path/{varname}";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidGlobWithExtraCharacters() {
		final var input = "/path/**{varname}extra";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidListWithoutComma() {
		final var input = "/path/**[foo bar *]";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidListWithTrailingComma() {
		final var input = "/path/**[foo, bar,]";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidMultiGlobStart() {
		final var input = "**/hi/**";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}

	@Test
	public void testInvalidMultiGlobMiddle() {
		final var input = "/a/**/hi/**";
		final var errors = parseInput(input);
		assertNotEquals(errors.getErrors(), Collections.emptyList());
		assertEquals(1, errors.getErrors().size());
	}
}
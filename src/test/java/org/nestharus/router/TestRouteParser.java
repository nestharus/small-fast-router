package org.nestharus.router;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestRouteParser {
  public class ErrorTrackingListener extends BaseErrorListener {
    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg,
        RecognitionException e) {
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

  @ParameterizedTest
  @ValueSource(
      strings = {
        "/",
        "/path/",
        "/static/path/segment",
        "static/path/segment",
        "/path/*",
        "/path/*?",
        "/path/*{varname}",
        "/path/**",
        "**/hello",
        "/**/hello",
        "hello/**/hello",
        "/path/**{varname}",
        "/path/**{varname}[*{foo}, *{bar}, *]",
        "/path/static*",
        "/path/*static",
        "/path/*?static",
        "/path/static*static",
        "/path/**[*, *, *]",
        "/path/**[*{foo}, *{bar}, *]",
        "/path/**[*{foo}, *{bar}?, *?]",
        "/hello?",
        "/hello?/world?",
        "/required/optional?",
        "/hello?*rawr?",
        "/path?/**[*, *]",
        "/path?/*static?",
        "/static?*?suffix",
      })
  public void testValid(final String input) {
    final var errors = parseInput(input);
    assertEquals(Collections.emptyList(), errors.getErrors());
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "/path/{varname",
        "/path/**[*{foo}, *{bar}, *",
        "/path/{varname}",
        "/path/**{varname}extra",
        "/path/**[*{foo} *{bar} *]",
        "/path/**[*{foo}, *{bar},]",
        "**/hi/**",
        "/a/**/hi/**",
        "//",
        "/?",
        "/**?",
        "/*??",
        "/?/",
        "/hello??",
        "/**??",
        "/path??/**",
      })
  public void testInvalid(final String input) {
    final var errors = parseInput(input);
    assertNotEquals(errors.getErrors(), Collections.emptyList());
    assertEquals(1, errors.getErrors().size());
  }
}

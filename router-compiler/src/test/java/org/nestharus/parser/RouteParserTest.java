package org.nestharus.parser;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RouteParserTest {
  private List<String> parseInput(final String input) {
    final var charStream = CharStreams.fromString(input);
    final var lexer = new RouteLexer(charStream);
    final var tokens = new CommonTokenStream(lexer);
    final var parser = new RouteParser(tokens);
    final var errorListener = new ErrorTrackingListener();
    parser.removeErrorListeners();
    lexer.addErrorListener(errorListener);
    parser.removeErrorListeners();
    parser.addErrorListener(errorListener);
    ParseTreeWalker.DEFAULT.walk(new RouteParserBaseListener(), parser.main());

    return Stream.of(errorListener.getErrors()).flatMap(List::stream).toList();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "/",
        "/a",
        "/aaa",
        "/a?",
        "/a<name>",
        "/a<name>?",
        "/a<hi12_12_a3b>",
        "/aa?a",
        "/aaa?",
        "/*?",
        "/**?",
        "/(a)?",
        "/a?|b?",
        "/!a",
        "/!a?",
        "/!a<name>",
        "/!a<name>?",
        "/!aaa",
        "/aa!a",
        "/!(a)",
        "/!(a)?",
        "/!(a)<name>",
        "/!(a)<name>?",
        "/!a|!b",
        "/!a|b",
        "/a|!b",
        "/a|b|c/",
        "/a<name>",
        "/aaa<name>",
        "/*<name>",
        "/**<name>",
        "/(a)<name>",
        "/a<name>|b<name>",
        "/*[1]",
        "/*[0|2,5]",
        "/**[1]",
        "/*<name>[1]?",
        "/*<name>?",
        "/*<name>[1]",
        "/*[1]?",
        "/!(a)*<name>[1]?",
        "/!(a)*<name>[1]",
        "/!(a)*<name>?",
        "/!(a)*[1]?",
        "/(a)*<name>[1]?",
        "/(a)*<name>[1]",
        "/(a)*<name>?",
        "/(a)*[1]?",
        "/(a)*",
      })
  public void testValid(final String input) {
    final var errors = parseInput(input);
    try {
      assertThat(errors).isEmpty();
    } catch (final AssertionError e) {
      final RouteLexer lexer = new RouteLexer(CharStreams.fromString(input));
      final CommonTokenStream tokens = new CommonTokenStream(lexer);
      tokens.fill();
      tokens.getTokens().stream()
          .map(
              token ->
                  RouteLexer.VOCABULARY.getSymbolicName(token.getType()) + ": " + token.getText())
          .forEach(System.out::println);

      throw e;
    }
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "a",
        "/<1>",
        "/<1>/",
        "//<1>",
        "/a<1>",
        "/a<*>",
        "/a<**>",
        "/a<a,b>",
        "/a<a b>",
        "/a<>",
        "/?",
        "/?/",
        "//?",
        "/a??",
        "/!",
        "/!/",
        "/!*",
        "/!**",
        "/|",
        "/a|",
        "/|b",
        "/[1]",
        "//[1]",
        "/a[1]",
        "/a[1,]",
        "/a[,1]",
        "/a[1,2]",
        "/(a)[1]",
        "/(a)[1,]",
        "/(a)[,1]",
        "/(a)[1,2]",
        "/a|b[1]",
        "/a|b[1,]",
        "/a|b[,1]",
        "/a|b[1,2]",
        "/a|b//",
        "/*[-1]",
        "/*[a]",
        "/*[1,2,3]",
        "/*[*]",
        "/*[**]",
        "/*[1]<name>",
        "/*?[1]",
        "/*?<name>",
        "/*?[1]",
        "/*?<name>",
        "/(a)*[1]<name>",
        "/(a)*?[1]",
        "/(a)*?<name>",
        "/(a)*?[1]",
        "/(a)*?<name>",
      })
  public void testInvalid(final String input) {
    final var errors = parseInput(input);
    try {
      assertThat(errors).isNotEmpty();
    } catch (final AssertionError e) {
      final RouteLexer lexer = new RouteLexer(CharStreams.fromString(input));
      final CommonTokenStream tokens = new CommonTokenStream(lexer);
      tokens.fill();
      tokens.getTokens().stream()
          .map(
              token ->
                  RouteLexer.VOCABULARY.getSymbolicName(token.getType()) + ": " + token.getText())
          .forEach(System.out::println);

      throw e;
    }
  }

  public static class ErrorTrackingListener extends BaseErrorListener {
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
}

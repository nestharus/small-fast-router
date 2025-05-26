package org.nestharus.parser;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.nestharus.parser.listener.AstNegationErrorListener;
import org.nestharus.parser.listener.ListBasedSemanticErrorListener;
import org.nestharus.parser.listener.RouteParserAstBuilderListener;
import org.nestharus.parser.walker.AstWalker;

class AstNegationErrorListenerTest {

  private List<String> analyzeRoute(final String input) {
    final var charStream = CharStreams.fromString(input);
    final var lexer = new RouteLexer(charStream);
    final var tokens = new CommonTokenStream(lexer);
    final var parser = new RouteParser(tokens);

    final var errorListener = new ListBasedSemanticErrorListener();
    final var astBuilderListener = new RouteParserAstBuilderListener();
    astBuilderListener.addErrorListener(errorListener);
    parser.removeErrorListeners();

    ParseTreeWalker.DEFAULT.walk(astBuilderListener, parser.main());

    final var allErrors = new ArrayList<>(errorListener.getErrors());

    if (astBuilderListener.getRootNode() != null) {
      final var negationListener = new AstNegationErrorListener();
      final var negationErrorListener = new ListBasedSemanticErrorListener();
      negationListener.addErrorListener(negationErrorListener);

      final var walker = new AstWalker();
      walker.walk(astBuilderListener.getRootNode(), negationListener);

      allErrors.addAll(negationErrorListener.getErrors());
    }

    return allErrors;
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "/!(a|*)",
        "/!(a|b|*)",
        "/!((a|b)|*)",
        "/!(a*)",
        "/!(*|**)",
        "/!(a|b|*)",
        "/!((a|*)|b)",
        "/!(*[1,2])",
        "/!(**[1,2])",
        "/!(a*[1,2])",
        "/!(a|b|*[2,3])"
      })
  void testNegationOfWildcardElementsProducesError(String route) {
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst())
        .contains("Negation cannot be applied to expressions containing wildcard elements");
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "/!a",
        "/!abc",
        "/!(a)",
        "/!(a|b)",
        "/!(a[1,2])",
        "/!(a|b|c)",
        "/!(!(a))",
        "/!((a|b))",
        "/!(abc|def|ghi)",
        "/!users",
        "/!admin",
        "/!(api|web)",
        "/!(a)*",
        "/!(a)**",
        "/!(a)*[1,5]",
        "/!(a|b)*",
        "/!((a|b))*[0,2]"
      })
  void testValidNegationExpressionsPass(String route) {
    final var errors = analyzeRoute(route);
    final var negationErrors =
        errors.stream().filter(e -> e.contains("wildcard elements")).toList();
    assertThat(negationErrors).isEmpty();
  }

  @Test
  void testComplexNestedNegationWithWildcard() {
    final String route = "/!(a|(b|(*|c)))";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst())
        .contains("Negation cannot be applied to expressions containing wildcard elements");
  }

  @Test
  void testMultipleWildcardErrors() {
    final String route = "/!(a|*|b|**)";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("wildcard elements");
  }

  @Test
  void testNegationInMiddleOfPath() {
    final String route = "/users/!admin/profile";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testWildcardPatternVsWildcardElement() {
    final String route1 = "/!(a*)";
    var errors = analyzeRoute(route1);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("wildcard elements");

    final String route2 = "/!(a)*";
    errors = analyzeRoute(route2);
    final var negationErrors =
        errors.stream().filter(e -> e.contains("wildcard elements")).toList();
    assertThat(negationErrors).isEmpty();
  }
}

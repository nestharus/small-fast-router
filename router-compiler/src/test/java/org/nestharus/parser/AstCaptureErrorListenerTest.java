package org.nestharus.parser;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.nestharus.parser.listener.AstCaptureErrorListener;
import org.nestharus.parser.listener.ListBasedSemanticErrorListener;
import org.nestharus.parser.listener.RouteParserAstBuilderListener;
import org.nestharus.parser.walker.AstWalker;

class AstCaptureErrorListenerTest {

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
      final var captureListener = new AstCaptureErrorListener();
      final var captureErrorListener = new ListBasedSemanticErrorListener();
      captureListener.addErrorListener(captureErrorListener);

      final var walker = new AstWalker();
      walker.walk(astBuilderListener.getRootNode(), captureListener);

      allErrors.addAll(captureErrorListener.getErrors());
    }

    return allErrors;
  }

  @Test
  void testSameLevelConflict() {
    final String route = "/a<one>/b<one>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("Duplicate capture name 'one'");
  }

  @Test
  void testBranchesDoNotConflict() {
    final String route = "/a<one>|b<one>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testWithinGroupConflict() {
    final String route = "/(a<one>b<one>)";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("Duplicate capture name 'one'");
  }

  @Test
  void testNestedGroupsDoNotConflict() {
    final String route = "/a<one>(b<one>(c<one>))";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testWildcardCaptureConflict() {
    final String route = "/*<id>/*<id>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("Duplicate capture name 'id'");
  }

  @Test
  void testMixedTextAndWildcardCapture() {
    final String route = "/user<id>/*<id>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("Duplicate capture name 'id'");
  }

  @Test
  void testValidUniqueCaptures() {
    final String route = "/user<userId>/post<postId>/comment<commentId>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testBranchWithDifferentCaptures() {
    final String route = "/(user<id>|admin<name>)";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testComplexNestedScopes() {
    final String route = "/a<x>(b<y>(c<x>|d<y>)e<x>)f<y>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }

  @Test
  void testMultipleDuplicatesInSameScope() {
    final String route = "/a<one>/b<two>/c<one>/d<two>";
    final var errors = analyzeRoute(route);
    assertThat(errors).hasSize(2);
    assertThat(errors.get(0)).contains("Duplicate capture name 'one'");
    assertThat(errors.get(1)).contains("Duplicate capture name 'two'");
  }

  @Test
  void testStarPatternCaptures() {
    final String route = "/(a)*<count>/(b)*<count>";
    final var errors = analyzeRoute(route);
    assertThat(errors).isNotEmpty();
    assertThat(errors.getFirst()).contains("Duplicate capture name 'count'");
  }

  @Test
  void testBranchExpressionScoping() {
    final String route = "/(a<x>|b<x>|c<x>)";
    final var errors = analyzeRoute(route);
    assertThat(errors).isEmpty();
  }
}

package org.nestharus.parser;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Range;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.nestharus.parser.listener.ListBasedSemanticErrorListener;
import org.nestharus.parser.listener.RouteParserAstBuilderListener;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.*;

class AstRouteBuilderListenerTest {

  private RootNode parseRoute(String route) {
    var lexer = new RouteLexer(CharStreams.fromString(route));
    var tokens = new CommonTokenStream(lexer);
    var parser = new RouteParser(tokens);

    var errorListener = new ListBasedSemanticErrorListener();
    var astBuilder = new RouteParserAstBuilderListener();
    astBuilder.addErrorListener(errorListener);
    parser.removeErrorListeners();

    ParseTreeWalker.DEFAULT.walk(astBuilder, parser.main());

    assertThat(errorListener.getErrors()).isEmpty();
    var rootNode = astBuilder.getRootNode();
    assertThat(rootNode).isNotNull();
    return rootNode;
  }

  // Helper method to strip source nodes from AST for comparison
  private ParserNode stripSourceNodes(ParserNode node) {
    if (node instanceof RootNode) {
      return new RootNode(
          ((RootNode) node)
              .children().stream().map(this::stripSourceNodes).collect(Collectors.toList()));
    } else if (node instanceof SlashNode) {
      return new SlashNode(Optional.empty());
    } else if (node
        instanceof
        TextNode(
            BooleanNode negated,
            Optional<StringNode> captureName,
            WildcardInterval interval,
            StringNode text)) {
      return TextNode.builder()
          .text(new StringNode(text.value(), Optional.empty()))
          .negated(new BooleanNode(negated.value(), Optional.empty()))
          .captureName(captureName.map(s -> new StringNode(s.value(), Optional.empty())))
          .interval(stripSourceNodesFromInterval(interval))
          .build();
    } else if (node
        instanceof
        WildcardNode(
            Optional<StringNode> captureName,
            WildcardInterval interval,
            Optional<SourceNode> sourceNode)) {
      return WildcardNode.builder()
          .captureName(captureName.map(s -> new StringNode(s.value(), Optional.empty())))
          .interval(stripSourceNodesFromInterval(interval))
          .sourceNode(Optional.empty())
          .build();
    } else if (node
        instanceof
        PatternNode(
            BooleanNode negated,
            Optional<StringNode> captureName,
            WildcardInterval interval,
            List<ParserNode> children)) {
      return PatternNode.builder()
          .negated(new BooleanNode(negated.value(), Optional.empty()))
          .captureName(captureName.map(s -> new StringNode(s.value(), Optional.empty())))
          .interval(stripSourceNodesFromInterval(interval))
          .children(children.stream().map(this::stripSourceNodes).collect(Collectors.toList()))
          .build();
    } else if (node instanceof BranchExpressionNode(List<BranchNode> children)) {
      return new BranchExpressionNode(
          children.stream()
              .map(branch -> (BranchNode) stripSourceNodes(branch))
              .collect(Collectors.toList()));
    } else if (node instanceof BranchNode(List<ParserNode> children)) {
      return new BranchNode(
          children.stream().map(this::stripSourceNodes).collect(Collectors.toList()));
    }
    return node;
  }

  private WildcardInterval stripSourceNodesFromInterval(WildcardInterval interval) {
    return WildcardInterval.builder()
        .type(interval.type())
        .interval(
            interval.intervals().stream()
                .map(range -> new RangeNode(range.value(), Optional.empty()))
                .collect(Collectors.toList()))
        .build();
  }

  @Test
  void testSimpleTextPattern() {
    var actual = stripSourceNodes(parseRoute("/hello"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("hello", Optional.empty()))
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testWildcardElement() {
    var actual = stripSourceNodes(parseRoute("/*"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                WildcardNode.builder()
                    .captureName(Optional.empty())
                    .sourceNode(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.atLeast(1), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testTextWithQuestionMark() {
    var actual = stripSourceNodes(parseRoute("/a?"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("a", Optional.empty()))
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(
                                List.of(
                                    new RangeNode(Range.closed(0, 0), Optional.empty()),
                                    new RangeNode(Range.closed(1, 1), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testNegatedText() {
    var actual = stripSourceNodes(parseRoute("/!a"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("a", Optional.empty()))
                    .negated(new BooleanNode(true, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testGroupWithNegation() {
    var actual = stripSourceNodes(parseRoute("/!(a)"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                PatternNode.builder()
                    .negated(new BooleanNode(true, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .children(
                        List.of(
                            TextNode.builder()
                                .text(new StringNode("a", Optional.empty()))
                                .negated(new BooleanNode(false, Optional.empty()))
                                .captureName(Optional.empty())
                                .interval(
                                    WildcardInterval.builder()
                                        .type(WildcardIntervalType.SEGMENT_BOUND)
                                        .interval(
                                            List.of(
                                                new RangeNode(
                                                    Range.singleton(1), Optional.empty())))
                                        .build())
                                .build()))
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testPatternWithInterval() {
    var actual = stripSourceNodes(parseRoute("/(a)*[0,2]"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                PatternNode.builder()
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.closed(0, 2), Optional.empty())))
                            .build())
                    .children(
                        List.of(
                            TextNode.builder()
                                .text(new StringNode("a", Optional.empty()))
                                .negated(new BooleanNode(false, Optional.empty()))
                                .captureName(Optional.empty())
                                .interval(
                                    WildcardInterval.builder()
                                        .type(WildcardIntervalType.SEGMENT_BOUND)
                                        .interval(
                                            List.of(
                                                new RangeNode(
                                                    Range.singleton(1), Optional.empty())))
                                        .build())
                                .build()))
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testWildcardPattern() {
    var actual = stripSourceNodes(parseRoute("/(a)*"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                PatternNode.builder()
                    .children(
                        List.of(
                            TextNode.builder()
                                .text(new StringNode("a", Optional.empty()))
                                .negated(new BooleanNode(false, Optional.empty()))
                                .captureName(Optional.empty())
                                .interval(
                                    WildcardInterval.builder()
                                        .type(WildcardIntervalType.SEGMENT_BOUND)
                                        .interval(
                                            List.of(
                                                new RangeNode(
                                                    Range.singleton(1), Optional.empty())))
                                        .build())
                                .build()))
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testWildcardPatternWithInterval() {
    var actual = stripSourceNodes(parseRoute("/a*[1,2]"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("a", Optional.empty()))
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build(),
                WildcardNode.builder()
                    .captureName(Optional.empty())
                    .sourceNode(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.closed(1, 2), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testBranchExpression() {
    var actual = stripSourceNodes(parseRoute("/(a|b)"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                PatternNode.builder()
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .children(
                        List.of(
                            new BranchExpressionNode(
                                List.of(
                                    new BranchNode(
                                        List.of(
                                            TextNode.builder()
                                                .text(new StringNode("a", Optional.empty()))
                                                .negated(new BooleanNode(false, Optional.empty()))
                                                .captureName(Optional.empty())
                                                .interval(
                                                    WildcardInterval.builder()
                                                        .type(WildcardIntervalType.SEGMENT_BOUND)
                                                        .interval(
                                                            List.of(
                                                                new RangeNode(
                                                                    Range.singleton(1),
                                                                    Optional.empty())))
                                                        .build())
                                                .build())),
                                    new BranchNode(
                                        List.of(
                                            TextNode.builder()
                                                .text(new StringNode("b", Optional.empty()))
                                                .negated(new BooleanNode(false, Optional.empty()))
                                                .captureName(Optional.empty())
                                                .interval(
                                                    WildcardInterval.builder()
                                                        .type(WildcardIntervalType.SEGMENT_BOUND)
                                                        .interval(
                                                            List.of(
                                                                new RangeNode(
                                                                    Range.singleton(1),
                                                                    Optional.empty())))
                                                        .build())
                                                .build()))))))
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void testMultipleSegments() {
    var actual = stripSourceNodes(parseRoute("/users/!admin/profile"));

    var expected =
        new RootNode(
            List.of(
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("users", Optional.empty()))
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build(),
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("admin", Optional.empty()))
                    .negated(new BooleanNode(true, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build(),
                new SlashNode(Optional.empty()),
                TextNode.builder()
                    .text(new StringNode("profile", Optional.empty()))
                    .negated(new BooleanNode(false, Optional.empty()))
                    .captureName(Optional.empty())
                    .interval(
                        WildcardInterval.builder()
                            .type(WildcardIntervalType.SEGMENT_BOUND)
                            .interval(List.of(new RangeNode(Range.singleton(1), Optional.empty())))
                            .build())
                    .build()));

    assertThat(actual).isEqualTo(expected);
  }
}

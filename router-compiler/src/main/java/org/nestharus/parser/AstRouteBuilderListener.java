package org.nestharus.parser;

import java.util.ArrayDeque;
import java.util.Deque;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.node.*;

public class AstRouteBuilderListener extends RouteParserBaseListener {
  private final Deque<ScopeNode> scopeStack;

  public AstRouteBuilderListener(final RootNode root) {
    scopeStack = new ArrayDeque<>();
    scopeStack.push(root);
  }

  private static Range<@NonNull Integer> calculateRange(
      final RouteParser.QuantifierContext ctx, final boolean isOptional) {
    if (ctx == null) {
      if (isOptional) {
        return Range.atLeast(0);
      }

      return Range.atLeast(1);
    }

    final var quantifier = ctx.quantifierElement();

    if (quantifier.quantifierBoundedElement() != null) {
      final var expression = quantifier.quantifierBoundedElement();
      final var leftText = expression.quantifierLeftElement().INTEGER().getText();
      final var rightText = expression.quantifierRightElement().INTEGER().getText();
      return Range.closed(Integer.parseInt(leftText), Integer.parseInt(rightText));
    }

    if (quantifier.quantifierSingleElement() != null) {
      final var expression = quantifier.quantifierSingleElement();
      final var text = expression.INTEGER().getText();
      return Range.singleton(Integer.parseInt(text));
    }

    if (quantifier.quantifierBoundedLowerElement() != null) {
      final var expression = quantifier.quantifierBoundedLowerElement();
      final var text = expression.INTEGER().getText();
      return Range.atLeast(Integer.parseInt(text));
    }

    // if (quantifier.quantifierBoundedUpperElement() != null) {
    final var expression = quantifier.quantifierBoundedUpperElement();
    final var text = expression.INTEGER().getText();
    return Range.atMost(Integer.parseInt(text));
  }

  // BANG? group capture? QMARK?
  @Override
  public void enterGroupExpression(RouteParser.GroupExpressionContext ctx) {
    final var containingScope = scopeStack.peek();

    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;

    final var interval =
        WildcardInterval.builder()
            .interval(calculateRange(null, isOptional))
            .type(WildcardIntervalType.SEGMENT_BOUND)
            .build();

    final var pattern =
        PatternNode.builder()
            .containingScope(containingScope)
            .negated(isNegated)
            .captureName(captureName)
            .interval(interval)
            .build();

    scopeStack.push(pattern);
    containingScope.children().add(pattern);
  }

  @Override
  public void exitGroupExpression(RouteParser.GroupExpressionContext ctx) {
    scopeStack.pop();
  }

  // BANG? group star capture? quantifier? QMARK?
  @Override
  public void enterStarPattern(RouteParser.StarPatternContext ctx) {
    final var containingScope = scopeStack.peek();

    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var intervalType =
        ctx.star().STAR() != null
            ? WildcardIntervalType.SEGMENT_BOUND
            : WildcardIntervalType.SEGMENT_UNBOUND;

    final var interval =
        WildcardInterval.builder()
            .type(intervalType)
            .interval(calculateRange(ctx.quantifier(), isOptional))
            .build();

    final var pattern =
        PatternNode.builder()
            .containingScope(containingScope)
            .negated(isNegated)
            .captureName(captureName)
            .interval(interval)
            .build();

    scopeStack.push(pattern);
    containingScope.children().add(pattern);
  }

  @Override
  public void exitStarPattern(RouteParser.StarPatternContext ctx) {
    scopeStack.pop();
  }

  @Override
  public void enterBranchElement(RouteParser.BranchElementContext ctx) {
    final var containingScope = scopeStack.peek();

    final var branch = BranchNode.builder().containingScope(containingScope).build();

    scopeStack.push(branch);
    containingScope.children().add(branch);
  }

  @Override
  public void exitBranchElement(RouteParser.BranchElementContext ctx) {
    scopeStack.pop();
  }

  @Override
  public void enterBranch(RouteParser.BranchContext ctx) {
    final var containingScope = scopeStack.peek();

    final var branches = BranchNode.builder().containingScope(containingScope).build();

    scopeStack.push(branches);
    containingScope.children().add(branches);
  }

  @Override
  public void exitBranch(RouteParser.BranchContext ctx) {
    scopeStack.pop();
  }

  // BANG? STATIC_TEXT capture? QMARK?
  @Override
  public void enterTextExpression(RouteParser.TextExpressionContext ctx) {
    final var containingScope = scopeStack.peek();

    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var interval =
        WildcardInterval.builder()
            .type(WildcardIntervalType.SEGMENT_BOUND)
            .interval(calculateRange(null, isOptional))
            .build();
    final var text = ctx.STATIC_TEXT().getText();

    final var node =
        TextNode.builder()
            .containingScope(containingScope)
            .negated(isNegated)
            .captureName(captureName)
            .interval(interval)
            .text(text)
            .build();

    containingScope.children().add(node);
  }

  // star capture? quantifier? QMARK?
  @Override
  public void enterStarExpression(RouteParser.StarExpressionContext ctx) {
    final var containingScope = scopeStack.peek();

    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var intervalType =
        ctx.star().STAR() != null
            ? WildcardIntervalType.SEGMENT_BOUND
            : WildcardIntervalType.SEGMENT_UNBOUND;

    final var interval =
        WildcardInterval.builder()
            .interval(calculateRange(ctx.quantifier(), isOptional))
            .type(intervalType)
            .build();

    final var node =
        WildcardNode.builder()
            .containingScope(containingScope)
            .captureName(captureName)
            .interval(interval)
            .build();

    containingScope.children().add(node);
  }

  @Override
  public void enterSlash(RouteParser.SlashContext ctx) {
    final var containingScope = scopeStack.peek();

    final var node =
        TextNode.builder().containingScope(containingScope).text(ctx.SLASH().getText()).build();

    containingScope.children().add(node);
  }
}

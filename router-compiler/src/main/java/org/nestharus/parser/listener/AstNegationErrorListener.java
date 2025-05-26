package org.nestharus.parser.listener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.*;

/**
 * Validates negation operator usage in route patterns according to semantic rules: - Negation
 * cannot be applied to wildcard elements (* or **) - This rule applies recursively through nested
 * expressions
 */
public class AstNegationErrorListener extends AstBaseListener {
  private final Deque<SourceNode> negationSourceStack = new ArrayDeque<>();
  private final List<SemanticErrorListener> errorListeners = new ArrayList<>();

  public void addErrorListener(final SemanticErrorListener listener) {
    errorListeners.add(listener);
  }

  @Override
  public void enterPatternNode(final PatternNode node) {
    if (node.negated().value() && node.negated().sourceNode().isPresent()) {
      negationSourceStack.push(node.negated().sourceNode().get());
    }
  }

  @Override
  public void exitPatternNode(final PatternNode node) {
    if (node.negated().value()) {
      negationSourceStack.pop();
    }
  }

  @Override
  public void enterTextNode(final TextNode node) {
    if (node.negated().value() && node.negated().sourceNode().isPresent()) {
      negationSourceStack.push(node.negated().sourceNode().get());
    }
  }

  @Override
  public void exitTextNode(final TextNode node) {
    if (node.negated().value()) {
      negationSourceStack.pop();
    }
  }

  @Override
  public void enterWildcardNode(final WildcardNode node) {
    if (!negationSourceStack.isEmpty()) {
      final SourceNode negationSource = negationSourceStack.peek();
      final String wildcardType =
          node.interval().type() == WildcardIntervalType.SEGMENT_UNBOUND ? "**" : "*";

      final SourceNode wildcardSource = node.sourceNode().get();
      final String errorMessage =
          String.format(
              "Negation cannot be applied to expressions containing wildcard elements (* or **). "
                  + "Found '%s' wildcard at line %d:%d that makes the negation invalid",
              wildcardType, wildcardSource.lineNumber(), wildcardSource.columnNumber() + 1);

      errorListeners.forEach(
          listener -> listener.reportSemanticError(errorMessage, negationSource));
    }
  }
}

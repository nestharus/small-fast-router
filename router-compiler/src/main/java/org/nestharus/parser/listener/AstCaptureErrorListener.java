package org.nestharus.parser.listener;

import java.util.*;

import org.nestharus.parser.value.*;

/**
 * Validates capture name uniqueness within scopes according to semantic rules: - Captures at the
 * same level conflict: /a<one>/b<one> conflicts - Parentheses create new scopes: (a<one>b<one>)
 * conflicts within the group - Branches create separate scopes: a<one>|b<one> does NOT conflict -
 * Nested groups create separate scopes: a<one>(b<one>(c<one>)) does NOT conflict
 */
public class AstCaptureErrorListener extends AstBaseListener {
  private final Deque<Set<String>> scopeStack = new ArrayDeque<>();
  private final List<SemanticErrorListener> errorListeners = new ArrayList<>();

  public void addErrorListener(final SemanticErrorListener listener) {
    errorListeners.add(listener);
  }

  @Override
  public void enterRootNode(final RootNode node) {
    scopeStack.push(new HashSet<>());
  }

  @Override
  public void exitRootNode(final RootNode node) {
    scopeStack.pop();
  }

  @Override
  public void enterPatternNode(final PatternNode node) {
    checkCapture(node.captureName());
    scopeStack.push(new HashSet<>());
  }

  @Override
  public void exitPatternNode(final PatternNode node) {
    scopeStack.pop();
  }

  @Override
  public void enterBranchNode(final BranchNode node) {
    scopeStack.push(new HashSet<>());
  }

  @Override
  public void exitBranchNode(final BranchNode node) {
    scopeStack.pop();
  }

  private void checkCapture(final Optional<StringNode> captureName) {
    if (captureName.isPresent() && !scopeStack.isEmpty()) {
      final StringNode capture = captureName.get();
      final String name = capture.value();
      final Set<String> currentScope = scopeStack.peek();

      if (currentScope.contains(name)) {
        final String errorMessage =
            String.format("Duplicate capture name '%s' in the same scope", name);

        if (capture.sourceNode().isPresent()) {
          errorListeners.forEach(
              listener -> listener.reportSemanticError(errorMessage, capture.sourceNode().get()));
        }
      } else {
        currentScope.add(name);
      }
    }
  }

  @Override
  public void enterTextNode(final TextNode node) {
    checkCapture(node.captureName());
  }

  @Override
  public void enterWildcardNode(final WildcardNode node) {
    checkCapture(node.captureName());
  }
}

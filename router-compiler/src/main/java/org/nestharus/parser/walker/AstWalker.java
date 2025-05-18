package org.nestharus.parser.walker;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.nestharus.parser.listener.AstListener;
import org.nestharus.parser.value.BranchExpressionNode;
import org.nestharus.parser.value.BranchNode;
import org.nestharus.parser.value.ParserNode;
import org.nestharus.parser.value.PatternNode;
import org.nestharus.parser.value.RootNode;
import org.nestharus.parser.value.SlashNode;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardNode;

/**
 * Walker for traversing an AST built by RouteParserAstBuilderListener. Provides depth-first
 * traversal with enter/exit callbacks for each node type.
 */
public class AstWalker {

  /**
   * Walks the AST starting from the root node, notifying the listener of enter/exit events for each
   * node.
   *
   * @param root the root node to start walking from
   * @param listener the listener to notify of traversal events
   */
  public void walk(@NonNull RootNode root, @NonNull AstListener listener) {
    walkNode(root, listener);
  }

  private void walkNode(@NonNull ParserNode node, @NonNull AstListener listener) {
    try {
      switch (node.type()) {
        case ROOT -> walkRootNode((RootNode) node, listener);
        case BRANCH_EXPRESSION -> walkBranchExpressionNode((BranchExpressionNode) node, listener);
        case BRANCH -> walkBranchNode((BranchNode) node, listener);
        case PATTERN -> walkPatternNode((PatternNode) node, listener);
        case TEXT -> walkTextNode((TextNode) node, listener);
        case WILDCARD -> walkWildcardNode((WildcardNode) node, listener);
        case SLASH -> walkSlashNode((SlashNode) node, listener);
      }
    } catch (final RuntimeException ignored) {
      // Converted into errors; allow signal to stop walker
    }
  }

  private void walkRootNode(@NonNull RootNode node, @NonNull AstListener listener) {
    listener.enterRootNode(node);
    walkChildren(node.children(), listener);
    listener.exitRootNode(node);
  }

  private void walkBranchExpressionNode(
      @NonNull BranchExpressionNode node, @NonNull AstListener listener) {
    listener.enterBranchExpressionNode(node);
    node.children().forEach(branch -> walkNode(branch, listener));
    listener.exitBranchExpressionNode(node);
  }

  private void walkBranchNode(@NonNull BranchNode node, @NonNull AstListener listener) {
    listener.enterBranchNode(node);
    walkChildren(node.children(), listener);
    listener.exitBranchNode(node);
  }

  private void walkPatternNode(@NonNull PatternNode node, @NonNull AstListener listener) {
    listener.enterPatternNode(node);
    walkChildren(node.children(), listener);
    listener.exitPatternNode(node);
  }

  private void walkTextNode(@NonNull TextNode node, @NonNull AstListener listener) {
    listener.enterTextNode(node);
    listener.exitTextNode(node);
  }

  private void walkWildcardNode(@NonNull WildcardNode node, @NonNull AstListener listener) {
    listener.enterWildcardNode(node);
    listener.exitWildcardNode(node);
  }

  private void walkSlashNode(@NonNull SlashNode node, @NonNull AstListener listener) {
    listener.enterSlashNode(node);
    listener.exitSlashNode(node);
  }

  private void walkChildren(
      @NonNull List<? extends ParserNode> children, @NonNull AstListener listener) {
    children.forEach(child -> walkNode(child, listener));
  }
}

package org.nestharus.parser.listener;

import org.nestharus.parser.value.BranchExpressionNode;
import org.nestharus.parser.value.BranchNode;
import org.nestharus.parser.value.PatternNode;
import org.nestharus.parser.value.RootNode;
import org.nestharus.parser.value.SlashNode;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardNode;

/**
 * Interface for listening to AST node traversal events. Provides enter and exit callbacks for each
 * AST node type.
 */
public interface AstListener {

  /**
   * Called when entering a RootNode.
   *
   * @param node the root node being entered
   */
  void enterRootNode(RootNode node);

  /**
   * Called when exiting a RootNode.
   *
   * @param node the root node being exited
   */
  void exitRootNode(RootNode node);

  /**
   * Called when entering a BranchExpressionNode.
   *
   * @param node the branch expression node being entered
   */
  void enterBranchExpressionNode(BranchExpressionNode node);

  /**
   * Called when exiting a BranchExpressionNode.
   *
   * @param node the branch expression node being exited
   */
  void exitBranchExpressionNode(BranchExpressionNode node);

  /**
   * Called when entering a BranchNode.
   *
   * @param node the branch node being entered
   */
  void enterBranchNode(BranchNode node);

  /**
   * Called when exiting a BranchNode.
   *
   * @param node the branch node being exited
   */
  void exitBranchNode(BranchNode node);

  /**
   * Called when entering a PatternNode.
   *
   * @param node the pattern node being entered
   */
  void enterPatternNode(PatternNode node);

  /**
   * Called when exiting a PatternNode.
   *
   * @param node the pattern node being exited
   */
  void exitPatternNode(PatternNode node);

  /**
   * Called when entering a TextNode.
   *
   * @param node the value node being entered
   */
  void enterTextNode(TextNode node);

  /**
   * Called when exiting a TextNode.
   *
   * @param node the value node being exited
   */
  void exitTextNode(TextNode node);

  /**
   * Called when entering a WildcardNode.
   *
   * @param node the wildcard node being entered
   */
  void enterWildcardNode(WildcardNode node);

  /**
   * Called when exiting a WildcardNode.
   *
   * @param node the wildcard node being exited
   */
  void exitWildcardNode(WildcardNode node);

  /**
   * Called when entering a SlashNode.
   *
   * @param node the slash node being entered
   */
  void enterSlashNode(SlashNode node);

  /**
   * Called when exiting a SlashNode.
   *
   * @param node the slash node being exited
   */
  void exitSlashNode(SlashNode node);
}

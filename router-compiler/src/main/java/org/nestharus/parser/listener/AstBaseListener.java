package org.nestharus.parser.listener;

import org.nestharus.parser.value.BranchExpressionNode;
import org.nestharus.parser.value.BranchNode;
import org.nestharus.parser.value.PatternNode;
import org.nestharus.parser.value.RootNode;
import org.nestharus.parser.value.SlashNode;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardNode;

/**
 * Base implementation of AstListener with empty default methods. Extend this class and override
 * only the methods you need.
 */
public class AstBaseListener implements AstListener {

  @Override
  public void enterRootNode(RootNode node) {}

  @Override
  public void exitRootNode(RootNode node) {}

  @Override
  public void enterBranchExpressionNode(BranchExpressionNode node) {}

  @Override
  public void exitBranchExpressionNode(BranchExpressionNode node) {}

  @Override
  public void enterBranchNode(BranchNode node) {}

  @Override
  public void exitBranchNode(BranchNode node) {}

  @Override
  public void enterPatternNode(PatternNode node) {}

  @Override
  public void exitPatternNode(PatternNode node) {}

  @Override
  public void enterTextNode(TextNode node) {}

  @Override
  public void exitTextNode(TextNode node) {}

  @Override
  public void enterWildcardNode(WildcardNode node) {}

  @Override
  public void exitWildcardNode(WildcardNode node) {}

  @Override
  public void enterSlashNode(SlashNode node) {}

  @Override
  public void exitSlashNode(SlashNode node) {}
}

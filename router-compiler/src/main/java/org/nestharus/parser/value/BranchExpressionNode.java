package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.ParserNodeType;

public record BranchExpressionNode(@NonNull List<BranchNode> children)
    implements ParserNode, GroupNode<BranchNode> {
  public BranchExpressionNode {
    Objects.requireNonNull(children, "property :children is required");
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.BRANCH_EXPRESSION;
  }
}

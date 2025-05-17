package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;

public record RootNode(List<ParserNode> children) implements ParserNode, ScopeNode {
  RootNode() {
    this(new ArrayList<>());
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.ROOT;
  }

  @Override
  public ScopeNode containingScope() {
    return null;
  }
}

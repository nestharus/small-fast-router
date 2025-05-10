package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RootNode implements ParserNode, ScopeNode {
  private final List<ParserNode> children = new ArrayList<>();

  @Override
  public ParserNodeType getType() {
    return ParserNodeType.ROOT;
  }

  @Override
  public ScopeNode getContainingScope() {
    return null;
  }
}

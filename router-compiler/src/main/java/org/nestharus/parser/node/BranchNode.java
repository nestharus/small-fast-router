package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class BranchNode implements ParserNode, ScopeNode {
  @NonNull private ScopeNode containingScope;
  private final List<ParserNode> children = new ArrayList<>();

  @Override
  public ParserNodeType getType() {
    return ParserNodeType.BRANCH;
  }
}

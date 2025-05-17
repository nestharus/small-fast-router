package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.ParserNodeType;

public record RootNode(@NonNull List<ParserNode> children)
    implements ParserNode, GroupNode<ParserNode> {
  public RootNode {
    Objects.requireNonNull(children, "property :children is required");
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.ROOT;
  }
}

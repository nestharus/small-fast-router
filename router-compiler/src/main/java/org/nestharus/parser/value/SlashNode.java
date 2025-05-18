package org.nestharus.parser.value;

import java.util.Optional;

import org.nestharus.parser.type.ParserNodeType;

public record SlashNode(Optional<SourceNode> sourceNode) implements ParserNode, ValueNode {
  @Override
  public ParserNodeType type() {
    return ParserNodeType.SLASH;
  }
}

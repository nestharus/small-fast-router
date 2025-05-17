package org.nestharus.parser.value;

import org.nestharus.parser.type.ParserNodeType;

public record SlashNode() implements ParserNode {
  @Override
  public ParserNodeType type() {
    return ParserNodeType.SLASH;
  }
}

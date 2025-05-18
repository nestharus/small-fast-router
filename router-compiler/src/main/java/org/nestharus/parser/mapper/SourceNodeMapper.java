package org.nestharus.parser.mapper;

import org.antlr.v4.runtime.Token;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.value.SourceNode;

public class SourceNodeMapper {
  public static SourceNode fromToken(@NonNull final Token token) {
    return SourceNode.builder()
        .lineNumber(token.getLine())
        .columnNumber(token.getCharPositionInLine())
        .text(token.getText())
        .build();
  }
}

package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.nestharus.parser.value.StringNode;

public class StringNodeMapper {
  public static Optional<StringNode> fromToken(final Optional<Token> optionalToken) {
    return optionalToken.map(StringNodeMapper::fromToken);
  }

  public static StringNode fromToken(final Token token) {
    return new StringNode(token.getText(), Optional.of(SourceNodeMapper.fromToken(token)));
  }
}

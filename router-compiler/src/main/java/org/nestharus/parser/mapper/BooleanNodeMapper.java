package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.nestharus.parser.value.BooleanNode;

public class BooleanNodeMapper {
  public static BooleanNode fromToken(final Optional<Token> optionalToken) {
    final var source = optionalToken.map(SourceNodeMapper::fromToken);
    final var value = optionalToken.isPresent();

    return new BooleanNode(value, source);
  }
}

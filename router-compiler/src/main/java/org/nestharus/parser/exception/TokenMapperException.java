package org.nestharus.parser.exception;

import java.util.List;

import org.antlr.v4.runtime.Token;

public class TokenMapperException extends Exception {
  private final List<Token> tokens;

  public TokenMapperException(final List<Token> sourceNodes, final String message) {
    super(message);

    this.tokens = sourceNodes;
  }

  public List<Token> getTokens() {
    return tokens;
  }
}

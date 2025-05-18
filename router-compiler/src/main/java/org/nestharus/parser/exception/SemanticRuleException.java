package org.nestharus.parser.exception;

import org.nestharus.parser.value.SourceNode;

public class SemanticRuleException extends RuntimeException {
  private final SourceNode source;

  public SemanticRuleException(final SourceNode source, final String message) {
    super(message);

    this.source = source;
  }

  public SourceNode getSource() {
    return source;
  }
}

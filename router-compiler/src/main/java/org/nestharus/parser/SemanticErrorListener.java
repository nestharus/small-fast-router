package org.nestharus.parser;

import org.antlr.v4.runtime.Token;

/** Interface for listeners that handle semantic errors detected during parse tree analysis. */
public interface SemanticErrorListener {

  /**
   * Reports a semantic error.
   *
   * @param message The specific error message.
   * @param offendingToken The token that relates to the error, used for location info.
   */
  void reportSemanticError(String message, Token offendingToken);
}

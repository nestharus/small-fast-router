package org.nestharus.parser.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.Token;

/**
 * A SemanticErrorListener that collects reported errors into a list of formatted strings. The
 * format mimics the syntax error reporting for consistency.
 */
public class ListBasedSemanticErrorListener implements SemanticErrorListener {

  private final List<String> errors = new ArrayList<>();

  @Override
  public void reportSemanticError(String message, Token offendingToken) {
    int line = offendingToken.getLine();
    int charPositionInLine = offendingToken.getCharPositionInLine();
    // Format matches ErrorTrackingListener: "Type Error at line L:C: message (near 'text')"
    String formattedMessage =
        String.format(
            "Semantic Error at line %d:%d: %s (near '%s')",
            line,
            charPositionInLine + 1, // User-friendly 1-based column
            message,
            offendingToken.getText());
    errors.add(formattedMessage);
  }

  /**
   * Gets the list of semantic errors collected.
   *
   * @return An unmodifiable list of formatted error messages.
   */
  public List<String> getErrors() {
    return Collections.unmodifiableList(errors);
  }

  /**
   * Checks if any semantic errors have been reported.
   *
   * @return true if errors have been reported, false otherwise.
   */
  public boolean hasErrors() {
    return !errors.isEmpty();
  }
}

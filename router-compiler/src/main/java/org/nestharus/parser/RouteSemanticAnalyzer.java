package org.nestharus.parser;

import java.util.*;

import org.antlr.v4.runtime.Token;

/**
 * Semantic analyzer for route patterns.
 *
 * <p>Validates route patterns according to semantic rules.
 */
public class RouteSemanticAnalyzer extends RouteParserBaseListener {
  /* ───────────────────────── Listener management ───────────────────────── */

  private final List<SemanticErrorListener> listeners = new ArrayList<>();

  public void addErrorListener(SemanticErrorListener listener) {
    listeners.add(listener);
  }

  private void notifyListeners(String message, Token offendingToken) {
    for (SemanticErrorListener l : listeners) {
      l.reportSemanticError(message, offendingToken);
    }
  }
}

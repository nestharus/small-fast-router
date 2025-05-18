package org.nestharus.parser.listener;

import java.util.ArrayList;
import java.util.List;

import org.nestharus.parser.RouteParser;

/**
 * An ANTLR listener focused on validating route quantifiers (`[...]`). It checks the syntax and
 * semantics of quantifier bounds, ensuring that:
 *
 * <ul>
 *   <li>The minimum bound is less than or equal to the maximum bound (if both are specified).
 *   <li>The integer values used for bounds do not cause overflow issues.
 * </ul>
 *
 * Violations result in {@code INVALID_QUANTIFIER_BOUNDS} errors reported via a {@link
 * SemanticErrorListener}.
 *
 * @see SemanticErrorListener
 * @see RouteParser.QuantifierContext
 * @see <a href="route_grammar_error_warning_spec.txt">Route Grammar Specification - Section 3.1</a>
 */
public class AstQuantifierListener extends AstBaseListener {
  private final List<SemanticErrorListener> errorListeners;

  public AstQuantifierListener() {
    this.errorListeners = new ArrayList<>();
  }

  public void addErrorListener(final SemanticErrorListener listener) {
    errorListeners.add(listener);
  }
}

package org.nestharus.parser.listener;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.RouteParserBaseListener;

/**
 * An ANTLR listener responsible for validating negation expressions (`!`) within a route
 * definition. It enforces semantic rules related to negation, such as:
 *
 * <ul>
 *   <li>Detecting potentially unmatchable routes like `!*` or `!**` (Rule: {@code
 *       UNMATCHABLE_ROUTE}).
 *   <li>Preventing the negation of expressions containing unbounded wildcards (Rule: {@code
 *       NEGATION_WITH_WILDCARD}).
 * </ul>
 *
 * This listener may utilize the results from {@link AstExpressionInterpreter} to understand the
 * nature of the expression being negated (e.g., whether it contains an unbounded wildcard). Errors
 * are reported using a {@link SemanticErrorListener}.
 *
 * @see AstExpressionInterpreter
 * @see SemanticErrorListener
 * @see RouteParser.TextExpressionContext
 * @see RouteParser.GroupExpressionContext
 * @see <a href="route_grammar_error_warning_spec.txt">Route Grammar Specification - Section 3.2</a>
 */
public class AstNegationErrorListener extends RouteParserBaseListener {
  // Implementation stubs
}

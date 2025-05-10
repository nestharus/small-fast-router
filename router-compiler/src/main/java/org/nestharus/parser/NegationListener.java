package org.nestharus.parser;

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
 * This listener may utilize the results from {@link ExpressionInterpreter} to understand the nature
 * of the expression being negated (e.g., whether it contains an unbounded wildcard). Errors are
 * reported using a {@link SemanticErrorListener}.
 *
 * @see ExpressionInterpreter
 * @see SemanticErrorListener
 * @see RouteParser.TextExpressionContext
 * @see RouteParser.GroupExpressionContext
 * @see <a href="route_grammar_error_warning_spec.txt">Route Grammar Specification - Section 3.2</a>
 */
public class NegationListener extends RouteParserBaseListener {
  // Implementation stubs
}

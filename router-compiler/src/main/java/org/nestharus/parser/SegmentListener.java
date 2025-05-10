package org.nestharus.parser;

/**
 * An ANTLR listener that processes individual route segments (the parts between '/'). Its specific
 * responsibilities might include:
 *
 * <ul>
 *   <li>Identifying the start and end of segments.
 *   <li>Performing validation checks specific to segments, such as ensuring no empty segments
 *       (`//`) exist if the grammar allows them syntactically but they are semantically invalid
 *       (Parser Rule 2.2).
 *   <li>Potentially gathering information about the sequence of segments.
 * </ul>
 *
 * @see RouteParser.ElementContext
 * @see <a href="route_grammar_error_warning_spec.txt">Route Grammar Specification - Section 2.2</a>
 */
public class SegmentListener extends RouteParserBaseListener {
  // Implementation stubs
}

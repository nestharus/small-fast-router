package org.nestharus.parser.listener;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.RouteParserBaseListener;

/**
 * An ANTLR listener responsible for identifying route capture groups (`&lt;name&gt;`) during a
 * parse tree walk. It validates capture group usage, primarily checking for duplicate capture names
 * within the same logical scope.
 *
 * <p>This listener relies on {@link RouteParserAstBuilderListener} to determine the current scope
 * when validating capture names. It reports errors using a {@link SemanticErrorListener}.
 *
 * @see RouteParserAstBuilderListener
 * @see SemanticErrorListener
 * @see RouteParser.CaptureContext
 */
public class AstCaptureErrorListener extends RouteParserBaseListener {
  // Implementation stubs
}

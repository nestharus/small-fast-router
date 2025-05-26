package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.nestharus.parser.RouteParser;

public class OperatorExtractor {

  public static Optional<Token> extractNegationToken(final TerminalNode bangNode) {
    return Optional.ofNullable(bangNode).map(TerminalNode::getSymbol);
  }

  public static Optional<Token> extractOptionalToken(final TerminalNode qmarkNode) {
    return Optional.ofNullable(qmarkNode).map(TerminalNode::getSymbol);
  }

  public static Optional<Token> extractCaptureToken(
      final RouteParser.CaptureContext captureContext) {
    return Optional.ofNullable(captureContext)
        .map(RouteParser.CaptureContext::IDENTIFIER)
        .map(TerminalNode::getSymbol);
  }

  public static Optional<RouteParser.QuantifierContext> extractQuantifier(
      final RouteParser.QuantifierContext quantifierContext) {
    return Optional.ofNullable(quantifierContext);
  }
}

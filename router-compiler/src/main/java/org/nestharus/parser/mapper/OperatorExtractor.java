package org.nestharus.parser.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;

public class OperatorExtractor {

  public static Optional<Token> extractNegationToken(
      final List<RouteParser.PrefixContext> prefixList) throws TokenMapperException {
    if (prefixList.isEmpty()) {
      return Optional.empty();
    }

    if (prefixList.size() > 1) {
      final var tokens =
          prefixList.stream()
              .map(RouteParser.PrefixContext::BANG)
              .filter(Objects::nonNull)
              .map(TerminalNode::getSymbol)
              .toList();
      throw new TokenMapperException(
          tokens, "Multiple negation operators not allowed on same element");
    }

    return Optional.of(prefixList.getFirst().BANG().getSymbol());
  }

  public static Optional<Token> extractOptionalToken(
      final List<RouteParser.PostfixContext> postfixList) throws TokenMapperException {
    final var optionalTokens =
        postfixList.stream()
            .map(RouteParser.PostfixContext::QMARK)
            .filter(Objects::nonNull)
            .map(TerminalNode::getSymbol)
            .toList();

    if (optionalTokens.size() > 1) {
      throw new TokenMapperException(
          optionalTokens, "Multiple optional operators not allowed on same element");
    }

    if (optionalTokens.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(optionalTokens.getFirst());
  }

  public static Optional<Token> extractCaptureToken(
      final List<RouteParser.PostfixContext> postfixList) throws TokenMapperException {
    final var captures = postfixList.stream().filter(postfix -> postfix.capture() != null).toList();

    if (captures.size() > 1) {
      final var tokens =
          captures.stream()
              .map(RouteParser.PostfixContext::capture)
              .map(RouteParser.CaptureContext::LT)
              .map(TerminalNode::getSymbol)
              .toList();
      throw new TokenMapperException(tokens, "Multiple captures not allowed on same element");
    }

    if (captures.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(captures.getFirst().capture().IDENTIFIER().getSymbol());
  }

  public static Optional<RouteParser.QuantifierContext> extractQuantifier(
      final List<RouteParser.PostfixContext> postfixList) throws TokenMapperException {
    final var quantifiers =
        postfixList.stream()
            .map(RouteParser.PostfixContext::quantifier)
            .filter(Objects::nonNull)
            .toList();

    if (quantifiers.size() > 1) {
      final var tokens =
          quantifiers.stream()
              .map(RouteParser.QuantifierContext::LBRACK)
              .map(TerminalNode::getSymbol)
              .toList();
      throw new TokenMapperException(tokens, "Multiple quantifiers not allowed on same element");
    }

    if (quantifiers.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(quantifiers.getFirst());
  }
}

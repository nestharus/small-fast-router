package org.nestharus.parser.mapper;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.common.collect.Range;
import org.apache.commons.lang3.function.Failable;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.value.RangeNode;

public class RangeNodeMapper {
  public static List<RangeNode> fromRangeContext(
      final RouteParser.QuantifierContext ctx, final boolean isOptional, final boolean isStatic)
      throws TokenMapperException {
    final List<RangeNode> ranges;

    if (ctx == null) {
      ranges = List.of(fromRangeContextWithNoInterval(isStatic));
    } else if (ctx.quantifierElement() != null) {
      ranges = List.of(fromQuantifierElement(ctx.quantifierElement()));
    } else if (ctx.quantifierBranchExpression() != null) {
      ranges = fromQuantifierBranchExpression(ctx.quantifierBranchExpression());
    } else {
      throw new RuntimeException("This should not happen");
    }

    return rangesToOptional(ranges, isOptional);
  }

  public static RangeNode fromQuantifierElement(final RouteParser.QuantifierElementContext ctx)
      throws TokenMapperException {
    if (ctx.quantifierBoundedElement() != null) {
      return fromBoundedInterval(ctx.quantifierBoundedElement());
    }

    if (ctx.quantifierSingleElement() != null) {
      return fromSingletonInterval(ctx.quantifierSingleElement());
    }

    if (ctx.quantifierBoundedLowerElement() != null) {
      return fromBoundedLowerInterval(ctx.quantifierBoundedLowerElement());
    }

    return fromBoundedUpperInterval(ctx.quantifierBoundedUpperElement());
  }

  public static List<RangeNode> fromQuantifierBranchExpression(
      final RouteParser.QuantifierBranchExpressionContext ctx) throws TokenMapperException {
    try {
      return ctx.quantifierElement().stream()
          .map(rule -> Failable.get(() -> RangeNodeMapper.fromQuantifierElement(rule)))
          .toList();
    } catch (final RuntimeException exception) {
      throw (TokenMapperException) exception.getCause();
    }
  }

  public static RangeNode fromRangeContextWithNoInterval(final boolean isStatic) {
    if (isStatic) {
      return new RangeNode(Range.singleton(1), Optional.empty());
    }

    return new RangeNode(Range.atLeast(1), Optional.empty());
  }

  public static RangeNode fromBoundedInterval(final RouteParser.QuantifierBoundedElementContext ctx)
      throws TokenMapperException {
    final var leftToken = ctx.quantifierLeftElement().INTEGER().getSymbol();
    final var rightToken = ctx.quantifierRightElement().INTEGER().getSymbol();

    final Range<@NonNull Integer> range;
    try {
      range =
          Range.closed(
              Integer.parseInt(leftToken.getText()), Integer.parseInt(rightToken.getText()));
    } catch (final RuntimeException runtimeException) {
      throw new TokenMapperException(List.of(leftToken, rightToken), runtimeException.getMessage());
    }

    return new RangeNode(range, Optional.of(SourceNodeMapper.fromToken(leftToken)));
  }

  public static RangeNode fromSingletonInterval(
      final RouteParser.QuantifierSingleElementContext ctx) throws TokenMapperException {
    final var token = ctx.INTEGER().getSymbol();

    final Range<@NonNull Integer> range;
    try {
      range = Range.singleton(Integer.parseInt(token.getText()));
    } catch (final RuntimeException runtimeException) {
      throw new TokenMapperException(List.of(token), runtimeException.getMessage());
    }

    return new RangeNode(range, Optional.of(SourceNodeMapper.fromToken(token)));
  }

  public static RangeNode fromBoundedUpperInterval(
      final RouteParser.QuantifierBoundedUpperElementContext ctx) throws TokenMapperException {
    final var token = ctx.INTEGER().getSymbol();

    final Range<@NonNull Integer> range;
    try {
      range = Range.atLeast(Integer.parseInt(token.getText()));
    } catch (final RuntimeException runtimeException) {
      throw new TokenMapperException(List.of(token), runtimeException.getMessage());
    }

    return new RangeNode(range, Optional.of(SourceNodeMapper.fromToken(token)));
  }

  public static RangeNode fromBoundedLowerInterval(
      final RouteParser.QuantifierBoundedLowerElementContext ctx) throws TokenMapperException {
    final var token = ctx.INTEGER().getSymbol();

    final Range<@NonNull Integer> range;
    try {
      range = Range.atMost(Integer.parseInt(token.getText()));
    } catch (final RuntimeException runtimeException) {
      throw new TokenMapperException(List.of(token), runtimeException.getMessage());
    }

    return new RangeNode(range, Optional.of(SourceNodeMapper.fromToken(token)));
  }

  public static List<RangeNode> rangesToOptional(
      final List<RangeNode> ranges, final boolean isOptional) {
    if (isOptional) {
      final var optionalRange = new RangeNode(Range.singleton(0), Optional.empty());

      return Stream.of(Stream.of(optionalRange), ranges.stream())
          .flatMap(Function.identity())
          .toList();
    }

    return ranges;
  }
}

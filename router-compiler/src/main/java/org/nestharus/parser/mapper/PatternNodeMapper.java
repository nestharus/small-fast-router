package org.nestharus.parser.mapper;

import java.util.List;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.ParserNode;
import org.nestharus.parser.value.PatternNode;
import org.nestharus.parser.value.WildcardInterval;

public class PatternNodeMapper {
  public static PatternNode fromStarPatternContext(
      final RouteParser.StarPatternContext ctx, final List<ParserNode> children) {
    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var intervalType =
        ctx.star().STAR() != null
            ? WildcardIntervalType.SEGMENT_BOUND
            : WildcardIntervalType.SEGMENT_UNBOUND;

    final var interval =
        WildcardInterval.builder()
            .type(intervalType)
            .interval(RangeMapper.fromRangeContext(ctx.quantifier(), isOptional))
            .build();

    return PatternNode.builder()
        .negated(isNegated)
        .captureName(captureName)
        .interval(interval)
        .children(children)
        .build();
  }

  public static PatternNode fromGroupExpressionContext(
      final RouteParser.GroupExpressionContext ctx, final List<ParserNode> children) {
    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;

    final var interval =
        WildcardInterval.builder()
            .interval(RangeMapper.fromRangeContext(null, isOptional))
            .type(WildcardIntervalType.SEGMENT_BOUND)
            .build();

    return PatternNode.builder()
        .negated(isNegated)
        .captureName(captureName)
        .interval(interval)
        .children(children)
        .build();
  }
}

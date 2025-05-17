package org.nestharus.parser.mapper;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.WildcardInterval;
import org.nestharus.parser.value.WildcardNode;

public class WildcardNodeMapper {
  public static WildcardNode fromStarExpression(final RouteParser.StarExpressionContext ctx) {
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var intervalType =
        ctx.star().STAR() != null
            ? WildcardIntervalType.SEGMENT_BOUND
            : WildcardIntervalType.SEGMENT_UNBOUND;

    final var interval =
        WildcardInterval.builder()
            .interval(RangeMapper.fromRangeContext(ctx.quantifier(), isOptional))
            .type(intervalType)
            .build();

    return WildcardNode.builder().captureName(captureName).interval(interval).build();
  }
}

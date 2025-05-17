package org.nestharus.parser.mapper;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardInterval;

public class TextNodeMapper {
  public static TextNode fromTextExpressionContext(final RouteParser.TextExpressionContext ctx) {
    final var isNegated = ctx.BANG() != null;
    final var isOptional = ctx.QMARK() != null;
    final var captureName = ctx.capture() != null ? ctx.capture().IDENTIFIER().getText() : null;
    final var interval =
        WildcardInterval.builder()
            .type(WildcardIntervalType.SEGMENT_BOUND)
            .interval(RangeMapper.fromRangeContext(null, isOptional))
            .build();
    final var text = ctx.STATIC_TEXT().getText();

    return TextNode.builder()
        .negated(isNegated)
        .captureName(captureName)
        .interval(interval)
        .text(text)
        .build();
  }
}

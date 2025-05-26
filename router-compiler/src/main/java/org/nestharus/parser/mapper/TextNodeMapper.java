package org.nestharus.parser.mapper;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardInterval;

public class TextNodeMapper {
  public static TextNode fromTextExpressionContext(final RouteParser.TextExpressionContext context)
      throws TokenMapperException {
    final var isNegatedToken = OperatorExtractor.extractNegationToken(context.prefix());
    final var isOptionalToken = OperatorExtractor.extractOptionalToken(context.postfix());
    final var captureNameToken = OperatorExtractor.extractCaptureToken(context.postfix());
    final var textToken = context.STATIC_TEXT().getSymbol();

    final var isOptional = isOptionalToken.isPresent();
    final var intervalRange = RangeNodeMapper.fromRangeContext(null, isOptional, true);

    return TextNode.builder()
        .negated(BooleanNodeMapper.fromToken(isNegatedToken))
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(
            WildcardInterval.builder()
                .type(WildcardIntervalType.SEGMENT_BOUND)
                .interval(intervalRange)
                .build())
        .text(StringNodeMapper.fromToken(textToken))
        .build();
  }
}

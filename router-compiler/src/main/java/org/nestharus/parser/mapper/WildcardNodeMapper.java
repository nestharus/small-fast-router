package org.nestharus.parser.mapper;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.value.WildcardInterval;
import org.nestharus.parser.value.WildcardNode;

public class WildcardNodeMapper {
  public static WildcardNode fromStarExpression(final RouteParser.StarExpressionContext context)
      throws TokenMapperException {
    final var isOptionalToken = OperatorExtractor.extractOptionalToken(context.postfix());
    final var captureNameToken = OperatorExtractor.extractCaptureToken(context.postfix());
    final var quantifierContext = OperatorExtractor.extractQuantifier(context.postfix());

    final var isOptional = isOptionalToken.isPresent();
    final var starNodeType = StarNodeMapper.typeFromParseRule(context.star());
    final var intervalType = WildcardIntervalTypeMapper.fromStarNodeType(starNodeType);
    final var intervalRange =
        RangeNodeMapper.fromRangeContext(quantifierContext.orElse(null), isOptional, false);

    return WildcardNode.builder()
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(WildcardInterval.builder().interval(intervalRange).type(intervalType).build())
        .build();
  }
}

package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.value.WildcardInterval;
import org.nestharus.parser.value.WildcardNode;

public class WildcardNodeMapper {
  public static WildcardNode fromStarExpression(final RouteParser.StarExpressionContext context)
      throws TokenMapperException {
    final var isOptionalToken = OperatorExtractor.extractOptionalToken(context.QMARK());
    final var captureNameToken = OperatorExtractor.extractCaptureToken(context.capture());
    final var quantifierContext = OperatorExtractor.extractQuantifier(context.quantifier());

    final var isOptional = isOptionalToken.isPresent();
    final var starNodeType = StarNodeMapper.typeFromParseRule(context.star());
    final var intervalType = WildcardIntervalTypeMapper.fromStarNodeType(starNodeType);
    final var intervalRange =
        RangeNodeMapper.fromRangeContext(quantifierContext.orElse(null), isOptional, false);

    final Token starToken =
        switch (starNodeType) {
          case STAR -> context.star().STAR().getSymbol();
          case DOUBLESTAR -> context.star().DOUBLESTAR().getSymbol();
        };

    return WildcardNode.builder()
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(WildcardInterval.builder().interval(intervalRange).type(intervalType).build())
        .sourceNode(Optional.of(SourceNodeMapper.fromToken(starToken)))
        .build();
  }
}

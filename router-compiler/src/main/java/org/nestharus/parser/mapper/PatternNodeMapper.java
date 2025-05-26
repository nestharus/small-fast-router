package org.nestharus.parser.mapper;

import java.util.List;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.*;

public class PatternNodeMapper {
  public static PatternNode fromStarPatternContext(
      final RouteParser.StarPatternContext context, final List<ParserNode> children)
      throws TokenMapperException {
    final var isNegatedToken = OperatorExtractor.extractNegationToken(context.prefix());
    final var isOptionalToken = OperatorExtractor.extractOptionalToken(context.postfix());
    final var captureNameToken = OperatorExtractor.extractCaptureToken(context.postfix());
    final var quantifierContext = OperatorExtractor.extractQuantifier(context.postfix());

    final var starNodeType = StarNodeMapper.typeFromParseRule(context.star());
    final var intervalType = WildcardIntervalTypeMapper.fromStarNodeType(starNodeType);
    final var isOptional = isOptionalToken.isPresent();
    final var intervalRange =
        RangeNodeMapper.fromRangeContext(quantifierContext.orElse(null), isOptional, false);

    return PatternNode.builder()
        .negated(BooleanNodeMapper.fromToken(isNegatedToken))
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(WildcardInterval.builder().type(intervalType).interval(intervalRange).build())
        .children(children)
        .build();
  }

  public static PatternNode fromGroupExpressionContext(
      final RouteParser.GroupExpressionContext context, final List<ParserNode> children)
      throws TokenMapperException {
    final var isNegatedToken = OperatorExtractor.extractNegationToken(context.prefix());
    final var isOptionalToken = OperatorExtractor.extractOptionalToken(context.postfix());
    final var captureNameToken = OperatorExtractor.extractCaptureToken(context.postfix());

    final var isOptional = isOptionalToken.isPresent();
    final var intervalRange = RangeNodeMapper.fromRangeContext(null, isOptional, false);

    return PatternNode.builder()
        .negated(BooleanNodeMapper.fromToken(isNegatedToken))
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(
            WildcardInterval.builder()
                .interval(intervalRange)
                .type(WildcardIntervalType.SEGMENT_BOUND)
                .build())
        .children(children)
        .build();
  }
}

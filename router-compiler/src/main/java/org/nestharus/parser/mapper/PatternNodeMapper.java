package org.nestharus.parser.mapper;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.*;

public class PatternNodeMapper {
  public static PatternNode fromStarPatternContext(
      final RouteParser.StarPatternContext ctx, final List<ParserNode> children)
      throws TokenMapperException {
    final var isNegatedToken = Optional.ofNullable(ctx.BANG()).map(TerminalNode::getSymbol);
    final var isOptionalToken = Optional.ofNullable(ctx.QMARK()).map(TerminalNode::getSymbol);
    final var captureNameToken =
        Optional.ofNullable(ctx.capture()).map(token -> token.IDENTIFIER().getSymbol());

    final var starNodeType = StarNodeMapper.typeFromParseRule(ctx.star());
    final var intervalType = WildcardIntervalTypeMapper.fromStarNodeType(starNodeType);
    final var isOptional = isOptionalToken.isPresent();
    final var intervalRange = RangeNodeMapper.fromRangeContext(ctx.quantifier(), isOptional, false);

    return PatternNode.builder()
        .negated(BooleanNodeMapper.fromToken(isNegatedToken))
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(WildcardInterval.builder().type(intervalType).interval(intervalRange).build())
        .children(children)
        .build();
  }

  public static PatternNode fromGroupExpressionContext(
      final RouteParser.GroupExpressionContext ctx, final List<ParserNode> children)
      throws TokenMapperException {
    final var isNegatedToken = Optional.ofNullable(ctx.BANG()).map(TerminalNode::getSymbol);
    final var isOptionalToken = Optional.ofNullable(ctx.QMARK()).map(TerminalNode::getSymbol);
    final var captureNameToken =
        Optional.ofNullable(ctx.capture()).map(token -> token.IDENTIFIER().getSymbol());

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

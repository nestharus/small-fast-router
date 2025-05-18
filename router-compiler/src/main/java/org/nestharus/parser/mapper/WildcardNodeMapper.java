package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.value.WildcardInterval;
import org.nestharus.parser.value.WildcardNode;

public class WildcardNodeMapper {
  public static WildcardNode fromStarExpression(final RouteParser.StarExpressionContext ctx)
      throws TokenMapperException {
    final var isOptionalToken = Optional.ofNullable(ctx.QMARK()).map(TerminalNode::getSymbol);
    final var captureNameToken =
        Optional.ofNullable(ctx.capture()).map(token -> token.IDENTIFIER().getSymbol());

    final var isOptional = isOptionalToken.isPresent();
    final var starNodeType = StarNodeMapper.typeFromParseRule(ctx.star());
    final var intervalType = WildcardIntervalTypeMapper.fromStarNodeType(starNodeType);
    final var intervalRange = RangeMapper.fromRangeContext(ctx.quantifier(), isOptional, false);

    return WildcardNode.builder()
        .captureName(StringNodeMapper.fromToken(captureNameToken))
        .interval(WildcardInterval.builder().interval(intervalRange).type(intervalType).build())
        .build();
  }
}

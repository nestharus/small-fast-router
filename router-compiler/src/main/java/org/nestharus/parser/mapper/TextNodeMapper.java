package org.nestharus.parser.mapper;

import java.util.Optional;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.type.WildcardIntervalType;
import org.nestharus.parser.value.TextNode;
import org.nestharus.parser.value.WildcardInterval;

public class TextNodeMapper {
  public static TextNode fromTextExpressionContext(final RouteParser.TextExpressionContext ctx)
      throws TokenMapperException {
    final var isNegatedToken = Optional.ofNullable(ctx.BANG()).map(TerminalNode::getSymbol);
    final var isOptionalToken = Optional.ofNullable(ctx.QMARK()).map(TerminalNode::getSymbol);
    final var captureNameToken =
        Optional.ofNullable(ctx.capture()).map(token -> token.IDENTIFIER().getSymbol());
    final var textToken = ctx.STATIC_TEXT().getSymbol();

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

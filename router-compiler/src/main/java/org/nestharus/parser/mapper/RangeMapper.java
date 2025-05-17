package org.nestharus.parser.mapper;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.RouteParser;

public class RangeMapper {
  public static Range<@NonNull Integer> fromRangeContext(
      final RouteParser.QuantifierContext ctx, final boolean isOptional) {
    if (ctx == null) {
      if (isOptional) {
        return Range.atLeast(0);
      }

      return Range.atLeast(1);
    }

    final var quantifier = ctx.quantifierElement();

    if (quantifier.quantifierBoundedElement() != null) {
      final var expression = quantifier.quantifierBoundedElement();
      final var leftText = expression.quantifierLeftElement().INTEGER().getText();
      final var rightText = expression.quantifierRightElement().INTEGER().getText();
      return Range.closed(Integer.parseInt(leftText), Integer.parseInt(rightText));
    }

    if (quantifier.quantifierSingleElement() != null) {
      final var expression = quantifier.quantifierSingleElement();
      final var text = expression.INTEGER().getText();
      return Range.singleton(Integer.parseInt(text));
    }

    if (quantifier.quantifierBoundedLowerElement() != null) {
      final var expression = quantifier.quantifierBoundedLowerElement();
      final var text = expression.INTEGER().getText();
      return Range.atLeast(Integer.parseInt(text));
    }

    // if (quantifier.quantifierBoundedUpperElement() != null) {
    final var expression = quantifier.quantifierBoundedUpperElement();
    final var text = expression.INTEGER().getText();
    return Range.atMost(Integer.parseInt(text));
  }
}

package org.nestharus.parser.mapper;

import org.nestharus.parser.RouteParser;
import org.nestharus.parser.type.StarNodeType;

public class StarNodeMapper {
  public static StarNodeType typeFromParseRule(final RouteParser.StarContext context) {
    if (context.STAR() != null) {
      return StarNodeType.STAR;
    }

    if (context.DOUBLESTAR() != null) {
      return StarNodeType.DOUBLESTAR;
    }

    throw new RuntimeException("This will never happen");
  }
}

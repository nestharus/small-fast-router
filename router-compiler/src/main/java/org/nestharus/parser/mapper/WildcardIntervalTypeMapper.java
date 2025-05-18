package org.nestharus.parser.mapper;

import org.nestharus.parser.type.StarNodeType;
import org.nestharus.parser.type.WildcardIntervalType;

public class WildcardIntervalTypeMapper {
  public static WildcardIntervalType fromStarNodeType(final StarNodeType starNodeType) {
    return switch (starNodeType) {
      case STAR -> WildcardIntervalType.SEGMENT_BOUND;
      case DOUBLESTAR -> WildcardIntervalType.SEGMENT_UNBOUND;
    };
  }
}

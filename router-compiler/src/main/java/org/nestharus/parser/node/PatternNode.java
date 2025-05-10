package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class PatternNode implements ParserNode, ScopeNode {
  @NonNull private ScopeNode containingScope;
  @Builder.Default private boolean negated = false;
  @Builder.Default private String captureName = null;

  @Builder.Default
  private WildcardInterval interval =
      WildcardInterval.builder()
          .interval(Range.closed(1, 1))
          .type(WildcardIntervalType.SEGMENT_BOUND)
          .build();

  private final List<ParserNode> children = new ArrayList<>();

  public boolean isCaptured() {
    return captureName != null && !captureName.isEmpty();
  }

  @Override
  public ParserNodeType getType() {
    return ParserNodeType.PATTERN;
  }
}

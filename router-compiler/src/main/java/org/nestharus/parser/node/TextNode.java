package org.nestharus.parser.node;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class TextNode implements ParserNode {
  @NonNull private ScopeNode containingScope;

  @Builder.Default private boolean negated = false;
  @Builder.Default private String captureName = null;

  @Builder.Default
  private WildcardInterval interval =
      WildcardInterval.builder()
          .interval(Range.closed(1, 1))
          .type(WildcardIntervalType.SEGMENT_BOUND)
          .build();

  @NonNull private String text;

  public boolean isCaptured() {
    return captureName != null && !captureName.isEmpty();
  }

  @Override
  public ParserNodeType getType() {
    return ParserNodeType.TEXT;
  }
}

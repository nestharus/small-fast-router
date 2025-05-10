package org.nestharus.parser.node;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class WildcardNode implements ParserNode {
  @NonNull private ScopeNode containingScope;

  @Builder.Default private String captureName = null;
  @NonNull private WildcardInterval interval;

  public boolean isCaptured() {
    return captureName != null && !captureName.isEmpty();
  }

  @Override
  public ParserNodeType getType() {
    return ParserNodeType.WILDCARD;
  }
}

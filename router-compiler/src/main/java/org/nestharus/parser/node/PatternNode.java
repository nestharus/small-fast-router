package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record PatternNode(
    @NonNull ScopeNode containingScope,
    boolean negated,
    @Nullable String captureName,
    @Nullable WildcardInterval interval,
    List<ParserNode> children)
    implements ParserNode, ScopeNode {
  public PatternNode {
    Objects.requireNonNull(containingScope, "property :containingScope is required");
    Objects.requireNonNull(children, "property :children is required");
  }

  public boolean isCaptured() {
    return captureName != null && !captureName.isEmpty();
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.PATTERN;
  }

  @Override
  public @NonNull String toString() {
    return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ScopeNode containingScope;

    private boolean negated;

    private String captureName;

    private WildcardInterval interval;

    private Builder() {
      negated = false;
      interval =
          WildcardInterval.builder()
              .interval(Range.closed(1, 1))
              .type(WildcardIntervalType.SEGMENT_BOUND)
              .build();
    }

    public Builder containingScope(@NonNull ScopeNode containingScope) {
      this.containingScope = Objects.requireNonNull(containingScope, "Null containingScope");
      return this;
    }

    public Builder negated(boolean negated) {
      this.negated = negated;
      return this;
    }

    public Builder captureName(@Nullable String captureName) {
      this.captureName = captureName;
      return this;
    }

    public Builder interval(@Nullable WildcardInterval interval) {
      this.interval = interval;
      return this;
    }

    public PatternNode build() {
      if (this.containingScope == null) {
        StringBuilder missing = new StringBuilder();
        missing.append(" containingScope");
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new PatternNode(
          this.containingScope, this.negated, this.captureName, this.interval, new ArrayList<>());
    }
  }
}

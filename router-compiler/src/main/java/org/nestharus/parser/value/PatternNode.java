package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.nestharus.parser.type.ParserNodeType;
import org.nestharus.parser.type.WildcardIntervalType;

public record PatternNode(
    boolean negated,
    @Nullable String captureName,
    @Nullable WildcardInterval interval,
    List<ParserNode> children)
    implements ParserNode, GroupNode<ParserNode>, CapturableNode {
  public PatternNode {
    Objects.requireNonNull(children, "property :children is required");
  }

  @Override
  public boolean captured() {
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
    private boolean negated;

    private String captureName;

    private WildcardInterval interval;

    private List<ParserNode> children;

    private Builder() {
      negated = false;
      interval =
          WildcardInterval.builder()
              .interval(Range.closed(1, 1))
              .type(WildcardIntervalType.SEGMENT_BOUND)
              .build();
    }

    public Builder negated(final boolean negated) {
      this.negated = negated;
      return this;
    }

    public Builder captureName(@Nullable final String captureName) {
      this.captureName = captureName;
      return this;
    }

    public Builder interval(@Nullable final WildcardInterval interval) {
      this.interval = interval;
      return this;
    }

    public Builder children(@NonNull final List<ParserNode> children) {
      this.children = children;
      return this;
    }

    public PatternNode build() {
      return new PatternNode(this.negated, this.captureName, this.interval, this.children);
    }
  }
}

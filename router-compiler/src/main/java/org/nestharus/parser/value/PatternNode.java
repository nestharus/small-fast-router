package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.nestharus.parser.type.ParserNodeType;
import org.nestharus.parser.type.WildcardIntervalType;

public record PatternNode(
    @NonNull BooleanNode negated,
    @NonNull Optional<StringNode> captureName,
    @NonNull WildcardInterval interval,
    @NonNull List<ParserNode> children)
    implements ParserNode, GroupNode<ParserNode>, CapturableNode {
  public PatternNode {
    Objects.requireNonNull(negated, "property :negated is required");
    Objects.requireNonNull(captureName, "property :captureName is required");
    Objects.requireNonNull(interval, "property :interval is required");
    Objects.requireNonNull(children, "property :children is required");
  }

  @Override
  public boolean captured() {
    return captureName.isPresent();
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
    private BooleanNode negated;

    private Optional<StringNode> captureName;

    private WildcardInterval interval;

    private List<ParserNode> children;

    private Builder() {
      negated = new BooleanNode(false, Optional.empty());
      interval =
          WildcardInterval.builder()
              .interval(
                  List.of(
                      RangeNode.builder()
                          .value(Range.atLeast(1))
                          .sourceNode(Optional.empty())
                          .build()))
              .type(WildcardIntervalType.SEGMENT_BOUND)
              .build();
      captureName = Optional.empty();
    }

    public Builder negated(@NonNull final BooleanNode negated) {
      this.negated = negated;
      return this;
    }

    public Builder captureName(@NonNull final Optional<StringNode> captureName) {
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

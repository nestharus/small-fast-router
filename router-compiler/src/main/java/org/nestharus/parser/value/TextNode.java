package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.ParserNodeType;
import org.nestharus.parser.type.WildcardIntervalType;

public record TextNode(
    @NonNull BooleanNode negated,
    @NonNull Optional<StringNode> captureName,
    @NonNull WildcardInterval interval,
    @NonNull StringNode text)
    implements ParserNode, CapturableNode {
  public TextNode {
    Objects.requireNonNull(negated, "property :negated is required");
    Objects.requireNonNull(captureName, "property :captureName is required");
    Objects.requireNonNull(interval, "property :interval is required");
    Objects.requireNonNull(text, "property :text is required");
  }

  @Override
  public boolean captured() {
    return captureName.isPresent();
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.TEXT;
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

    private StringNode text;

    private Builder() {
      negated = new BooleanNode(false, Optional.empty());
      interval =
          WildcardInterval.builder()
              .interval(
                  List.of(
                      RangeNode.builder()
                          .value(Range.singleton(1))
                          .sourceNode(Optional.empty())
                          .build()))
              .type(WildcardIntervalType.SEGMENT_BOUND)
              .build();
    }

    public Builder negated(BooleanNode negated) {
      this.negated = negated;
      return this;
    }

    public Builder captureName(@NonNull Optional<StringNode> captureName) {
      this.captureName = captureName;
      return this;
    }

    public Builder interval(@NonNull WildcardInterval interval) {
      this.interval = Objects.requireNonNull(interval, "Null interval");
      return this;
    }

    public Builder text(@NonNull StringNode text) {
      this.text = Objects.requireNonNull(text, "Null value");
      return this;
    }

    public TextNode build() {
      if (this.text == null) {
        throw new IllegalStateException("Missing required properties:" + " value");
      }
      return new TextNode(this.negated, this.captureName, this.interval, this.text);
    }
  }
}

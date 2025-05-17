package org.nestharus.parser.value;

import java.util.Objects;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.nestharus.parser.type.ParserNodeType;
import org.nestharus.parser.type.WildcardIntervalType;

public record TextNode(
    boolean negated, @Nullable String captureName, WildcardInterval interval, @NonNull String text)
    implements ParserNode, CapturableNode {
  public TextNode {
    Objects.requireNonNull(interval, "property :interval is required");
    Objects.requireNonNull(text, "property :text is required");
  }

  @Override
  public boolean captured() {
    return captureName != null && !captureName.isEmpty();
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
    private boolean negated;

    private String captureName;

    private WildcardInterval interval;

    private String text;

    private Builder() {
      negated = false;
      interval =
          WildcardInterval.builder()
              .interval(Range.closed(1, 1))
              .type(WildcardIntervalType.SEGMENT_BOUND)
              .build();
    }

    public Builder negated(boolean negated) {
      this.negated = negated;
      return this;
    }

    public Builder captureName(@Nullable String captureName) {
      this.captureName = captureName;
      return this;
    }

    public Builder interval(@NonNull WildcardInterval interval) {
      this.interval = Objects.requireNonNull(interval, "Null interval");
      return this;
    }

    public Builder text(@NonNull String text) {
      this.text = Objects.requireNonNull(text, "Null text");
      return this;
    }

    public TextNode build() {
      if (this.text == null) {
        throw new IllegalStateException("Missing required properties:" + " text");
      }
      return new TextNode(this.negated, this.captureName, this.interval, this.text);
    }
  }
}

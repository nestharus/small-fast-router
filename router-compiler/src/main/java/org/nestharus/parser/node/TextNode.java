package org.nestharus.parser.node;

import java.util.Objects;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record TextNode(
    @NonNull ScopeNode containingScope,
    boolean negated,
    @Nullable String captureName,
    WildcardInterval interval,
    @NonNull String text)
    implements ParserNode {
  public TextNode {
    Objects.requireNonNull(containingScope, "property :containingScope is required");
    Objects.requireNonNull(interval, "property :interval is required");
    Objects.requireNonNull(text, "property :text is required");
  }

  public boolean isCaptured() {
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
    private ScopeNode containingScope;

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

    public Builder interval(@NonNull WildcardInterval interval) {
      this.interval = Objects.requireNonNull(interval, "Null interval");
      return this;
    }

    public Builder text(@NonNull String text) {
      this.text = Objects.requireNonNull(text, "Null text");
      return this;
    }

    public TextNode build() {
      if (this.containingScope == null || this.text == null) {
        StringBuilder missing = new StringBuilder();
        if (this.containingScope == null) {
          missing.append(" containingScope");
        }
        if (this.text == null) {
          missing.append(" text");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new TextNode(
          this.containingScope, this.negated, this.captureName, this.interval, this.text);
    }
  }
}

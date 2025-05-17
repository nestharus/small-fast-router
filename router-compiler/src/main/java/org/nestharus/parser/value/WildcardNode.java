package org.nestharus.parser.value;

import java.util.Objects;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.nestharus.parser.type.ParserNodeType;

public record WildcardNode(@Nullable String captureName, @NonNull WildcardInterval interval)
    implements ParserNode, CapturableNode {
  public WildcardNode {
    Objects.requireNonNull(interval, "property :interval is required");
  }

  public boolean captured() {
    return captureName != null && !captureName.isEmpty();
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.WILDCARD;
  }

  @Override
  public @NonNull String toString() {
    return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String captureName;

    private WildcardInterval interval;

    private Builder() {}

    public Builder captureName(@Nullable String captureName) {
      this.captureName = captureName;
      return this;
    }

    public Builder interval(@NonNull WildcardInterval interval) {
      this.interval = Objects.requireNonNull(interval, "Null interval");
      return this;
    }

    public WildcardNode build() {
      if (this.interval == null) {
        throw new IllegalStateException("Missing required properties:" + " interval");
      }
      return new WildcardNode(this.captureName, this.interval);
    }
  }
}

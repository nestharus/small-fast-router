package org.nestharus.parser.node;

import java.util.Objects;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record WildcardNode(
    @NonNull ScopeNode containingScope,
    @Nullable String captureName,
    @NonNull WildcardInterval interval)
    implements ParserNode {
  public WildcardNode {
    Objects.requireNonNull(containingScope, "property :containingScope is required");
    Objects.requireNonNull(interval, "property :interval is required");
  }

  public boolean isCaptured() {
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
    private ScopeNode containingScope;

    private String captureName;

    private WildcardInterval interval;

    private Builder() {}

    public Builder containingScope(@NonNull ScopeNode containingScope) {
      this.containingScope = Objects.requireNonNull(containingScope, "Null containingScope");
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

    public WildcardNode build() {
      if (this.containingScope == null || this.interval == null) {
        StringBuilder missing = new StringBuilder();
        if (this.containingScope == null) {
          missing.append(" containingScope");
        }
        if (this.interval == null) {
          missing.append(" interval");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new WildcardNode(this.containingScope, this.captureName, this.interval);
    }
  }
}

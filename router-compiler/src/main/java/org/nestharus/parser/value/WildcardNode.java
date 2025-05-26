package org.nestharus.parser.value;

import java.util.Objects;
import java.util.Optional;

import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.ParserNodeType;

public record WildcardNode(
    @NonNull Optional<StringNode> captureName, @NonNull WildcardInterval interval)
    implements ParserNode, CapturableNode {
  public WildcardNode {
    Objects.requireNonNull(interval, "property :interval is required");
  }

  public boolean captured() {
    return captureName.isPresent();
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.WILDCARD;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Optional<StringNode> captureName;

    private WildcardInterval interval;

    private Builder() {}

    public Builder captureName(@NonNull Optional<StringNode> captureName) {
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

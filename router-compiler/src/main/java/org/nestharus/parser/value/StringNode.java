package org.nestharus.parser.value;

import java.util.Objects;
import java.util.Optional;

public record StringNode(String value, Optional<SourceNode> sourceNode) implements ValueNode {
  public StringNode {
    Objects.requireNonNull(value, "property :value is required");
    Objects.requireNonNull(sourceNode, "property :sourceNode is required");
  }

  @Override
  public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String value;

    private Optional<SourceNode> sourceNode;

    private Builder() {}

    public Builder value(String value) {
      this.value = Objects.requireNonNull(value, "Null value");
      return this;
    }

    public Builder sourceNode(Optional<SourceNode> sourceNode) {
      this.sourceNode = Objects.requireNonNull(sourceNode, "Null sourceNode");
      return this;
    }

    public StringNode build() {
      if (this.value == null || this.sourceNode == null) {
        StringBuilder missing = new StringBuilder();
        if (this.value == null) {
          missing.append(" value");
        }
        if (this.sourceNode == null) {
          missing.append(" sourceNode");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new StringNode(this.value, this.sourceNode);
    }
  }
}

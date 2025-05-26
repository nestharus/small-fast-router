package org.nestharus.parser.value;

import java.util.Objects;
import java.util.Optional;

public record BooleanNode(boolean value, Optional<SourceNode> sourceNode) implements ValueNode {
  public BooleanNode {
    Objects.requireNonNull(sourceNode, "property :sourceNode is required");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private boolean value;

    private Optional<SourceNode> sourceNode;

    private Builder() {}

    public Builder value(boolean value) {
      this.value = value;
      return this;
    }

    public Builder sourceNode(Optional<SourceNode> sourceNode) {
      this.sourceNode = Objects.requireNonNull(sourceNode, "Null sourceNode");
      return this;
    }

    public BooleanNode build() {
      if (this.sourceNode == null) {
        throw new IllegalStateException("Missing required property: sourceNode");
      }
      return new BooleanNode(this.value, this.sourceNode);
    }
  }
}

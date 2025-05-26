package org.nestharus.parser.value;

import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;

public record RangeNode(Range<@NonNull Integer> value, Optional<SourceNode> sourceNode)
    implements ValueNode {
  public RangeNode {
    Objects.requireNonNull(value, "property :value is required");
    Objects.requireNonNull(sourceNode, "property :sourceNode is required");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Range<Integer> value;

    private Optional<SourceNode> sourceNode;

    private Builder() {}

    public Builder value(Range<Integer> value) {
      this.value = Objects.requireNonNull(value, "Null value");
      return this;
    }

    public Builder sourceNode(Optional<SourceNode> sourceNode) {
      this.sourceNode = Objects.requireNonNull(sourceNode, "Null sourceNode");
      return this;
    }

    public RangeNode build() {
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
      return new RangeNode(this.value, this.sourceNode);
    }
  }
}

package org.nestharus.parser.value;

import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.WildcardIntervalType;

public record WildcardInterval(List<RangeNode> intervals, @NonNull WildcardIntervalType type) {

  public WildcardInterval {
    Objects.requireNonNull(intervals, "property :intervals is required");
    Objects.requireNonNull(type, "property :type is required");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private List<RangeNode> intervals;

    private WildcardIntervalType type;

    private Builder() {}

    public Builder interval(List<RangeNode> intervals) {
      this.intervals = Objects.requireNonNull(intervals, "Null intervals");
      return this;
    }

    public Builder type(@NonNull WildcardIntervalType type) {
      this.type = Objects.requireNonNull(type, "Null type");
      return this;
    }

    public WildcardInterval build() {
      if (this.intervals == null || this.type == null) {
        StringBuilder missing = new StringBuilder();
        if (this.intervals == null) {
          missing.append(" intervals");
        }
        if (this.type == null) {
          missing.append(" type");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new WildcardInterval(this.intervals, this.type);
    }
  }
}

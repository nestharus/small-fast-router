package org.nestharus.parser.value;

import java.util.Objects;

import com.google.common.collect.Range;
import org.jspecify.annotations.NonNull;
import org.nestharus.parser.type.WildcardIntervalType;

public record WildcardInterval(
    Range<@NonNull Integer> interval, @NonNull WildcardIntervalType type) {

  public WildcardInterval {
    Objects.requireNonNull(interval, "property :interval is required");
    Objects.requireNonNull(type, "property :type is required");
  }

  @Override
  public @NonNull String toString() {
    return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Range<@NonNull Integer> interval;

    private WildcardIntervalType type;

    private Builder() {}

    public Builder interval(Range<@NonNull Integer> interval) {
      this.interval = Objects.requireNonNull(interval, "Null interval");
      return this;
    }

    public Builder type(@NonNull WildcardIntervalType type) {
      this.type = Objects.requireNonNull(type, "Null type");
      return this;
    }

    public WildcardInterval build() {
      if (this.interval == null || this.type == null) {
        StringBuilder missing = new StringBuilder();
        if (this.interval == null) {
          missing.append(" interval");
        }
        if (this.type == null) {
          missing.append(" type");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new WildcardInterval(this.interval, this.type);
    }
  }
}

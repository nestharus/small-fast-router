package org.nestharus.router;

import java.util.Objects;

import jdk.incubator.vector.ByteVector;
import org.jspecify.annotations.NonNull;

public record MaskedVector(@NonNull ByteVector vector, int length) {
  public MaskedVector {
    Objects.requireNonNull(vector, "property :vector is required");
  }

  @Override
  public @NonNull String toString() {
    return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ByteVector vector;

    private int length;

    private Builder() {
      length = 0;
    }

    public Builder vector(@NonNull ByteVector vector) {
      this.vector = Objects.requireNonNull(vector, "Null vector");
      return this;
    }

    public Builder length(int length) {
      if (length <= 0) {
        throw new IllegalArgumentException("length must be non-negative");
      }
      this.length = length;
      return this;
    }

    public MaskedVector build() {
      if (this.vector == null || this.length == 0) {
        StringBuilder missing = new StringBuilder();
        if (this.vector == null) {
          missing.append(" vector");
        }
        if (this.length == 0) {
          missing.append(" length");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new MaskedVector(this.vector, this.length);
    }
  }
}

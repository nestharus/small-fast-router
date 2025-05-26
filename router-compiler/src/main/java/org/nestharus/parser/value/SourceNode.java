package org.nestharus.parser.value;

import java.util.Objects;

public record SourceNode(int lineNumber, int columnNumber, String text) {
  public SourceNode {
    Objects.requireNonNull(text, "property :text is required");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private int lineNumber;

    private int columnNumber;

    private String text;

    private Builder() {}

    public Builder lineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    public Builder columnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
      return this;
    }

    public Builder text(String text) {
      this.text = Objects.requireNonNull(text, "Null text");
      return this;
    }

    public SourceNode build() {
      if (this.text == null) {
        throw new IllegalStateException("Missing required property: text");
      }
      return new SourceNode(this.lineNumber, this.columnNumber, this.text);
    }
  }
}

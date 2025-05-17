package org.nestharus.parser.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.NonNull;

public record BranchNode(@NonNull ScopeNode containingScope, List<ParserNode> children)
    implements ParserNode, ScopeNode {
  public BranchNode {
    Objects.requireNonNull(containingScope, "property :containingScope is required");
    Objects.requireNonNull(children, "property :children is required");
  }

  @Override
  public ParserNodeType type() {
    return ParserNodeType.BRANCH;
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

    private Builder() {}

    public Builder containingScope(@NonNull ScopeNode containingScope) {
      this.containingScope = Objects.requireNonNull(containingScope, "Null containingScope");
      return this;
    }

    public BranchNode build() {
      if (this.containingScope == null) {
        throw new IllegalStateException("Missing required properties:" + " containingScope");
      }
      return new BranchNode(this.containingScope, new ArrayList<>());
    }
  }
}

package org.nestharus.parser.value;

import java.util.Optional;

public interface ValueNode {
  Optional<SourceNode> sourceNode();
}

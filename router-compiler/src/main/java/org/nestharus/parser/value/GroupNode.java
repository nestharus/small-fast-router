package org.nestharus.parser.value;

import java.util.List;

public interface GroupNode<T extends ParserNode> {
  List<T> children();
}

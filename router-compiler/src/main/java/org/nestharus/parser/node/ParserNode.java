package org.nestharus.parser.node;

public interface ParserNode {
  ParserNodeType type();

  ScopeNode containingScope();
}

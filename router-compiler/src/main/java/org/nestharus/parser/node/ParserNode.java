package org.nestharus.parser.node;

public interface ParserNode {
  ParserNodeType getType();

  ScopeNode getContainingScope();
}

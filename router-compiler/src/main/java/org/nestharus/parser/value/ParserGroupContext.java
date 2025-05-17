package org.nestharus.parser.value;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class ParserGroupContext {
  private final Deque<ImmutableList.Builder<ParserNode>> contextStack;

  public ParserGroupContext() {
    contextStack = new ArrayDeque<>();
  }

  public void add(final ParserNode node) {
    contextStack.peek().add(node);
  }

  public void push() {
    contextStack.push(new ImmutableList.Builder<>());
  }

  public List<ParserNode> pop() {
    return contextStack.pop().build();
  }
}

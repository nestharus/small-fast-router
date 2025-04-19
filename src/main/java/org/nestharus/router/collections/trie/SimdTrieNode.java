package org.nestharus.router.collections.trie;

import java.util.Collections;
import java.util.Map;

/**
 * Placeholder for a node that performs non‑byte checks (SIMD, char‑class, etc.). These nodes will
 * never be DFA‑converted.
 */
public final class SimdTrieNode<T> implements TrieNode<T> {

  @Override
  public boolean isTerminal() {
    return false;
  }

  @Override
  public Map<Byte, TrieNode<T>> children() {
    return Collections.emptyMap();
  }
}

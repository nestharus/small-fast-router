package org.nestharus.router.collections.trie;

/**
 * Minimal behavioural contract shared by every node type.
 *
 * <p>Additional methods (priority, SIMD masks, etc.) can be added later.
 */
public sealed interface TrieNode permits ByteTrieNodeI, SimdTrieNode {
  TrieNodeType type();

  boolean isTerminal();

  Iterable<TrieNode> children();
}

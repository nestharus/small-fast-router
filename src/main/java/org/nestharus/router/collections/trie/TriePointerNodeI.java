package org.nestharus.router.collections.trie;

public sealed interface TriePointerNodeI<T> permits DfaTriePointerNode, TriePointerNode {
  TrieNodeType type();

  T get();
}

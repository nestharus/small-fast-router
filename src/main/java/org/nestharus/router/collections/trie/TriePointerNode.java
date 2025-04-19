package org.nestharus.router.collections.trie;

/**
 * @param id 1‑based index into arb‑table
 */
public record TriePointerNode(int id) implements TriePointerNode<TrieNode> {
  @Override
  public Object get() {
    return null;
  }
}

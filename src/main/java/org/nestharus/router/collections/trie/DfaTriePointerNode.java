package org.nestharus.router.collections.trie;

/**
 * @param offset byte offset into segment
 */
public record DfaTriePointerNode(int offset) implements TriePointerNodeI {
  @Override
  public Object get() {
    return null;
  }
}

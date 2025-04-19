package org.nestharus.router.collections.trie;

public sealed interface ByteTrieNodeI extends TrieNode<Byte> permits ByteTrieNode {
  ByteTrieNodeI child(byte b);

  Iterable<TrieNode<Byte>> children();

  boolean isTerminal();
}

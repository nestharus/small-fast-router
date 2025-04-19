package org.nestharus.router.matcher;

import org.nestharus.router.collections.trie.ByteTrieNodeI;
import org.nestharus.router.collections.trie.TrieNode;

public final class TrieMatcher {
  private TrieMatcher() {}

  public static int match(TrieNode root, byte[] in, int pos) {
    int i = pos;
    ByteTrieNodeI n = root;
    while (i < in.length && n != null) {
      n = n.child(in[i]);
      i++;
      if (n != null && n.isTerminal()) return i;
    }
    return -1;
  }
}

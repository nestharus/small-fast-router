package org.nestharus.router.collections.trie;

import java.util.HashMap;
import java.util.Map;

public final class ByteTrieNode implements ByteTrieNodeI {
  private final Map<Byte, ByteTrieNodeI> kids = new HashMap<>();
  private boolean term;

  @Override
  public ByteTrieNodeI child(byte b) {
    return kids.get(b);
  }

  @Override
  public Iterable<Byte> children() {
    return kids.keySet();
  }

  @Override
  public boolean isTerminal() {
    return term;
  }

  /* package */ Map<Byte, ByteTrieNodeI> raw() {
    return kids;
  }

  /* package */ void setTerminal() {
    term = true;
  }
}

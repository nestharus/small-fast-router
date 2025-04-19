package org.nestharus.router.collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class PrefixCompressedTrieNode {

  /** Immutable edge label (may be empty for root). */
  public final byte[] label;

  private final Map<Byte, Object> children = new HashMap<>();
  private int priority = -1; // –1 → non‑terminal

  private PrefixCompressedTrieNode(byte[] lbl) {
    this.label = lbl;
  }

  /* ---------- factory helpers ---------- */
  public static PrefixCompressedTrieNode nonTerminal(byte[] lbl) {
    return new PrefixCompressedTrieNode(Arrays.copyOf(lbl, lbl.length));
  }

  public static PrefixCompressedTrieNode terminal(byte[] lbl, int prio) {
    PrefixCompressedTrieNode n = nonTerminal(lbl);
    n.priority = prio;
    return n;
  }

  /* ---------- builder‑time helpers ---------- */
  public void putChild(byte k, Object n) {
    children.put(k, n);
  }

  public Object child(byte k) {
    return children.get(k);
  }

  public Set<Map.Entry<Byte, Object>> childEntries() {
    return children.entrySet();
  }

  /** Promote to terminal (highest priority wins). */
  public void markTerminal(int prio) {
    if (prio > priority) priority = prio;
  }

  public boolean isTerminal() {
    return priority >= 0;
  }

  public int priority() {
    return priority;
  }
}

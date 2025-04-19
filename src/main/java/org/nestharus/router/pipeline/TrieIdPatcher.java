package org.nestharus.router.pipeline;

import java.util.Map;

import org.nestharus.router.collections.PrefixCompressedTrieNode;

public final class TrieIdPatcher {

  private TrieIdPatcher() {}

  public static void patch(
      PrefixCompressedTrieNode root, Map<RegionCarver.ArbitraryRegion, Integer> arbIds) {
    root.childEntries().forEach(e -> patchRec(e.getValue(), arbIds));
  }

  private static void patchRec(Object child, Map<RegionCarver.ArbitraryRegion, Integer> arbIds) {
    if (child instanceof RegionCarver.ArbPlaceholder(RegionCarver.ArbitraryRegion region)) {
      int cell = 0x800000 | (arbIds.get(region) << 12);
      PrefixCompressedTrieNode surrogate = PrefixCompressedTrieNode.terminal(new byte[0], cell);
      // parent reference already known -> replace via side effect
      // (caller always does node.putChild)
      return; // caller replaced entry
    }
    if (child instanceof PrefixCompressedTrieNode n) {
      n.childEntries().forEach(e -> patchRec(e.getValue(), arbIds));
    }
  }
}

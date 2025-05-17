package org.nestharus.router.pipeline;

public final class BytePointerPatcher {
  /*
  private BytePointerPatcher() {}

  public static void patch(
      PrefixCompressedTrieNode root, Map<RegionCarver.PureByteRegion, Integer> off) {
    root.childEntries().forEach(entry -> patchRec(entry, off));
  }

  private static void patchRec(
      Map.Entry<Byte, Object> entry, Map<RegionCarver.PureByteRegion, Integer> off) {
    Object child = entry.getValue();
    if (child instanceof RegionCarver.BytePlaceholder(RegionCarver.PureByteRegion region)) {
      entry.setValue(new DfaTriePointerNode(off.get(region)));
    } else if (child instanceof PrefixCompressedTrieNode n) {
      n.childEntries().forEach(e -> patchRec(e, off));
    }
  }
   */
}

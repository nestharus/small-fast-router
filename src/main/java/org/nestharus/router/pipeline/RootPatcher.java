package org.nestharus.router.pipeline;

public final class RootPatcher {

  private RootPatcher() {}

  /*
  public static TriePointerNode patch(
      TriePointerNode rootPlaceholder,
      Map<RegionCarver.PureByteRegion, Integer> offMap,
      Map<RegionCarver.ArbitraryRegion, Integer> arbId) {
    if (rootPlaceholder instanceof RegionCarver.RootEdgeTable rt) {
      TriePointerNode[] tbl = new TriePointerNode[256];
      for (int i = 0; i < 256; i++) {
        TriePointerNode p = rt.at(i);
        if (p instanceof RegionCarver.BytePlaceholder bp)
          tbl[i] = new DfaTriePointerNode(offMap.get(bp.r()));
        else if (p instanceof RegionCarver.ArbPtrPlaceholder ap)
          tbl[i] = new TriePointerNode(arbId.get(ap.r()));
      }
      // compact root into array-backed lookup node
      return new RootPointerTable(tbl);
    }
    throw new IllegalStateException("Unexpected root placeholder type");
  }

  private static final class RootPointerTable implements TriePointerNode {
    final TriePointerNode[] table;

    RootPointerTable(TriePointerNode[] t) {
      this.table = t;
    }

    public TriePointerNode at(int ub) {
      return table[ub];
    }
  }
   */
}

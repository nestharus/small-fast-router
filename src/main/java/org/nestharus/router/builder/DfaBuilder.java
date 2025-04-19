package org.nestharus.router.builder;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.BitSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.nestharus.router.collections.PrefixCompressedTrieNode;
import org.nestharus.router.collections.dfa.PureByteDfaSegment;
import org.nestharus.router.pipeline.RegionCarver;

public final class DfaBuilder {

  private static final int ROW = 256 * 3;
  private static final int STATE = ((ROW + 1 + 63) & ~63); // 64‑byte aligned

  private DfaBuilder() {}

  public static Emit emit(
      List<RegionCarver.PureByteRegion> byteRegs,
      Map<RegionCarver.ArbitraryRegion, Integer> arbIds,
      Arena arena) {
    /* 1. assign contiguous stateIndex to every Byte‑node */
    Map<PrefixCompressedTrieNode, Integer> idx = new IdentityHashMap<>();
    int next = 0;
    for (var reg : byteRegs) for (var n : reg.nodes()) idx.put(n, next++);

    MemorySegment seg = arena.allocate((long) next * STATE);
    BitSet startBm = new BitSet(256);
    Map<RegionCarver.PureByteRegion, Integer> regionOff = new IdentityHashMap<>();

    /* 2. convert edges & row‑write per region */
    for (var reg : byteRegs) {
      int baseOff = idx.get(reg.nodes().get(0)) * STATE;
      regionOff.put(reg, baseOff);
      reg.nodes().forEach(n -> convertEdges(n, idx, arbIds));

      for (var n : reg.nodes()) {
        int sIdx = idx.get(n);
        byte[] row = new byte[ROW];
        for (var e : n.childEntries()) {
          int ub = e.getKey() & 0xFF;
          int cell = ((PrefixCompressedTrieNode) e.getValue()).priority();
          row[ub * 3] = (byte) (cell >>> 16);
          row[ub * 3 + 1] = (byte) (cell >>> 8);
          row[ub * 3 + 2] = (byte) cell;
          if (sIdx == 0) startBm.set(ub);
        }
        seg.asSlice((long) sIdx * STATE, ROW).copyFrom(MemorySegment.ofArray(row));
        if (n.isTerminal())
          seg.set(ValueLayout.JAVA_BYTE, (long) sIdx * STATE + STATE - 1, (byte) n.priority());
      }
    }
    PureByteDfaSegment dfa = new PureByteDfaSegment(seg, STATE, startBm);
    return new Emit(dfa, regionOff);
  }

  /* replace each edge value by its FINAL 24‑bit cell */
  private static void convertEdges(
      PrefixCompressedTrieNode n,
      Map<PrefixCompressedTrieNode, Integer> idx,
      Map<RegionCarver.ArbitraryRegion, Integer> arbIds) {
    n.childEntries()
        .forEach(
            e -> {
              PrefixCompressedTrieNode tgt = (PrefixCompressedTrieNode) e.getValue();

              int cell;
              if (tgt.label.length == 0
                  && tgt.priority() >= 0
                  && (tgt.priority() & 0x800000) != 0) // surrogate
              {
                cell = tgt.priority() | idx.get(n); // fill resumeIdx
              } else { // local jump
                cell = idx.get(tgt);
              }
              e.setValue(PrefixCompressedTrieNode.terminal(new byte[0], cell));
            });
  }

  /** Emitter result. */
  public record Emit(
      PureByteDfaSegment segment, Map<RegionCarver.PureByteRegion, Integer> regionToOffset) {}
}

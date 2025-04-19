package org.nestharus.router.collections.dfa;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public final class DfaSegment {
  private final MemorySegment seg;
  private final long base;
  private final int stateSize;
  private final VarHandle VH_BYTE;
  private final java.util.BitSet startBitmap;

  public DfaSegment(MemorySegment seg, int stateSize, java.util.BitSet bm) {
    this.seg = seg;
    this.base = seg.address();
    this.stateSize = stateSize;
    this.startBitmap = bm;
    VH_BYTE = ValueLayout.JAVA_BYTE.varHandle();
  }

  public int transition(int stateOff, int ub) {
    long off = base + stateOff + (ub * 3L);
    int b0 = (byte) VH_BYTE.get(seg, off) & 0xFF;
    int b1 = (byte) VH_BYTE.get(seg, off + 1) & 0xFF;
    int b2 = (byte) VH_BYTE.get(seg, off + 2) & 0xFF;
    return (b0 << 16) | (b1 << 8) | b2;
  }

  public byte accept(int stateOff) {
    return (byte) VH_BYTE.get(seg, base + stateOff + stateSize - 1);
  }

  public boolean startsWith(byte b) {
    return startBitmap.get(b & 0xFF);
  }

  public int stateSize() {
    return stateSize;
  }
}

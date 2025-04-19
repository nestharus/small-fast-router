package org.nestharus.router.collections.dfa;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.util.BitSet;

public final class PureByteDfaSegment {
  private static final VarHandle GET = ValueLayout.JAVA_BYTE.varHandle();

  private final MemorySegment seg;
  private final int stateSize;
  private final BitSet startBitmap;

  public PureByteDfaSegment(MemorySegment s, int stateSize, BitSet bm) {
    this.seg = s;
    this.stateSize = stateSize;
    this.startBitmap = bm;
  }

  public int stateSize() {
    return stateSize;
  }

  public boolean state0Accepts(byte b) {
    return startBitmap.get(b & 0xFF);
  }

  public int transition(int stateOff, int ub) {
    long p = (long) stateOff + ((long) ub * 3L);
    int b0 = (byte) GET.get(seg, p) & 0xFF;
    int b1 = (byte) GET.get(seg, p + 1L) & 0xFF;
    int b2 = (byte) GET.get(seg, p + 2L) & 0xFF;
    return (b0 << 16) | (b1 << 8) | b2;
  }

  public byte accept(int off) {
    return (byte) GET.get(seg, (long) off + stateSize - 1L);
  }
}

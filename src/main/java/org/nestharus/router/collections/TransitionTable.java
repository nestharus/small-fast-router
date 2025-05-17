package org.nestharus.router.collections;

/**
 * Immutable, runtime‑only representation of ONE packed DFA region.
 *
 * <p>Construction is delegated to {@link TransitionTableBuilder} (step 2+).
 *
 * @param <T> type of user payload (not yet used, but kept for future tags)
 */
public final class TransitionTable<T> {
  /*
  public static final class Layout {
    public static final int TRANSITION_ENTRY_SIZE = 3; // 24‑bit offset
    public static final int ACCEPT_FLAG_SIZE = 1; // 1 byte footer
    public static final int STATE_SIZE =
        ((TRANSITION_ENTRY_SIZE * 256) + ACCEPT_FLAG_SIZE + 63) & ~63; // 64‑byte aligned

    private Layout() {}
  }

  private final MemorySegment segment;
  private final BitSet startBitmap; // bitmap of first bytes accepted by DFA

  TransitionTable(MemorySegment segment, BitSet bitmap) {
    this.segment = Objects.requireNonNull(segment, "segment");
    this.startBitmap = (BitSet) bitmap.clone();
  }

  public int match(byte[] input, int offset, int limit) {
    if (offset >= limit) return -1;

    int first = Byte.toUnsignedInt(input[offset]);
    if (!startBitmap.get(first)) return -1;

    MemorySegment full = segment.reinterpret(segment.byteSize());
    int stateOff = 0;
    int best = -1;

    for (int i = offset; i < limit; i++) {
      int b = Byte.toUnsignedInt(input[i]);

      long rel = stateOff + (b * Layout.TRANSITION_ENTRY_SIZE);
      int t0 = full.get(ValueLayout.JAVA_BYTE, rel) & 0xFF;
      int t1 = full.get(ValueLayout.JAVA_BYTE, rel + 1) & 0xFF;
      int t2 = full.get(ValueLayout.JAVA_BYTE, rel + 2) & 0xFF;
      int trans = (t0 << 16) | (t1 << 8) | t2;

      // Pure DFA – tagged transitions are impossible
      if ((trans & 0x800000) != 0) break;

      stateOff = trans;

      byte accept = (byte) VH_BYTE.get(full, stateOff + Layout.STATE_SIZE - 1);
      if (accept != 0) best = i + 1;
    }
    return best;
  }

  public MemorySegment segment() {
    return segment;
  }

  public BitSet startBitmap() {
    return (BitSet) startBitmap.clone();
  }

  @Override
  public String toString() {
    long states = segment.byteSize() / Layout.STATE_SIZE;
    return "TransitionTable[states=" + states + ", bytes=" + segment.byteSize() + "]";
  }

   */
}

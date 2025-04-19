package org.nestharus.router.collections;

import java.lang.foreign.MemorySegment;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Utility that keeps a canonical copy of every unique DFA transition row (768 bytes) and re‑uses it
 * for subsequent identical states.
 *
 * <p>The key is the <em>content</em> of the row, not its identity.
 */
final class RowDeduplicator {

  private final int rowLen;
  private final Map<RowKey, Integer> offsetByRow = new HashMap<>();

  RowDeduplicator(int rowLen) {
    this.rowLen = rowLen;
  }

  /**
   * Write {@code rowBuf} to {@code destOffset} in {@code segment} – unless an identical row has
   * already been written, in which case the canonical bytes are copied instead.
   *
   * @return the offset of the canonical row (may differ from destOffset)
   */
  int deduplicateAndWrite(byte[] rowBuf, MemorySegment segment, int destOffset) {
    RowKey key = new RowKey(rowBuf);
    Integer canonicalOff = offsetByRow.get(key);
    if (canonicalOff == null) {
      // first time we see this content → store as canonical
      segment.asSlice(destOffset, rowLen).copyFrom(MemorySegment.ofArray(rowBuf));
      offsetByRow.put(key, destOffset);
      return destOffset;
    } else {
      // duplicate → copy existing canonical bytes
      segment.asSlice(destOffset, rowLen).copyFrom(segment.asSlice(canonicalOff, rowLen));
      return canonicalOff;
    }
  }

  /* ---------------------- internal key ---------------------- */
  private record RowKey(byte[] bytes) {
    RowKey {
      Objects.requireNonNull(bytes);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(bytes);
    }

    @Override
    public boolean equals(Object o) {
      return (o instanceof RowKey rk) && Arrays.equals(bytes, rk.bytes);
    }
  }
}

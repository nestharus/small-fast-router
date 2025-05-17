package org.nestharus.router.collections;

import static org.junit.jupiter.api.Assertions.*;

class TransitionTableTest {
  /*
   @Test
   void acceptsSingleA() {
     try (Arena arena = Arena.ofConfined()) {

       int stateSize = TransitionTable.Layout.STATE_SIZE;
       MemorySegment seg = arena.allocate(stateSize * 2); // state0 & state1

       byte[] row0 = new byte[TransitionTable.Layout.TRANSITION_ENTRY_SIZE * 256];
       int offsetState1 = stateSize; // state1 starts after state0
       int p = 'a' * 3;
       row0[p] = (byte) ((offsetState1 >>> 16) & 0xFF);
       row0[p + 1] = (byte) ((offsetState1 >>> 8) & 0xFF);
       row0[p + 2] = (byte) (offsetState1 & 0xFF);
       seg.asSlice(0, row0.length).copyFrom(MemorySegment.ofArray(row0));

       seg.set(java.lang.foreign.ValueLayout.JAVA_BYTE, offsetState1 + stateSize - 1, (byte) 1);

       BitSet bitmap = new BitSet(256);
       bitmap.set('a');

       TransitionTable<Void> table = new TransitionTable<>(seg, bitmap);

       assertEquals(1, table.match("a".getBytes(), 0, 1));
       assertEquals(-1, table.match("b".getBytes(), 0, 1));
     }
   }

  */
}

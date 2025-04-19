package org.nestharus.router.collections;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.*;

import org.nestharus.router.collections.trie.ByteTrieNodeI;

/**
 * Compile‑time helper that converts one <em>or many</em> pure {@link ByteTrieNodeI} tries into a
 * packed {@link TransitionTable}.
 *
 * <p>Assumptions for STEP 2:
 *
 * <ul>
 *   <li>Every child reference is a {@code ByteTrieNode} (no SIMD / pointer nodes yet).
 *   <li>Accept payload is a single byte flag (1 = terminal).
 * </ul>
 *
 * <p>Row de‑duplication: identical 768‑byte transition rows are stored once and shared by states
 * that have the same outgoing table.
 */
public final class TransitionTableBuilder {

  private TransitionTableBuilder() {}

  public static <T> BuildResult<T> build(List<ByteTrieNodeI<T>> roots, Arena arena) {
    validateArgs(roots, arena);
  }

  private static <T> void validateArgs(List<ByteTrieNodeI<T>> roots, Arena arena) {
    Objects.requireNonNull(roots, "roots list must not be null");
    Objects.requireNonNull(arena, "arena must not be null");

    // Reject empty lists – a TransitionTable without states is invalid.
    if (roots.isEmpty()) {
      throw new IllegalArgumentException("At least one root trie is required");
    }

    // Roots must be distinct; IdentityHashSet via IdentityHashMap for speed.
    var seen = Collections.newSetFromMap(new IdentityHashMap<ByteTrieNodeI<T>, Boolean>());
    for (ByteTrieNodeI<T> root : roots) {
      if (!seen.add(root)) {
        throw new IllegalArgumentException("Duplicate root trie detected: " + root);
      }
    }
  }

  private static <T> StateMetadata<T> enumerateStates(List<ByteTrieNodeI<T>> roots) {
    Map<ByteTrieNodeI<T>, Integer> nodeToState = new IdentityHashMap<>();
    List<ByteTrieNodeI<T>> stateList = new ArrayList<>();
    Deque<ByteTrieNodeI<T>> queue = new ArrayDeque<>();

    Map<ByteTrieNodeI<T>, Integer> rootOffsets = new IdentityHashMap<>();
    BitSet startBitmap = new BitSet(256);

    for (ByteTrieNodeI<T> root : roots) {
      if (nodeToState.containsKey(root)) continue; // skip duplicates

      int baseIndex = stateList.size(); // assign id to root
      nodeToState.put(root, baseIndex);
      stateList.add(root);
      rootOffsets.put(root, baseIndex * TransitionTable.Layout.STATE_SIZE);
      queue.add(root);

      while (!queue.isEmpty()) {
        ByteTrieNodeI<T> node = queue.removeFirst();
        for (var entry : node.children().entrySet()) {
          ByteTrieNodeI<T> child = (ByteTrieNodeI<T>) entry.getValue();

          // assign new state id if not seen
          if (nodeToState.putIfAbsent(child, stateList.size()) == null) {
            stateList.add(child);
            queue.add(child);
          }

          // update bitmap only for transitions leaving a root
          if (node == root) {
            startBitmap.set(Byte.toUnsignedInt(entry.getKey()));
          }
        }
      }
    }
    return new StateMetadata<>(stateList, nodeToState, rootOffsets, startBitmap);
  }

  private static MemorySegment allocateSegment(int stateCount, Arena arena) {
    long bytes = (long) stateCount * TransitionTable.Layout.STATE_SIZE;
    return arena.allocate(bytes);
  }
}

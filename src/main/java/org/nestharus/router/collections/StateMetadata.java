package org.nestharus.router.collections;

/**
 * Immutable container for the data gathered during the BFS enumeration step inside {@link
 * TransitionTableBuilder}.
 *
 * @param stateList ordered list of unique states (index == stateId)
 * @param nodeToState identity map: node → stateId
 * @param rootOffsets byte offset in the segment where each supplied root starts
 * @param startBitmap ORed bitmap of the first rows of all roots
 */
record StateMetadata<T>(/*
    List<ByteTrieNodeI<T>> stateList,
    Map<ByteTrieNodeI<T>, Integer> nodeToState,
    Map<ByteTrieNodeI<T>, Integer> rootOffsets,
    BitSet startBitmap*/ ) {}

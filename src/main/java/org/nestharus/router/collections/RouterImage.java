package org.nestharus.router.collections;

import java.util.List;

import org.nestharus.router.collections.dfa.PureByteDfaSegment;

public record RouterImage(
    PrefixCompressedTrieNode rootTrie,
    PrefixCompressedTrieNode[] fastRoot, // 256‑entry first‑char table
    PureByteDfaSegment dfaSegment,
    List<PrefixCompressedTrieNode> arbTable // 1‑based index
    ) {}

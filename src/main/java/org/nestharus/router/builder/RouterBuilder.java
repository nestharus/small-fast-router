package org.nestharus.router.builder;

import java.lang.foreign.Arena;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nestharus.router.collections.PrefixCompressedTrieNode;
import org.nestharus.router.collections.RouterImage;
import org.nestharus.router.pipeline.*;

public final class RouterBuilder {

  private RouterBuilder() {}

  public static RouterImage build(List<String> routePatterns) {
    /* Pass S – compile source strings into trie with priority scores */
    PrefixCompressedTrieNode rootTrie = RouteCompiler.buildTrie(routePatterns);

    /* Pass A – carve regions */
    var carve = RegionCarver.carve(rootTrie);

    /* Pass B – id tables */
    Map<RegionCarver.ArbitraryRegion, Integer> arbIds = IdAssigner.assignIds(carve.arbRegs());
    Map<RegionCarver.PureByteRegion, Integer> byteIds =
        IdAssigner.assignIds(carve.byteRegs()); // not used further (offsets suffice)

    /* Pass C – patch ARB placeholders (wildcards) */
    TrieIdPatcher.patch(carve.root(), arbIds);

    /* Pass D – emit DFA, get per‑region offset map */
    Arena arena = Arena.ofShared();
    var emit = DfaBuilder.emit(carve.byteRegs(), arbIds, arena);

    /* Pass E – patch BYTE placeholders */
    BytePtrPatcher.patch(carve.root(), emit.regionToOffset());

    /* fast‑root array for first byte */
    PrefixCompressedTrieNode[] fast = new PrefixCompressedTrieNode[256];
    carve
        .root()
        .childEntries()
        .forEach(e -> fast[e.getKey() & 0xFF] = (PrefixCompressedTrieNode) e.getValue());

    /* ARB region table (1‑based) */
    List<PrefixCompressedTrieNode> arbTable = new ArrayList<>();
    carve.arbRegs().forEach(r -> arbTable.add(r.nodes().get(0)));

    return new RouterImage(carve.root(), fast, emit.segment(), arbTable);
  }
}

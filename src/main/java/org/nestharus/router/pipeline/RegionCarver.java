package org.nestharus.router.pipeline;

public final class RegionCarver {
  /*
  public static final class PureByteRegion {
    private final List<PrefixCompressedTrieNode> nodes = new ArrayList<>();

    public List<PrefixCompressedTrieNode> nodes() {
      return nodes;
    }
  }

  public static final class ArbitraryRegion {
    private final List<PrefixCompressedTrieNode> nodes = new ArrayList<>();

    public List<PrefixCompressedTrieNode> nodes() {
      return nodes;
    }
  }

  public sealed interface Placeholder permits BytePlaceholder, ArbPlaceholder {}

  public record BytePlaceholder(PureByteRegion region) implements Placeholder {}

  public record ArbPlaceholder(ArbitraryRegion region) implements Placeholder {}

  public final class RootEdgeTable implements TriePointerNode {
    private final TriePointerNode[] table = new TriePointerNode[256];

    public void put(byte b, TriePointerNode p) {
      table[b & 0xFF] = p;
    }

    public TriePointerNode at(int ub) {
      return table[ub];
    }

    public TriePointerNode[] entries() {
      return table;
    }
  }

  public record CarveResult(
      PrefixCompressedTrieNode root,
      List<PureByteRegion> byteRegs,
      List<ArbitraryRegion> arbRegs) {}

  private RegionCarver() {}

  public static CarveResult carve(PrefixCompressedTrieNode triRoot) {
    List<PureByteRegion> byteRegs = new ArrayList<>();
    List<ArbitraryRegion> arbRegs = new ArrayList<>();

    PrefixCompressedTrieNode patched = carveRec(triRoot, false, byteRegs, arbRegs);

    return new CarveResult(patched, byteRegs, arbRegs);
  }

  private static PrefixCompressedTrieNode carveRec(
      PrefixCompressedTrieNode node,
      boolean inByte,
      List<PureByteRegion> byteRegs,
      List<ArbitraryRegion> arbRegs) {
    boolean isByteNode = node.label.length > 0; // root has empty label → ARB

    if (isByteNode && !inByte) { // start BYTE region
      PureByteRegion reg = new PureByteRegion();
      dfsByte(node, reg);
      byteRegs.add(reg);
      return new BytePlaceholder(reg); // substituted later
    }
    if (!isByteNode && inByte) { // start ARB region
      ArbitraryRegion reg = new ArbitraryRegion();
      dfsArb(node, reg);
      arbRegs.add(reg);
      return new ArbPlaceholder(reg);
    }

    PrefixCompressedTrieNode clone =
        node.isTerminal()
            ? PrefixCompressedTrieNode.terminal(node.label, node.priority())
            : PrefixCompressedTrieNode.nonTerminal(node.label);

    for (var e : node.childEntries()) {
      clone.putChild(
          e.getKey(),
          carveRec((PrefixCompressedTrieNode) e.getValue(), isByteNode, byteRegs, arbRegs));
    }
    return clone;
  }

  private static void dfsByte(PrefixCompressedTrieNode n, PureByteRegion r) {
    r.nodes.add(n);
    n.childEntries().stream()
        .map(Map.Entry::getValue)
        .map(PrefixCompressedTrieNode.class::cast)
        .filter(ch -> ch.label.length > 0) // still BYTE
        .forEach(ch -> dfsByte(ch, r));
  }

  private static void dfsArb(PrefixCompressedTrieNode n, ArbitraryRegion r) {
    r.nodes.add(n);
    n.childEntries().stream()
        .map(Map.Entry::getValue)
        .map(PrefixCompressedTrieNode.class::cast)
        .filter(ch -> ch.label.length == 0) // ARB kind
        .forEach(ch -> dfsArb(ch, r));
  }
  */
}

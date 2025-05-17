package org.nestharus.router.pipeline;

public final class RouteCompiler {
  /*
  public static PrefixCompressedTrieNode buildTrie(List<String> routes) {
    Map<String, Integer> prio = RouteScorer.score(routes);
    PrefixCompressedTrieNode root = PrefixCompressedTrieNode.nonTerminal(new byte[0]);

    for (String r : routes) {
      byte[] b = r.getBytes(StandardCharsets.UTF_8);
      insert(root, b, 0, prio.get(r), false);
    }
    return root;
  }

  private static void insert(
      PrefixCompressedTrieNode node, byte[] route, int pos, int priority, boolean hasDfaAnc) {
    if (pos >= route.length) {
      if (!hasDfaAnc) node.markTerminal(priority);
      return;
    }
    byte k = route[pos];
    Object child = node.child(k);

    boolean edgeIsByte = route[pos] != '*';
    boolean nextHasDfa = hasDfaAnc || edgeIsByte;

    if (child == null) {
      byte[] tail = Arrays.copyOfRange(route, pos, route.length);
      PrefixCompressedTrieNode c = PrefixCompressedTrieNode.nonTerminal(tail);
      node.putChild(k, c);
      insert(c, route, route.length, priority, nextHasDfa);
      return;
    }
    PrefixCompressedTrieNode ch = (PrefixCompressedTrieNode) child;
    int com = commonPrefix(ch.label, route, pos);
    if (com < ch.label.length) {
      byte[] newLab = Arrays.copyOf(ch.label, com);
      byte[] oldR = Arrays.copyOfRange(ch.label, com, ch.label.length);
      PrefixCompressedTrieNode split = PrefixCompressedTrieNode.nonTerminal(newLab);
      node.putChild(k, split);
      ch.label = oldR;
      split.putChild(oldR[0], ch);
      ch = split;
    }
    insert(ch, route, pos + com, priority, nextHasDfa);
  }

  private static int commonPrefix(byte[] a, byte[] b, int ob) {
    int n = Math.min(a.length, b.length - ob);
    for (int i = 0; i < n; i++) if (a[i] != b[ob + i]) return i;
    return n;
  }
  */
}

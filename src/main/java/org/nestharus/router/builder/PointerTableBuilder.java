package org.nestharus.router.builder;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class PointerTableBuilder {

  private PointerTableBuilder() {}

  /** Assign contiguous 1‑based IDs to every region placeholder. */
  public static <P> Map<P, Integer> buildIdTable(List<P> placeholders) {
    Map<P, Integer> map = new IdentityHashMap<>(placeholders.size() * 2);
    int id = 1;
    for (P p : placeholders) map.put(p, id++);
    return map;
  }
}

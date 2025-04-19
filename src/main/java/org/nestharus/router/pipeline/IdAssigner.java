package org.nestharus.router.pipeline;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class IdAssigner {
  private IdAssigner() {}

  public static <T> Map<T, Integer> assignIds(List<T> list) {
    Map<T, Integer> m = new IdentityHashMap<>();
    for (int i = 0; i < list.size(); i++) m.put(list.get(i), i + 1);
    return m;
  }
}

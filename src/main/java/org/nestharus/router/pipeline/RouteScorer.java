package org.nestharus.router.pipeline;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class RouteScorer {
  private static final Pattern P2 = Pattern.compile("\\*\\*");
  private static final Pattern P1 = Pattern.compile("\\*");

  public static Map<String, Integer> score(List<String> routes) {
    Map<String, Integer> map = new LinkedHashMap<>();
    for (String r : routes) {
      int d2 = (int) P2.matcher(r).results().count();
      int d1 = (int) P1.matcher(r).results().count() - 2 * d2;
      int stat = r.length() - d1 - 2 * d2;
      map.put(r, stat + d1); // each '*'=1, '**'=0
    }
    return map;
  }
}

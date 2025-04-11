package org.nestharus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnsafeWarningTest {
  public static void main(String[] args) {
    // This should generate an unchecked warning
    List list = new ArrayList();
    list.add("test");
    String s = (String) list.get(0);

    // More unchecked operations
    Map map = new HashMap();
    map.put("key", 123);
    Integer i = (Integer) map.get("key");

    System.out.println("Test completed: " + s + ", " + i);
  }
}

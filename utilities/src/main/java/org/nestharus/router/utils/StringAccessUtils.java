package org.nestharus.router.utils;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import javax.annotation.Nonnull;

public final class StringAccessUtils {
  private static final VarHandle STRING_VALUE_HANDLE;

  // Private constructor to prevent instantiation
  private StringAccessUtils() {}

  static {
    try {
      STRING_VALUE_HANDLE =
          MethodHandles.privateLookupIn(String.class, MethodHandles.lookup())
              .findVarHandle(String.class, "value", byte[].class);
    } catch (final Exception exception) {
      throw new RuntimeException("Cannot access String.value field", exception);
    }
  }

  /**
   * Gets the underlying byte array of a String without copying. Simplified implementation for HTTP
   * routing: - No fallback to UTF-8 conversion - we assume direct access works - No exception
   * handling - we let exceptions propagate - No null checks - we assume non-null input in the
   * critical path
   *
   * @param str the String to get the byte array from (cannot be null)
   * @return the underlying byte array
   */
  public static byte[] getStringBytes(@Nonnull final String str) {
    return (byte[]) STRING_VALUE_HANDLE.get(str);
  }
}

package org.nestharus.router.utils;

import static com.google.common.truth.Truth.assertThat;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class StringAccessUtilsTest {

  @Test
  void testGetStringBytesWithUTF8String() {
    // "Hello, World"
    final var expectedBytes =
        new byte[] {
          0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
          0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
        };
    final var expectedString = new String(expectedBytes, StandardCharsets.UTF_8);

    final var actualBytes = StringAccessUtils.getStringBytes(expectedString);

    assertThat(actualBytes).isEqualTo(expectedBytes);
  }

  @Test
  void testGetStringBytesWithEmptyString() {
    final var expectedBytes = new byte[] {};
    final var expectedString = new String(expectedBytes, StandardCharsets.UTF_8);

    final var actualBytes = StringAccessUtils.getStringBytes(expectedString);

    assertThat(actualBytes).isEqualTo(expectedBytes);
  }
}

package org.nestharus.router.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.primitives.Booleans;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class VectorUtilsTest {

  @Nested
  class MaskTests {
    @Test
    void testGetValidMask() {
      IntStream.range(1, VectorUtils.VECTOR_LENGTH + 1)
          .forEach(
              maskLength -> {
                final var expectedSelectedLanes =
                    Stream.of(
                            Collections.nCopies(maskLength, true),
                            Collections.nCopies(VectorUtils.VECTOR_LENGTH - maskLength, false))
                        .flatMap(List::stream)
                        .toArray(Boolean[]::new);

                final var actualMask = VectorUtils.getMask(maskLength);
                final var actualSelectedLanes =
                    Booleans.asList(actualMask.toArray()).toArray(Boolean[]::new);

                assertThat(actualSelectedLanes)
                    .asList()
                    .containsExactlyElementsIn(expectedSelectedLanes);
              });
    }

    @Test
    void testGetInvalidMask() {
      assertThrows(
          ArrayIndexOutOfBoundsException.class,
          () -> VectorUtils.getMask(VectorUtils.VECTOR_LENGTH + 1));
    }
  }

  @Nested
  class LoadVectorTests {
    @Test
    void testLoadVector() {
      // "Hello, World"
      final var expectedBytes =
          new byte[] {
            0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
            0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
          };
      final var alignedExpectedBytes = new byte[VectorUtils.VECTOR_LENGTH];
      System.arraycopy(expectedBytes, 0, alignedExpectedBytes, 0, expectedBytes.length);

      final var resultVector = VectorUtils.loadVector(expectedBytes, 0, expectedBytes.length);
      final var actualBytes = resultVector.toArray();

      assertThat(actualBytes).isEqualTo(alignedExpectedBytes);
    }

    @Test
    void testLoadVectorOffset() {
      // "Hello, World"
      final var expectedBytes =
          new byte[] {
            0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
            0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
          };
      final var alignedExpectedBytes = new byte[VectorUtils.VECTOR_LENGTH];
      System.arraycopy(expectedBytes, 2, alignedExpectedBytes, 0, expectedBytes.length - 2);

      final var resultVector = VectorUtils.loadVector(expectedBytes, 2, expectedBytes.length - 2);
      final var actualBytes = resultVector.toArray();

      assertThat(actualBytes).isEqualTo(alignedExpectedBytes);
    }

    @Test
    void testLoadVectorOverflow() {
      // "Hello, World"
      final var bytes =
          new byte[] {
            0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
            0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
          };

      assertThrows(
          ArrayIndexOutOfBoundsException.class,
          () -> VectorUtils.loadVector(bytes, 0, VectorUtils.VECTOR_LENGTH + 1));
    }

    @Test
    void testCreateVector() {
      // "Hello, World"
      final var expectedBytes =
          new byte[] {
            0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
            0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
          };
      final var alignedExpectedBytes = new byte[VectorUtils.VECTOR_LENGTH];
      System.arraycopy(expectedBytes, 0, alignedExpectedBytes, 0, expectedBytes.length);

      final var resultVector = VectorUtils.createVector(expectedBytes);
      final var actualBytes = resultVector.toArray();

      assertThat(actualBytes).isEqualTo(alignedExpectedBytes);
    }
  }

  @Nested
  class CompareVectorsTests {
    @Test
    void testCompareVectorsEqual() {
      // "Hello, World"
      final var expectedBytes =
          new byte[] {
            0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
            0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
          };

      final var vector1 = VectorUtils.createVector(expectedBytes);
      final var vector2 = VectorUtils.createVector(expectedBytes);

      final var result = VectorUtils.compareVectors(vector1, vector2);

      if (!result) {
        assertThat(vector1.toArray()).isEqualTo(vector2.toArray());
      }
    }

    @Test
    void testCompareVectorsNotEqual() {
      // "Hello, World" vs "Hello, Worle" (last character different)
      final var vector1 =
          VectorUtils.createVector(
              new byte[] {
                0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
                0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
              });
      final var vector2 =
          VectorUtils.createVector(
              new byte[] {
                0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
                0x20, 0x57, 0x6f, 0x72, 0x6c, 0x65
              });

      final var result = VectorUtils.compareVectors(vector1, vector2);

      if (result) {
        assertThat(vector2.toArray()).isNotEqualTo(vector1.toArray());
      }
    }

    @Test
    void testComparePartialVectorsEqual() {
      // "Hello, World" vs "Hello, Worle" (last character different)
      final var vector1 =
          VectorUtils.createVector(
              new byte[] {
                0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
                0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64
              });
      final var vector2 =
          VectorUtils.createVector(
              new byte[] {
                0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c,
                0x20, 0x57, 0x6f, 0x72, 0x6c, 0x65
              });

      final var result = VectorUtils.compareVectors(vector1, vector2, 11);

      if (!result) {
        assertThat(Arrays.copyOf(vector1.toArray(), 11))
            .isEqualTo(Arrays.copyOf(vector2.toArray(), 11));
      }
    }
  }
}

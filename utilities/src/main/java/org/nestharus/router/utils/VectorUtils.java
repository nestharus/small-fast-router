package org.nestharus.router.utils;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;

import jdk.incubator.vector.*;

public final class VectorUtils {
  public static final VectorSpecies<Byte> SPECIES_PREFERRED = ByteVector.SPECIES_PREFERRED;
  public static final int VECTOR_LENGTH = SPECIES_PREFERRED.length();

  // Precompute masks for partial vector operations
  private static final VectorMask<Byte>[] MASKS;

  // Private constructor to prevent instantiation
  private VectorUtils() {}

  static {
    try {
      // Verify Vector API is supported - fail fast if not
      ByteVector.zero(SPECIES_PREFERRED);
    } catch (Exception e) {
      // Require SIMD support
      throw new RuntimeException("Vector API is required for HTTP routing", e);
    }

    // Precompute masks for all possible lengths
    MASKS = createMasks();
  }

  /**
   * Creates an array of vector masks for partial vector operations.
   *
   * @return array of vector masks
   */
  private static VectorMask<Byte>[] createMasks() {
    final var maskArray = new boolean[VECTOR_LENGTH];

    return IntStream.range(0, VECTOR_LENGTH)
        .mapToObj(
            i -> {
              maskArray[i] = true;
              return VectorMask.fromArray(SPECIES_PREFERRED, maskArray, 0);
            })
        .<VectorMask<Byte>>toArray(VectorMask[]::new);
  }

  /**
   * Returns a mask for partial vector operations.
   *
   * @param length the number of true values in the mask
   * @return the vector mask
   */
  public static VectorMask<Byte> getMask(final int length) {
    return MASKS[length - 1];
  }

  /**
   * Loads a byte array into a vector with a mask for partial loads.
   *
   * @param array the byte array (cannot be null)
   * @param offset the offset in the array
   * @param length the number of bytes to load
   * @return the loaded vector
   */
  public static ByteVector loadVector(
      @Nonnull final byte[] array, final int offset, final int length) {
    return ByteVector.fromArray(SPECIES_PREFERRED, array, offset, getMask(length));
  }

  /**
   * Compares a vector with a pre-computed route vector.
   *
   * @param requestVector the vector from the incoming request path (cannot be null)
   * @param routeVector the pre-computed route vector (cannot be null)
   * @return true if the vectors match, false otherwise
   */
  public static boolean compareVectors(
      @Nonnull final ByteVector requestVector,
      @Nonnull final ByteVector routeVector,
      final int length) {
    return requestVector
            .lanewise(VectorOperators.XOR, routeVector)
            .reduceLanes(VectorOperators.OR, getMask(length))
        == 0;
  }

  /**
   * Compares a vector with a pre-computed route vector.
   *
   * @param requestVector the vector from the incoming request path (cannot be null)
   * @param routeVector the pre-computed route vector (cannot be null)
   * @return true if the vectors match, false otherwise
   */
  public static boolean compareVectors(
      @Nonnull final ByteVector requestVector, @Nonnull final ByteVector routeVector) {
    return requestVector.lanewise(VectorOperators.XOR, routeVector).reduceLanes(VectorOperators.OR)
        == 0;
  }

  /**
   * Creates a vector from a route pattern or route byte array.
   *
   * @param bytes the route pattern or route byte array (cannot be null)
   * @return the created vector
   */
  public static ByteVector createVector(@Nonnull final byte[] bytes) {
    return loadVector(bytes, 0, bytes.length);
  }
}

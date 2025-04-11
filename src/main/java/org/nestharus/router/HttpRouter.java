package org.nestharus.router;

import java.nio.charset.Charset;
import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import jdk.incubator.vector.*;

public class HttpRouter {
  public static void main(final String[] args) {
    final var species = ByteVector.SPECIES_PREFERRED;
    final int byteVectorLength = species.length();
    final VectorMask<Byte>[] masks = (VectorMask<Byte>[]) new VectorMask[byteVectorLength];
    boolean[] maskArray = new boolean[byteVectorLength];
    for (int i = 0; i < byteVectorLength; i++) {
      Arrays.fill(maskArray, 0, i + 1, true);
      Arrays.fill(maskArray, i + 1, byteVectorLength, false);
      masks[i] = VectorMask.fromArray(species, maskArray, 0);
    }
    final String first = "wow there!";
    ByteBuf byteBuf = Unpooled.buffer();
    byteBuf.writeCharSequence(first, Charset.defaultCharset());
    byteBuf.array();
    byteBuf.writerIndex();
    byteBuf.readerIndex();
    ByteVector firstVec =
        ByteVector.fromArray(
            species,
            byteBuf.array(),
            byteBuf.readerIndex(),
            masks[byteBuf.writerIndex() - byteBuf.readerIndex()]);
    ByteVector secondVec =
        ByteVector.fromArray(species, first.getBytes(), 0, masks[first.getBytes().length - 1]);
    boolean areEqual =
        firstVec.lanewise(VectorOperators.XOR, secondVec).reduceLanes(VectorOperators.OR) == 0;
    System.out.println(areEqual);
  }
}

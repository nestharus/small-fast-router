package org.nestharus.router;

import io.vertx.core.http.impl.HttpServerImpl;
import jdk.incubator.vector.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;


public class HttpRouter {
	public static void main(String[] args) {
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
		ByteVector firstVec = ByteVector.fromArray(species, byteBuf.array(), byteBuf.readerIndex(), masks[byteBuf.writerIndex() - byteBuf.readerIndex()]);
		ByteVector secondVec = ByteVector.fromArray(species, first.getBytes(), 0, masks[first.getBytes().length - 1]);
		boolean areEqual = firstVec.lanewise(VectorOperators.XOR, secondVec).reduceLanes(VectorOperators.OR) == 0;
		System.out.println(areEqual);

		// *
		// **
		// *{name}
		// **{name}

		// masks
		// static match -> maxed out up to any type of wildcard
		// segment wildcard -> next static text in reverse by itself (not merged with next segment)
		// path wildcard -> next is static match starting at next segment
		//
 		// segment wildcard has mask ending with 0s
		// path wildcard has mask ending with 0s
		//
		// segment wildcard limit 1 per segment
		// path wildcard limit 1 per route
		//
 		// segment wildcard go to end of segment '/' and then grab max static char length from children (stored)
		// and then grab last N bytes of own segment into SIMD and then match against next children.
		// afterwards static match as normal
		//
 		// path wildcard children from this point on are reversed so start matching from back of url
	}
}

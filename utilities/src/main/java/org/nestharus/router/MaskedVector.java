package org.nestharus.router;

import jdk.incubator.vector.ByteVector;
import lombok.Builder;

@Builder
public record MaskedVector(ByteVector vector, int length) {}

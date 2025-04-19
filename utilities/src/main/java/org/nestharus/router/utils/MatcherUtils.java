package org.nestharus.router.utils;

import javax.annotation.Nonnull;

import jdk.incubator.vector.ByteVector;
import org.nestharus.router.MaskedVector;

public class MatcherUtils {
  /**
   * Attempts to match a static pattern against an input using SIMD operations.
   *
   * @param input The input byte array to match against
   * @param pattern The pattern ByteVector to match
   * @return The number of bytes matched, or -1 if no match
   */
  public static boolean matchSimdStatic(
      @Nonnull final ByteVector input, @Nonnull final MaskedVector pattern) {

    return false;
    // return VectorUtils.compareVectors(input, pattern);
  }

  // class Node {
  //     List<Node> children;
  //     int parentLength;
  //     public Node visit(byte[] request, int currentPosition)
  //
  // parameters are stored by position
  // parameters are stored in a list
  // * is stored as a single
  // ** is stored as a list and is 0 or more
  // compilers maps named parameters from their positions in methods
  // to their positions in routes and creates a table
  // upon calling map parameters to positions by table
  // [ ] groups parameters into a list. these parameters are always anonymous
  // but the grouping can be named
  // groups cannot be put into groups
  // if ** is in a [ ] group then the [ ] group is unbounded
  // all ungrouped parameters must be named
  // *{name}
  // **{name}
  // ungrouped parameters and groups can be optional
  // *{name}?
  // **{name}?
  // [*/*/*]{name} parameter list of 3
  // [*/*/*/**]{name} parameter list of 3 or more
  // [**] parameter list of 0 or more
  // ** parameter list of 0 or more
  // [*/*/*]{name}? optional parameter list of 3
  // only one unbounded list can be present in a route
  // ** represents unbounded segments within a group
  // that group can be at root or within a []
  // this implies that multiple ** can exist within a []
  // [**/*hello*/**]{name} a route that must contain a segment that contains hello
  // [**/hello/**]{name} a route that has a hello segment somewhere
  // [**/hello*/**]{name} a route with a segment that starts with hello
  // [**/*hello/**]{name} a route with a segment that ends with hello
  // ALL segments within the [] will be grouped together as a list
  // At the root level the ** will be its own list
  // **/g/h/i/**
  // if matching /g/h/i/g/h/i then does the extra /g/h/i go into the first ** or the second **?
  // it should go into the second ** reading from left to right
  // the ** matches until it runs across /g/h/i and then stops
  // this means the second /g/h/i goes into the second **
  // between two unbounded lists there must be atleast one static node

  // a/b/c/**/d/e/f/**/g/h/i/**/j/k/l
  // a/b/c/
  // l/k/j/
  //
  // how to match
  // /d/e/f/**/g/h/i

  // RootNode
  // SimdWildcardNode
  // SimdPathWildcardNode
  // SimdStaticNode
  // ByteWildcardNode
  // BytePathWildcardNode
  // ByteStaticNode

  // List<Node> createWildcardChildren
  // List<Node> createPathWildcardChildren
  // Node visitWildcardChildren(byte[] request, int currentPosition, int parentLength)
  // Node visitPathWildcardChildren(byte[] request, int currentPosition, int parentLength)
}

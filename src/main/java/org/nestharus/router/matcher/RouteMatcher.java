package org.nestharus.router.matcher;

import java.util.ArrayDeque;
import java.util.Deque;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import org.nestharus.router.collections.PrefixCompressedTrieNode;
import org.nestharus.router.collections.RouterImage;
import org.nestharus.router.collections.dfa.PureByteDfaSegment;
import org.nestharus.router.collections.trie.DfaTriePointerNode;
import org.nestharus.router.collections.trie.TriePointerNode;

public final class RouteMatcher {

  /* 24‑bit cell layout */
  private static final int FLAG_ARB = 0x800000;
  private static final int MASK_ARBID = 0x7FF000;
  private static final int MASK_RES = 0x000FFF;

  private final RouterImage img;
  private final PureByteDfaSegment dfa;
  private final PrefixCompressedTrieNode[] fast;

  public RouteMatcher(RouterImage img) {
    this.img = img;
    this.dfa = img.dfaSegment();
    this.fast = img.fastRoot();
  }

  /**
   * @return highest priority among all matches, –1 if none.
   */
  public int match(byte[] uri) {
    if (uri.length == 0) return -1;
    Object first = fast[uri[0] & 0xFF];
    if (first == null) return -1;

    /* stack frame definition */
    final class Frame {
      boolean isDfa;
      int stateOff, pos;
      PrefixCompressedTrieNode trie;

      Frame(int off, int p) {
        isDfa = true;
        stateOff = off;
        pos = p;
      }

      Frame(PrefixCompressedTrieNode t, int p) {
        isDfa = false;
        trie = t;
        pos = p;
      }
    }
    Deque<Frame> stack = new ArrayDeque<>();
    stack.push(
        first instanceof PrefixCompressedTrieNode t
            ? new Frame(t, 0)
            : new Frame((PrefixCompressedTrieNode) first, 0));

    int best = -1;
    while (!stack.isEmpty()) {
      Frame f = stack.peek();

      /* ---------- DFA frame ---------- */
      if (f.isDfa) {
        if (f.pos >= uri.length) { // EOS
          byte acc = dfa.accept(f.stateOff);
          if (acc > best) best = acc;
          stack.pop();
          continue;
        }
        /* SIMD skip on sparse state‑0 & long chunk */
        if (f.stateOff == 0
            && uri.length - f.pos >= 32
            && dfa.state0Accepts((byte) uri[f.pos]) == false) {
          f.pos = skipSimd(uri, f.pos);
          if (f.pos >= uri.length) {
            stack.pop();
            continue;
          }
        }
        int ub = uri[f.pos] & 0xFF;
        int cell = dfa.transition(f.stateOff, ub);

        if ((cell & FLAG_ARB) == 0) { // intra‑DFA
          f.stateOff = cell * dfa.stateSize();
          f.pos++;
          if (dfa.accept(f.stateOff) > best) best = dfa.accept(f.stateOff);
          continue;
        }
        /* dispatch to ARB */
        int arbId = (cell & MASK_ARBID) >>> 12;
        int resume = (cell & MASK_RES);
        stack.push(new Frame(img.arbTable().get(arbId - 1), f.pos + 1));
        f.stateOff = resume * dfa.stateSize();
        continue;
      }

      /* ---------- TRIE frame ---------- */
      PrefixCompressedTrieNode n = f.trie;
      int len = n.label.length;
      if (f.pos + len > uri.length) {
        stack.pop();
        continue;
      }
      for (int i = 0; i < len; i++)
        if (uri[f.pos + i] != n.label[i]) {
          stack.pop();
          continue;
        }
      f.pos += len;
      if (n.isTerminal() && n.priority() > best) best = n.priority();
      if (f.pos >= uri.length) {
        stack.pop();
        continue;
      }

      byte next = uri[f.pos];
      Object child = n.child(next);
      if (child == null) {
        stack.pop();
        continue;
      }

      if (child instanceof DfaTriePointerNode(int offset)) {
        stack.push(new Frame(offset, f.pos));
      } else if (child instanceof TriePointerNode(int id)) {
        stack.push(new Frame(img.arbTable().get(id - 1), f.pos));
      } else {
        stack.push(new Frame((PrefixCompressedTrieNode) child, f.pos));
      }
    }
    return best;
  }

  /* SIMD skip helper (state‑0 bitmap pre‑checked outside). */
  private static int skipSimd(byte[] bs, int pos) {
    final int VL = ByteVector.SPECIES_PREFERRED.length();

    // Broadcast constants once
    final ByteVector slash = ByteVector.broadcast(ByteVector.SPECIES_PREFERRED, (byte) '/');
    final ByteVector dash = ByteVector.broadcast(ByteVector.SPECIES_PREFERRED, (byte) '-');

    while (pos + VL <= bs.length) {
      ByteVector vec = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, bs, pos);

      VectorMask<Byte> isSlash = vec.compare(VectorOperators.EQ, slash);
      VectorMask<Byte> isDash = vec.compare(VectorOperators.EQ, dash);

      if (isSlash.or(isDash).anyTrue()) {
        break;
      }

      pos += VL;
    }

    return pos;
  }
}

package org.nestharus.parser;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.nestharus.parser.listener.ListBasedSemanticErrorListener;

public class RouteFullParserTest {
  private List<String> parseInput(final String input) {
    final var charStream = CharStreams.fromString(input);
    final var lexer = new RouteLexer(charStream);
    final var tokens = new CommonTokenStream(lexer);
    final var parser = new RouteParser(tokens);
    final var errorListener = new ErrorTrackingListener();
    final var semanticErrorListener = new ListBasedSemanticErrorListener();
    // final var semanticAnalyzer = new RouteSemanticAnalyzer();
    parser.removeErrorListeners();
    parser.addErrorListener(errorListener);
    // semanticAnalyzer.addErrorListener(semanticErrorListener);
    try {
      // ParseTreeWalker.DEFAULT.walk(semanticAnalyzer, parser.main());
      parser.main(); // Just parse without semantic analysis for now
    } catch (final RuntimeException ignored) {
      // Ignore the exception (stop signal); only capturing errors
    }

    return Stream.of(errorListener.getErrors(), semanticErrorListener.getErrors())
        .flatMap(List::stream)
        .toList();
  }

  @Disabled
  @ParameterizedTest
  @ValueSource(
      strings = {
        // all paths can match with a trailing slash except root
        // all paths can match without a trailing slash
        // all routes can optionally include a trailing slash
        // rules regarding trailing slashes will not be included per route
        // wildcards are not greedy

        // --- Basic Static Routes ---

        // Matches the absolute root path only.
        // def handler()
        "/",

        // Matches "/path"
        // def handler()
        "/path/",

        // Matches "/static/path/segment".
        // def handler()
        "/static/path/segment",

        // Matches "static/path/segment"
        // def handler()
        "/static/path/segment/",

        // --- Optional Elements ---

        // Matches "/optional" or just "/". The 'optional' segment is optional.
        // def handler()
        "/optional?",

        // Matches "/optional" or just "/". The 'optional' segment is optional.
        // def handler()
        "/(optional)?",

        // Matches "/one/two" or "/one". The last segment 'two' is optional.
        // def handler()
        "/one/two?",

        // Matches "/one/two" or "/two". The first segment 'one' is optional.
        // def handler()
        "/one?/two",

        // Matches "/one/two/three" or "/one/three". Middle segment 'two' is optional.
        // def handler()
        "/one/two?/three",

        // --- Alternatives (OR logic) ---

        // Matches "/a", "/b", or "/c". Single segment alternatives.
        // def handler()
        "/a|b|c",

        // Matches "/a", "/b", "/c", or "/". The OR group is optional.
        // def handler()
        "/(a|b|c)?",

        // Matches "/a", "/b", or "/c".
        //
        // OrGroup { OrGroupType type }
        // OrGroupType { TYPE1, TYPE2, TYPE3 }
        // def handler(OrGroup name) {
        //     switch (name.getType()) {
        //         case TYPE1 -> continue;
        //         case TYPE2 -> continue;
        //         case TYPE3 -> continue;
        //     }
        // }
        "/(a|b|c)<name>",

        // Optional group. Matches empty path "", "a", "b", or "c" (relative).
        // nothing is captured because the root group is unnamed! this should issue
        // a warning.
        // def handler()
        "/(a|b|c<name>)?",

        // Optional group. Matches empty path "", "a", "b", or "c" (relative).
        // OrGroup { OrGroupType type, Object object }
        // OrGroupType { TYPE1, TYPE2, TYPE3 }
        // OrGroupObject3 { boolean name }
        // def handler(Optional<OrGroup> name) {
        //     switch (name.getType()) {
        //         case TYPE1 -> continue;
        //         case TYPE2 -> continue;
        //         case TYPE3 -> ((OrGroupObject3) name.getObject()).name;
        //     }
        // }
        "/(a|b|c<name>)<name>?",

        // Optional group followed by "/a". Matches "/a", "/a/a", "/b/a", or "/c/a".
        // nothing is captured because the root group is unnamed! this should issue
        // a warning.
        // def handler()
        "/(a|b|c<name>)?/a",

        // Matches "/a", "/b", or "/c" using explicit groups.
        // Identical to "/a|b|c"
        // def handler()
        "/(a)|(b)|(c)",

        // Matches segments like "aaa", "bbb", or "ccc"
        // OrGroup { OrGroupType type, Object object }
        // OrGroupType { TYPE1, TYPE2, TYPE3 }
        // OrGroupObject1 { String name }
        // OrGroupObject2 { String name }
        // OrGroupObject3 { String name }
        // def handler(OrGroup name) {
        //     switch (name.getType()) {
        //         case TYPE1 -> ((OrGroupObject1) name.getObject()).name;
        //         case TYPE2 -> ((OrGroupObject2) name.getObject()).name;
        //         case TYPE3 -> ((OrGroupObject3) name.getObject()).name;
        //     }
        // }
        "/(a*<name>)|(b*<name>)|(c*<name>)<name>",

        // --- Wildcards (*) ---

        // Matches any single segment with 1 or more characters.
        // def handler()
        "/*",

        // Matches any single segment with 0 or 2+ characters.
        // def handler(String name)
        "/*<name>[2,]?",

        // Matches 0 or 2 or more segments with any number of characters each.
        // def handler(String name)
        "/**<name>[2,]?",

        // Matches a single segment
        // starts with a
        // followed by 1 or more characters until b
        // match b
        // followed by 1 or more characters including b
        // the latter b's get rolled into the second *
        // def handler()
        "/a*b*",

        // Matches a single segment
        // starts with a
        // followed by 1 or more characters until b
        // match b
        // match 3 characters after b
        // latter b's get rolled into the * BUT this can fail matches with try next
        // /adbbccc
        // match a
        // d -> *1
        // match b
        // b -> *2
        // c -> *2
        // c -> *2
        // c FAIL
        // go back to last match
        // go back to last match
        // b -> *1
        // match b
        // c -> *2
        // c -> *2
        // c -> *2
        // MATCH
        // def handler()
        "/a*b*[3]",

        // Matches any single segment 1 or more characters.
        // name cannot be empty
        // def handler(String name)
        "/*<name>",

        // Optional wildcard segment. Matches any single segment with 0 or more characters.
        // name can be empty
        // if a parameter is a sequence the ? will allow for an empty sequence
        // if a parameter is not a sequence the ? will wrap it in an Optional
        // def handler(String name)
        "/*<name>?",

        // Optional wildcard segment. Matches any single segment with 0 or more characters.
        // name can be empty
        // if a parameter is a sequence the ? will allow for an empty sequence
        // if a parameter is not a sequence the ? will wrap it in an Optional
        // def handler(String name)
        "/(*<name>)?",

        // Matches a single segment with 1 or more characters.
        // SegmentObject { String name } // name cannot empty
        // def handler(SegmentObject name)
        //    name.name
        "/(*<name>)<name>",

        // Matches a single OPTIONAL segment with 1 or more characters.
        // GroupObject { String name } // name cannot be empty
        // def handler(Optional<GroupObject> name)
        //    name.name
        "/(<name>*)<name>?",

        // --- Repetition (*) and Globs (**) on Groups ---

        // Matches a single segment with atleast one repeating ab.
        // /ab
        // /abab
        // /ababab
        // def handler()
        "/(ab)*",

        // Matches path with "/a/b" repeated one+ times: "/a/b", "/a/b/a/b".
        // def handler()
        "/(a/b)*",

        // Matches a single segment with 0 or more repeating abc.
        // /
        // /abc
        // /abcabc
        // /abcabcabc
        // def handler()
        "/(abc)*?",

        // Matches path 0 or more times.
        // "/"
        // "/ab/c"
        // "/ab/cab/c".
        // def handler()
        "/(ab/c)*?",

        // Matches path 0 or more times.
        // "/a"
        // "/a/ab/c"
        // "/a/ab/c/ab/c".
        // def handler()
        "/a(/ab/c)*?",

        // Matches a single URL path segment composed of one or more consecutive repetitions of the
        // exact string "abc".
        // The capture '<name>' stores the concatenated strings of matched 'abc'
        // Examples:
        // - Matches "/abc"
        // - Matches "/abcabc"
        // Does not match "/" (zero repetitions).
        // def handler(String name)
        "/(abc)*<name>",

        // Matches a sequence of characters consisting of zero or more repetitions of "ab/c".
        // The capture '<name>' stores the concatenated strings of matched 'ab/c'
        // Examples:
        // - Matches "/"
        // - Matches "/ab/c"
        // - Matches "/ab/cab/c"
        // def handler(String name)
        "/(ab/c)*<name>?",

        // --- Quantifiers ([min,max]) ---

        // Matches a single segment with 1 to 3 repeating abc.
        "/(abc)*[1,3]",

        // Matches a path with 0 or 2 to 3 repeating ab/c.
        // this is not confined to a segment due to the inclusion of /
        // in the pattern.
        // def handler()
        "/(ab/c)*[2,3]?",

        // Matches a single segment with 1 or more repeating abc.
        // def handler()
        "/(abc)*[1,]",

        // Matches a path with 0 or two or more repeating ab/c.
        // def handler()
        "/(ab/c)*[2,]?",

        // Matches a single segment with 0 to 3 repeating abc.
        // def handler()
        "/(abc)*[,3]",

        // Matches a path with 0 to 3 repeating ab/c.
        // def handler()
        "/(ab/c)*[,3]?",

        // Matches a single segment with 1 to 3 repeating abc.
        // The capture '<name>' stores the concatenated strings of matched 'abc'
        // def handler(String name)
        "/(abc)*<name>[1,3]",

        // Matches a path with 0 or 2 to 3 repeating ab/c.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c'
        // def handler(String name)
        "/(ab/c)*<name>[2,3]?",

        // Matches a single segment with 1 or more repeating abc.
        // The capture '<name>' stores the concatenated strings of matched 'abc'
        // def handler(String name)
        "/(abc)*<name>[1,]",

        // Matches a path with 0 or two or more repeating ab/c.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c'
        // def handler(String name)
        "/(ab/c)*<name>[2,]?",

        // Matches a single segment with 0 to 3 repeating abc.
        // // The capture '<name>' stores the concatenated strings of matched 'abc'
        // def handler(String name)
        "/(abc)*<name>[,3]",

        // Matches a path with 0 to 3 repeating ab/c.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c'
        // def handler(String name)
        "/(ab/c)*<name>[,3]?",

        // Matches a single segment containing only a and or b
        // of 1 to 3 characters.
        // The capture '<name>' stores the concatenated strings of matched 'a|b'
        // def handler(String name)
        "/(a|b)*<name>[1,3]",

        // --- Globs (**) ---

        // Matches a path with 1 or more repeating ab joined by '/'.
        // def handler()
        "/(ab)**",

        // Matches a path with 1 or more repeating ab/c joined by '/'.
        // def handler()
        "/(a/b)**",

        // Matches a path with 0 or more repeating abc joined by '/'.
        // def handler()
        "/(abc)**?",

        // Matches a path with 0 or more repeating ab/c joined by '/'.
        // def handler()
        "/(ab/c)**?",

        // Matches a path with 1 or more repeating abc joined by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'abc' joined by '/'
        // def handler(String name);
        "/(abc)**<name>",

        // Matches a path with 0 or more repeating ab/c joined by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c' joined by '/'
        // def handler(String name);
        "/(ab/c)**<name>?",

        // Matches a path with 1 to 3 repeating abc joined by '/'.
        // def handler()
        "/(abc)**[1,3]",

        // Matches a path with 0 or 2 to 3 repeating ab/c joined by '/'.
        // def handler()
        "/(ab/c)**[2,3]?",

        // Matches a path with 1 or more repeating abc joined by '/'.
        // def handler()
        "/(abc)**[1,]",

        // Matches a path with 0 or two or more repeating ab/c joined by '/'.
        // def handler()
        "/(ab/c)**[2,]?",

        // Matches a path with 0 to 3 repeating abc joined by '/'.
        // def handler()
        "/(abc)**[,3]",

        // Matches a path with 0 to 3 repeating ab/c joined by '/'.
        // def handler()
        "/(ab/c)**[,3]?",

        // Matches a path with 1 to 3 repeating abc joined by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'abc' joined by '/'
        // def handler(String name);
        "/(abc)**<name>[1,3]",

        // Matches a path with 0 or 2 to 3 repeating ab/c joined by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c' joined by '/'
        // Examples:
        // - Matches "/"
        // - Matches "/ab/c/ab/c"
        // - Matches "/ab/c/ab/c/ab/c"
        // def handler(String name)
        "/(ab/c)**<name>[2,3]?",

        // Matches a path starting with / followed by 1 or more repetitions of 'abc' joined by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'abc' joined by '/'
        // Examples:
        // - Matches "/abc"
        // - Matches "/abc/abc"
        // - Matches "/abc/abc/abc"
        // ... and so on
        // def handler(String name)
        "/(abc)**<name>[1,]",

        // Matches a path with 0 or 2 or more repeating 'ab/c joined by '/''.
        // The trailing '?' makes the entire repetition construct optional
        // If the repetition is present, '[2,]' requires at least 2 repetitions.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c' joined by '/'
        // Examples:
        // - Matches "/"
        // - Matches "/ab/c/ab/c"
        // - Matches "/ab/c/ab/c/ab/c"
        // ... and so on
        // def handler(String name)
        "/(ab/c)**<name>[2,]?",

        // Matches a path starting with / followed by 0 to 3 repetitions of 'abc' joined by '/'.
        // Because the pattern (abc) is constant and uses the ** repetition operator,
        // each repetition creates a new path segment separated by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'abc' joined by '/'
        // The quantifier [,3] allows zero (min 0 implied) up to a maximum of 3 repetitions.
        // Examples:
        // - Matches "/"
        // - Matches "/abc"
        // - Matches "/abc/abc"
        // - Matches "/abc/abc/abc"
        // Does not match "/abc/abc/abc/abc" (more than 3 repetitions).
        // def handler(String name)
        "/(abc)**<name>[,3]",

        // Matches a path starting with / followed optionally by 2 or 3 repetitions of
        // 'ab/c' joined by '/'.
        // The pattern (ab/c) uses the ** repetition operator, creating segments separated by '/'.
        // The capture '<name>' stores the concatenated strings of matched 'ab/c' joined by '/'
        // The quantifier [2,3] requires exactly 2 or 3 repetitions if the element is present.
        // The trailing '?' makes the entire repetition element optional.
        // If the element is matched, 'name' will be 2 or 3. If the element is omitted, 'name' will
        // be 0.
        // Examples:
        // - Matches "/"
        // - Matches "/ab/c/ab/c"
        // - Matches "/ab/c/ab/c/ab/c"
        // Does not match "/ab/c" (only 1 repetition).
        // def handler(String name)
        "/(ab/c)**<name>[2,3]?",

        // --- Globs in context ---

        // matches any path that ends with a segment hello
        // with 1 or more segments before it
        // def handler()
        "/**/hello",

        // matches any path that ends with a segment hello
        // def handler()
        "/**?/hello",

        // matches any path that starts with a segment hello
        // and ends with a segment hello with 1 or more segments in between
        // def handler()
        "/hello/**/hello",

        // matches any path that starts with a segment hello
        // and ends with a segment hello
        // def handler()
        "/hello/**?/hello",

        // --- Wildcards (*) mixed with static value ---

        // Matches a path with the following segments
        // /path
        // /static*
        // The second segment must start with static and end with one or more
        // characters.
        // Examples:
        // - Matches "/path/staticfoo"
        // - Matches "/path/static-bar_123"
        // Does not match "/path/static" (missing the wildcard).
        // Does not match "/path/static/baz" (wildcard doesn't cross segments).
        // def handler()
        "/path/static*",

        // Matches a path with the following segments:
        // 1. /path
        // 2. /*static
        // The second segment must end with "static" and start with one or more
        // characters.
        // Examples:
        // - Matches "/path/foostatic"
        // - Matches "/path/123static"
        // Does not match "/path/static" (missing the required wildcard part).
        // Does not match "/path/foo/static" (wildcard '*' doesn't cross segments).
        // def handler()
        "/path/*static",

        // Matches a path with the following segments:
        // 1. /path
        // 2. /*?static
        // The second segment must end with "static".
        // Examples:
        // - Matches "/path/static" (optional wildcard part is absent)
        // - Matches "/path/foostatic" (optional wildcard part matches "foo")
        // Does not match "/path/foo/static" (wildcard '*' doesn't cross segments).
        // def handler()
        "/path/*?static",

        // Matches a path with the following segments:
        // 1. /path
        // 2. /static*static
        // The second segment must start with "static", end with "static",
        // and have one or more characters ('*') in between.
        // Examples:
        // - Matches "/path/staticMIDDLEstatic"
        // - Matches "/path/static-123-static"
        // Does not match "/path/staticstatic" (missing the required wildcard part between the
        // literals).
        // Does not match "/path/static/static" (wildcard '*' doesn't cross segments).
        // def handler()
        "/path/static*static",

        // Matches a path containing a single segment matching the expression "hello?*rawr?".
        // This segment:
        // - Can start with "hello" (optional).
        // - Must be followed by one or more characters ('*').
        // - Can end with rawr (optional).
        // Examples:
        // - Matches "/hello_ANYTHING_rawr"
        // - Matches "/_MORE_"
        // Does not match "/hellorawr" (missing the required '*' part).
        // Does not match "/hello/rawr" (expression defines a single segment).
        // def handler()
        "/hello?*rawr?",

        // Matches a path containing a single segment matching the expression "static?*?suffix".
        // This segment:
        // - Can start with "static" (optional).
        // - Can have any number of characters in between.
        // - Must end with "suffix".
        // Examples:
        // - Matches "/suffix" ('static' absent, '*?' absent)
        // - Matches "/staticsuffix" ('static' present, '*?' absent)
        // - Matches "/_ANYTHING_suffix" ('static' absent, '*?' present)
        // - Matches "/static_MORE_suffix" ('static' present, '*?' present)
        // Does not match "/static/suffix" (expression defines a single segment).
        // Does not match "/staticsuffixEXTRA" (extra characters after "suffix").
        // def handler()
        "/static?*?suffix",

        // matches any path that contains hello somewhere
        // def handler()
        "/**?/*hello*/**?",

        // --- Complex Combinations ---

        // Matches a single‑segment consisting ONLY of chars 'a' or 'b'
        // (≥1 char because the group is repeated by *).
        //
        // def handler()
        "/(a|b)*",

        // Either variant‐1  (a*<name1>b*<name2>)  OR variant‑2 (c*<name>)
        // then the whole alternative is captured again by outer <name>.
        //
        // OrGroup { OrGroupType type, Object object }
        // OrGroupType { V1, V2 }
        // V1 { String name1, String name2 }
        // V2 { String name }
        // def handler(OrGroup name) {
        //   switch (name.getType()) {
        //     case V1 -> {
        //        ((V1) name.getObject()).name1;
        //        ((V1) name.getObject()).name2;
        //     }
        //     case V2 -> ((V2) name.getObject()).name;
        //   }
        // }
        "/(a*<name1>b*<name2>|c*<name>)<name>",

        // The most outer ** repeats the preceding pattern ( )
        // across segments. A * applied to a pattern repeats that
        // pattern as is. A ** will prepend that pattern with a /
        // after every repetition. There will be no prepended /
        // on the first application of the pattern.
        // The group within will resolve to one of three variants per
        // repetition
        // 1. (a*<name>)
        // 2. (b*<name3>)
        // 3. ((hello/a/*<price>)**)
        // So one of those 3 groups will be repeated 1 or more times. Each group
        // will be placed into a separate segment.
        // The third variant is a sequence of segments that would be repeated.
        // The ** is unnecessary in the third variant as the behavior is
        // the same without it. However, a ** would be functionally different
        // if that third variant included another OR group inside of it.
        // The third variant will repeat hello/a/* 1 or more times.
        // the second+ time will start in new segments.
        //    /hello/a/*/hello/a/*
        // * and ** repetitions capture cycles in a list. Each cycle is one element
        // of the list.
        // OrGroup { OrGroupType type, Object object }
        // OrGroupType { V1, V2, V3 }
        // OrGroup1 { String name }
        // OrGroup2 { String name3 }
        // OrGroup3 { List<PatternInner> name }
        // PatternInner { String price }
        // def handler(List<OrGroup> name) {
        //  for (OrGroup group : name) {
        //    switch (group.getType()) {
        //      case V1 -> ((OrGroup1) group.getObject()).name;
        //      case V2 -> ((OrGroup2) group.getObject()).name3;
        //      case V3 -> {
        //        for (PatternInner inner : ((OrGroup3) group.getObject()).name) {
        //          inner.price;
        //        }
        //      }
        //    }
        //  }
        // }
        "/(a*<name>|b*<name3>|((hello/a/*<price>)**<name>))**<name>",

        // Repeats literal 'a' across segments, then repeats literal 'b' across
        // segments.
        //
        //   (a)**
        //     • Treats the static group (a) as one *cycle*.
        //     • The first cycle appears in‑place (no leading slash).
        //     • Each subsequent cycle is prefixed with “/” because ** spans
        //       *segments*.
        //     • Therefore it produces paths: "a", "a/a", "a/a/a", …
        //
        //   /(b)**
        //     • Same behaviour but with static 'b' after the mandatory “/”
        //       that separates the two major parts of the route.
        //
        // Overall route matches:
        //
        //   /a/b                    // 1× 'a' segment, 1× 'b' segment
        //   /a/a/b                  // 2× 'a' segments, 1× 'b'
        //   /a/a/a/b/b              // 3× 'a' segments, 2× 'b'
        //   /a/a/b/b/b/b            // etc.
        //
        // No variables are captured (both groups are unnamed), so the handler
        // receives no parameters.
        //
        // def handler()
        "/(a)**/(b)**",

        // ----------------------------------------------------------------------
        //  Single‑segment wildcard repetition
        // ----------------------------------------------------------------------
        //
        //   (a)*  – one‑or‑more consecutive 'a' characters
        //   (b)*  – one‑or‑more consecutive 'b' characters
        //
        // Both groups live in the **same path segment** because * (not **)
        // repeats inside a segment.  No slashes are introduced by either
        // repetition.
        //
        // Valid examples
        //   /ab            ✓   // 1× 'a', 1× 'b'
        //   /aab           ✓   // 2× 'a', 1× 'b'
        //   /aaabbbb       ✓   // 3× 'a', 4× 'b'
        //
        // Invalid examples
        //   /a             ✗   // missing trailing (b)*
        //   /b             ✗   // missing leading (a)*
        //   /ba            ✗   // order must be a* then b*
        //   /a/b           ✗   // slash splits the single segment
        //
        // Neighbouring‑wildcard rule
        //   The two *‑groups are adjacent but **not identical** (one anchors on
        //   'a', the other on 'b'); therefore the “no identical neighbours” rule
        //   is satisfied.
        //
        // No variables are captured, so the generated handler has no parameters.
        //
        // def handler()
        "/(a)*(b)*",

        // ------------------------------------------------------------------
        //  Two‑phase glob with alternative groups
        // ------------------------------------------------------------------
        //
        //   Phase 1 :  (a|b)**        – one‑or‑more "cycles" where each cycle
        //                              is *either* literal 'a' *or* literal
        //                              'b'.  Because ** spans segments, every
        //                              cycle after the first is prefixed by
        //                              "/".
        //
        //   Phase 2 :  /(c|d)**       – mandatory "/" separator followed by
        //                              one‑or‑more cycles of *either* 'c' or
        //                              'd', again distributed across path
        //                              segments by ** semantics.
        //
        // Example matches
        //   /a/c                  ✓  (one 'a', one 'c')
        //   /b/b/b/d/d            ✓  (three 'b', two 'd')
        //   /a/a/b/c/d/d/d        ✓
        //   /c/d                  ✗  (phase‑1 missing)
        //   /a/b                  ✗  (phase‑2 missing)
        //
        // Alternative groups are *unnamed*, so nothing is captured.  The
        // handler therefore has no parameters.
        //
        // def handler()
        "/(a|b)**/(c|d)**",

        // Route: "/(a|b)*(c|d)*"
        // Matches a single segment beginning with '/'.
        // The segment must contain:
        //   1. One or more characters, each being 'a' or 'b' (matched by `(a|b)*`).
        //   2. Immediately followed by one or more characters, each being 'c' or 'd' (matched by
        // `(c|d)*`).
        // Both sequences must be present and contain at least one character.
        // Matching Process: The first group `(a|b)*` matches non-greedily (lazily). It initially
        // matches
        // the minimum required number of 'a's or 'b's (one). The engine then attempts to match the
        // rest of the segment with `(c|d)*` (which also requires at least one 'c' or 'd'). If this
        // fails, the engine backtracks, forcing `(a|b)*` to consume one additional character, and
        // retries.
        //
        // Examples:
        //   - Matches:
        //     - "/ac"    (Split: `a` | `c`)
        //     - "/ad"    (Split: `a` | `d`)
        //     - "/bc"    (Split: `b` | `c`)
        //     - "/bd"    (Split: `b` | `d`)
        //     - "/abcd"  (Split: `ab` | `cd`)
        //     - "/aaacd" (Split: `aaa` | `cd`)
        //   - Does Not Match:
        //     - "/" (Missing both sequences)
        //     - "/a" (Missing the (c|d)* sequence)
        //     - "/c" (Missing the (a|b)* sequence)
        //     - "/ca" ('c' cannot start the segment, must follow 'a' or 'b')
        //     - "/ae" (Contains 'e', not allowed by either group)
        //     - "/a/c" (Matches only a single segment, not multiple)
        //
        // No variables are captured, so the generated handler has no parameters.
        //
        // def handler()
        "/(a|b)*(c|d)*",

        // Route: "/(a|b)*(a|c)*"
        // Matches a single segment beginning with '/'.
        // The segment must contain:
        //   1. One or more characters, each being 'a' or 'b' (matched by `(a|b)*`).
        //   2. Immediately followed by one or more characters, each being 'a' or 'c' (matched by
        // `(a|c)*`).
        // Both sequences must be present and contain at least one character.
        // Matching Process: The first group `(a|b)*` matches non-greedily (lazily). It initially
        // matches
        // the minimum required number of 'a's or 'b's (one). The engine then attempts to match the
        // rest of the segment with `(a|c)*` (which also requires at least one 'a' or 'c'). If this
        // fails because `(a|c)*` cannot consume the remainder, the engine backtracks, forcing
        // `(a|b)*`
        // to consume one additional character ('a' or 'b'), and retries matching the new remainder
        // with `(a|c)*`. This continues until the entire segment is successfully matched or all
        // possibilities are exhausted. Because 'a' is common to both, `(a|b)*` prefers to match
        // fewer 'a's initially, letting `(a|c)*` attempt to match them.
        //
        // Examples:
        //   - Matches:
        //     - "/aa"    (Split: `a` | `a`)
        //     - "/ac"    (Split: `a` | `c`)
        //     - "/ba"    (Split: `b` | `a`)
        //     - "/bc"    (Split: `b` | `c`)
        //     - "/aba"   (Split after backtrack: `ab` | `a`)
        //     - "/aaca"  (Split: `a` | `aca`) // No backtrack needed here
        //     - "/abbac" (Split after backtrack: `abb` | `ac`)
        //   - Does Not Match:
        //     - "/" (Missing both sequences)
        //     - "/a" (Missing the (a|c)* sequence)
        //     - "/b" (Missing the (a|c)* sequence)
        //     - "/c" (Missing the (a|b)* sequence)
        //     - "/bab" (After `(a|b)*` matches `b`, `ab` fails for `(a|c)*`. After backtrack,
        // `(a|b)*` matches `ba`, `b` fails for `(a|c)*`. No match.)
        //     - "/ca" ('c' cannot start the segment, must follow 'a' or 'b')
        //     - "/abd" (Contains 'd', not allowed by either group)
        //     - "/a/a" (Matches only a single segment, not multiple)
        // def handler()
        "/(a|b)*(a|c)*",

        // Route: "/(a)**/**"
        // Matches a path with two distinct parts:
        //   1. First part: One or more segments, each being exactly 'a' (matched by `(a)**`)
        //   2. Second part: One or more segments of any content (matched by `**`)
        // The first part uses the glob operator to repeat 'a' across segments.
        // The second part uses the glob operator to match any remaining segments.
        // These are not considered identical wildcards because (a)** is a specific pattern
        // while ** is a general wildcard - they are different types despite both using the glob
        // operator.
        //
        // Important: Wildcards are not greedy (as noted in line 43). The first glob pattern `(a)**`
        // will match the minimum number of 'a' segments needed (one), and the second glob
        // expression
        // `**`
        // will match the rest of the path.
        //
        // Examples:
        //   - Matches:
        //     - "/a/anything"       (First part: 'a', Second part: 'anything')
        //     - "/a/a/something"    (First part: 'a', Second part: 'a/something') // Non-greedy
        // matching
        //     - "/a/a/a/foo/bar"    (First part: 'a', Second part: 'a/a/foo/bar') // Non-greedy
        // matching
        //   - Does Not Match:
        //     - "/" (Missing both parts)
        //     - "/b/a" ('b' not allowed in first part)
        //     - "/a" (Missing second part)
        //
        // def handler()
        "/(a)**/**",

        // Route: "/(a)**/(a|b)**"
        // Matches a path with two distinct parts separated by a slash:
        //   1. First part: One or more segments, each being exactly 'a' (matched by `(a)**`)
        //   2. Second part: One or more segments, each being either 'a' or 'b' (matched by
        // `(a|b)**`)
        // Both parts use the glob operator to repeat their patterns across segments.
        // These are not considered identical wildcards because they match different patterns.
        //
        // Important: Wildcards are not greedy (as noted in line 43). The first glob pattern `(a)**`
        // will match the minimum number of 'a' segments needed (one), and the second glob pattern
        // `(a|b)**`
        // will match the rest of the path as long as each segment is either 'a' or 'b'.
        //
        // Examples:
        //   - Matches:
        //     - "/a/a"             (First part: 'a', Second part: 'a')
        //     - "/a/b"             (First part: 'a', Second part: 'b')
        //     - "/a/a/a/b"         (First part: 'a', Second part: 'a/a/b') // Non-greedy matching
        //     - "/a/a/a/b/a/b"     (First part: 'a', Second part: 'a/a/b/a/b') // Non-greedy
        // matching
        //   - Does Not Match:
        //     - "/" (Missing both parts)
        //     - "/a" (Missing second part)
        //     - "/b/a" ('b' not allowed in first part)
        //     - "/a/c" ('c' not allowed in second part)
        //
        // def handler()
        "/(a)**/(a|b)**",

        // Route: "/(a|b)**/(a|c)**"
        // Matches a path with two distinct parts separated by a slash:
        //   1. First part: One or more segments, each being either 'a' or 'b' (matched by
        // `(a|b)**`)
        //   2. Second part: One or more segments, each being either 'a' or 'c' (matched by
        // `(a|c)**`)
        // Both parts use the glob operator to repeat their patterns across segments.
        // These are not considered identical wildcards because they match different patterns,
        // even though they share the common alternative 'a'.
        //
        // Important: Wildcards are not greedy (as noted in line 43). The matching process works as
        // follows:
        // 1. The first pattern `(a|b)**` initially tries to match just one segment (non-greedy)
        // 2. The second pattern `(a|c)**` tries to match the rest of the path
        // 3. If the second pattern fails (e.g., encounters a 'b' which it can't match), the engine
        // backtracks
        //    and the first pattern consumes one more segment
        // 4. This process continues until a valid match is found or all possibilities are exhausted
        //
        // Examples:
        //   - Matches:
        //     - "/a/a"             (First part: 'a', Second part: 'a')
        //     - "/a/c"             (First part: 'a', Second part: 'c')
        //     - "/b/a"             (First part: 'b', Second part: 'a')
        //     - "/b/c"             (First part: 'b', Second part: 'c')
        //     - "/a/b/a/c"         (First part: 'a/b', Second part: 'a/c') // Backtracking occurs
        // because 'b' isn't allowed in second part
        //     - "/a/b/b/a/c/a"     (First part: 'a/b/b', Second part: 'a/c/a') // Multiple
        // backtracking steps
        //   - Does Not Match:
        //     - "/" (Missing both parts)
        //     - "/a" (Missing second part)
        //     - "/c/a" ('c' not allowed in first part)
        //     - "/a/b/d" ('d' not allowed in either part)
        //
        // def handler()
        "/(a|b)**/(a|c)**",

        // Matches a path that is either exactly "/" or exactly "/a".
        // The expression "/a<name>?" follows the `Constant<name>?` structure:
        // - The constant static value "a" is made optional by the trailing '?'.
        // Static value is always wrapped in an enum when captured.
        //
        // Examples:
        // - Matches "/" (The optional "a" is absent; 'name' captures empty optional)
        // - Matches "/a" (The optional "a" is present; 'name' captures filled optional)
        //
        // Does not match:
        // - "/aa" (Does not match the required "aa" if anything follows the initial "/")
        // - "/aab" (Contains extra characters after "aa")
        //
        // enum TYPE { TYPE1 }
        // def handler(Optional<TYPE> name)
        "/a<name>?",

        // Matches a path that is either exactly "/" or exactly "/aa".
        // The expression "/aa<name>?" follows the `Constant<name>?` structure:
        // - The constant static value "aa" is made optional by the trailing '?'.
        // Static value is always wrapped in an enum when captured.
        //
        // Examples:
        // - Matches "/" (The optional "aa" is absent; 'name' captures empty optional)
        // - Matches "/aa" (The optional "aa" is present; 'name' captures filled optional)
        //
        // Does not match:
        // - "/a" (Does not match the required "aa" if anything follows the initial "/")
        // - "/aa" (Contains extra characters after "a")
        //
        // enum TYPE { TYPE1 }
        // def handler(Optional<TYPE> name)
        "/aa<name>?",

        // Matches a single segment with 'ab' followed 1 or more characters.
        // def handler()
        "/(ab)(*)",

        // ! is a negation operator. It negates an expression.
        // this would match any single character except a.
        // def handler()
        "/!a",

        // ! is a negation operator. It negates an expression.
        // this would match any single character except a.
        // def handler()
        "/!(a)",

        // ! is a negation operator. It negates an expression.
        // the expression that is negated here is a*
        // this would match any single character except a and emit a warning.
        // def handler()
        "/!(a)*",

        // ! is a negation operator. It negates an expression.
        // the expression that is negated here is a
        // this would match a segment that is 2 or more characters
        // and does not start with a.
        // def handler()
        "/!a*",

        // ! is a negation operator. It negates an expression.
        // the expression that is negated here is a
        // this would match a segment that is 1 or more characters
        // and does not contain a.
        // def handler()
        "/(!a)*",

        // ! is a negation operator. It negates an expression.
        // the expression that is negated here is ab
        // the * is isolated by the surrounding ( ) so it
        // does not apply to a expression. It is standalone.
        // this would match a segment that is 3 or more characters
        // and does not start with ab.
        // def handler()
        "/!(ab)(*)",

        // ! is a negation operator. It negates an expression.
        // the expression that is negated here is (a|b)
        // the * is isolated by the surrounding ( ) so it
        // does not apply to the preceding pattern. It is standalone.
        // this would match a segment that is 2 or more characters
        // and does not start with a or b.
        // def handler()
        "/!(a|b)(*)",

        // static value with ! applied becomes dynamic and as such can be named
        // they are essentially wildcards
        // def handler(String name)
        "/!a<name>",

        // ! is a negation operator. It negates an expression.
        // As such !a is illegal but !a* is legal.
        // This results in a segment with two possible variants.
        // 1. a
        // 2. (!a*<name>?)
        // The second variant is a segment that is 1 or more characters and does
        // not start with a.
        // OrGroup { OrGroupType type, Object object }
        // OrGroupType { TYPE1, TYPE2 }
        // OrGroupObject1 { }
        // OrGroupObject2 { String name }
        // def handler(OrGroup name) {
        //     switch (name.getType()) {
        //         case TYPE1 -> continue;
        //         case TYPE2 -> ((OrGroupObject2) name.getObject()).name;
        //     }
        // }
        "/(a|(!a*<name>?))<name>",

        // PROBLEMATIC ROUTE - Will generate warning
        // Issues:
        // 1. Contains nested * with [,1] quantifier leading to potential infinite loop
        // 2. Inner <name> capture is unused and will generate warning
        // Intended to match a path that does not contain 'a' anywhere and ends with 'cheese'
        // Should be rewritten as: ((!a)*<name>)**/cheese
        // def handler()
        "/(((!a)*[,1])*<name>)**/cheese",

        // PROBLEMATIC ROUTE - Will generate warning
        // Contains nested * with [,1] quantifier leading to potential infinite loop
        // Captures the path wildcard match into 'name' parameter
        // def handler(List<GroupObject> name)
        // Where GroupObject { String name }
        "/(((!a)*[,1])*<name>)**<name>/cheese",

        // Matches a path with multiple segments where:
        // First part: Captures one wildcard segment in first **<name>
        // Second part: Captures remaining segments (before /cheese) in **<name2>
        // Final segment must be 'cheese'
        // def handler(List<GroupObject> name, List<GroupObject2> name2)
        // Where:
        //   GroupObject { String name }
        //   GroupObject2 { String name }
        "/(*<name>)**<name>/(*<name>)**<name2>/cheese",

        // Matches a path where the segment may not start with 'a' and must be at least 2
        // characters.
        // The first character must not be 'a', and remaining characters can be anything.
        // Note: The 'b' in (!a|b) is redundant as it's already included in !a (optimization
        // warning).
        // def handler()
        "/(!a|b)(*)",

        // Matches a path where the segment may not start with 'a' or 'b' and must be at least 2
        // characters.
        // def handler()
        "/!(a|b)(*)",

        // Matches a path that starts with 'a' and ends with 'b'.
        // The ** matches one or more characters and can span across segments.
        // Examples that match:
        //   /acb     (single segment)
        //   /ac/b    (two segments)
        //   /a/c/b   (three segments)
        //   /a/cb    (two segments)
        // def handler()
        "/a**b",

        // Matches a path with exactly two segments, each containing one or more characters.
        // def handler()
        "/*/*",

        // Matches a path that starts with 'a' followed by zero or more segments.
        // The ? after ** makes the path wildcard match zero or more segments (instead of one or
        // more).
        // def handler()
        "/a**?",

        // Matches a path that has zero or more segments followed by a segment 'a'.
        // The ? after ** makes the path wildcard match zero or more segments.
        // def handler()
        "/**?a",

        // Matches a segment starting with 'a' followed by zero or more characters.
        // The ? after * makes the wildcard match zero or more characters (instead of one or more).
        // def handler()
        "/a*?",

        // Matches a segment with zero or more characters followed by 'a'.
        // The ? after * makes the wildcard match zero or more characters.
        // def handler()
        "/*?a",

        // Matches zero or more segments.
        // The ? after ** makes the path wildcard match zero or more segments.
        // def handler()
        "/**?",

        // Matches a single segment containing zero or more characters.
        // The ? after * makes the wildcard match zero or more characters.
        // def handler()
        "/*?",

        // Matches two segments:
        // First segment: Group named 'name', must contain 'a' followed by any characters
        // Second segment: Group named 'name2', must contain 'b' followed by any characters
        // def handler(GroupObject1 name, GroupObject2 name2)
        // Where:
        //   GroupObject1 { String name }
        //   GroupObject2 { String name }
        "/(a*<name>)<name>/(b*<name>)<name2>",

        // Matches one segment:
        // Segment must start with a followed by characters that are not a
        // def handler(GroupObject name)
        // Where:
        //   GroupObject { String name } // name contains sequence characters that are not a
        "/(a(!a)*?<name>)<name>",

        // Matches one segment:
        // Segment has cycles of a followed by a sequence not containing a
        // def handler(List<GroupObject> name)
        // Where:
        //   GroupObject { String name } // name contains sequence characters that are not a
        "/(a(!a)*<name>)*<name>",

        // Matches one segment:
        // Segment has cycles of a followed by a sequence not containing a
        // enum CONST { TYPE1 }
        // GroupObjectInner { String name }
        // GroupObjectOutter { GroupObjectInner name; CONST const }
        // def handler(List<GroupObjectOutter> name)
        "/(a<const>(!a<name>)*<name>)*<name>",

        // Matches one segment:
        // Segment has cycles of a followed by not a sequence
        // def handler(String name)
        "/(a(!a)*)*<name>",

        // Warning: Wildcards of the same type are neighbors in the same scope
        "/**/**",

        // Warning: Wildcards of the same type are neighbors in the same scope
        "/(*)(*)",

        // Warning: Wildcard patterns of the same pattern are neighbors in the same scope
        "/(a)**/(a)**",

        // Warning: Wildcard patterns of the same pattern are neighbors in the same scope
        "/(a)*(a)*",

        // Constant capture
        // enum CapturedConstant { TYPE1 }
        // def handler(CapturedConstant name)
        "/a<name>",

        // Empty capture
        // CapturedObject { }
        // def handler(CapturedObject name)
        "/(a)<name>",

        // Constant capture of constant group
        // enum CapturedConstant { TYPE1 }
        // CapturedObject { CapturedConstant name } // name is always 'a'
        // def handler(CapturedObject name)
        "/(a<name>)<name>",

        // <name> applied to nothing
        "/(<name>*)",

        // matches /a
        "/!!a",

        // matches /!a
        "/!!!a",

        // matches /a
        "/!!!!a",

        // matches /a*
        "/!!a*",

        // matches /a**
        "/!!a**",

        // matches /ab
        "/!!ab",

        // matches /ab*
        "/!!ab*",

        // matches /ab**
        "/!!ab**",

        // matches /a or /!b
        "/!(!a|b)",

        // matches /(a)* or /(!b)*
        "/!(!a|b)*",

        // matches /(a)** or /(!b)**
        "/!(!a|b)**",

        // matches /ab or /!(cd)
        "/!(!ab|cd)",

        // matches /(ab)* or /(!(cd))*
        "/!(!ab|cd)*",

        // matches /ab or /!(cd)
        "/!(!ab|cd)**",

        // matches /(ab)* or /(!(cd))*
        "/!(!ab|cd)*",
        "/(*[5])?",
      })
  public void testValid(final String input) {
    final var errors = parseInput(input);
    assertThat(errors).isEmpty();
  }

  @Disabled
  @ParameterizedTest
  @ValueSource(
      strings = {
        // Invalid: Routes must start with a slash
        "static/path/segment",
        // Invalid: Routes must start with a slash
        "static/path/segment/",

        // unmatchable because !** consumes atleast 1 character that
        // cannot match anything
        "/!*",

        // unmatchable because !** consumes atleast 1 character that
        // cannot match anything
        "/!**",

        // --- Dangling Operators/Tokens ---

        // Invalid: Glob operator '**' cannot be used without a leading slash
        "**",

        // Invalid: Wildcard operator '*' cannot be used without a leading slash
        "*",

        // Invalid: Misplaced optional operator '?' after slash - cannot make slash optional in
        // middle of an expression
        "/a/?a",

        // Invalid: Optional operator '?' cannot be used standalone without a preceding element
        "?",

        // Invalid: Negation operator '!' cannot be used standalone without a leading slash
        "!",

        // Invalid: Negation operator '!' must be followed by an expression to negate
        "/!",

        // Invalid: Unrecognized token '{' - not part of the route syntax
        "/path/{",

        // Invalid: Pipe operator '|' must have elements on both sides
        "/a|",

        // Invalid: Named segment feature removed.
        "/<name>",

        // Invalid: Routes must start with a slash
        "a",

        // Invalid: Quantifier opening bracket '<' cannot be used standalone
        "<",

        // Invalid: Quantifier opening bracket '>' cannot be used standalone
        ">",

        // Invalid: Quantifier opening bracket '[' cannot be used standalone
        "[",

        // Invalid: Quantifier closing bracket ']' cannot be used standalone
        "]",

        // Invalid: Unrecognized token '@' - not part of the route syntax
        "/rawr@",

        // Invalid: Empty group '()' is not allowed
        "/()",

        // Invalid: Empty group '()' is not allowed in alternatives
        "/a|()",

        // Invalid: Empty group '()' is not allowed in alternatives
        "/a|",

        // Invalid: Empty group '()' is not allowed in alternatives
        "/|a",

        // Invalid: Empty alternative between pipes - each alternative must contain an expression
        "/a||b",

        // Invalid: Empty group '()' cannot be negated
        "/!()",

        // --- Misplaced/Invalid Operators/Syntax ---

        // Invalid: Empty segment between consecutive slashes
        "//",

        // Invalid: Multiple optional operators '??' after wildcard - only one is allowed
        "/*??",

        // Invalid: Multiple optional operators '??' after glob - only one is allowed
        "/**??",

        // Invalid: Multiple optional operators '??' after static value - only one is allowed
        "/hello??",

        // Invalid: Unclosed group - missing closing parenthesis
        "/(",

        // Invalid: Unclosed group - missing closing parenthesis
        "/)",

        // Invalid: Quantifier '[1,3]' must be applied to a wildcard element
        "/[1,3]",

        // Invalid: Quantifier '[1,3]' must be applied to a wildcard element
        "/a[1,3]",

        // Invalid: Quantifier '[1,3]' must be applied to a wildcard element
        "/(a)[1,3]",

        // Invalid: Quantifier '[1,3]' must be applied to a wildcard element
        "/a<name>[1,3]",

        // Invalid: Quantifier '[1,3]' must be applied to a wildcard element
        "/a[1,3]?",

        // Invalid: dangling <name>
        "/a?<name>",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/*[1,3]<name>?",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/*<name>?[1,3]",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/*?<name>[1,3]",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        // correct would be /(a)*<name>[1,3]?
        "/(a)[1,3]*<name>?",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/**[1,3]<name>?",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/**<name>?[1,3]",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        "/**?<name>[1,3]",

        // Invalid: must be in order group wildcard quantifier name capture optional
        // group wildcard [ ] <name> ?
        // correct would be /(a)**<name>[1,3]?
        "/(a)[1,3]**<name>?",

        // Invalid: Unclosed quantifier - missing closing bracket
        "/[1,",

        // Invalid: Quantifier values must be integers, not letters
        "/*[a,b]",

        // Invalid: Quantifier minimum (1) cannot be greater than maximum (0)
        "/*[1,0]",

        // Invalid: Quantifier format is [min,max], not [min,mid,max]
        "/*[1,2,3]",

        // --- Duplicate Variable Names (Semantic Issue) ---

        // Invalid: Duplicate variable name collision in the same scope
        // def handler(String name, String name)
        "/a*<name>/b*<name>",

        // Invalid: Duplicate variable name in the same scope
        // GroupObject1 { String name; String name; } // duplicate
        // GroupObject2 { String name }
        // def handler(GroupObject1 name, GroupObject2 name2)
        "/(*<name>a*<name>)**<name>/(*<name>)**<name2>/cheese",

        // Invalid: Without parentheses around the OR group, the second '<name>' creates ambiguity
        // as it becomes part of the last OR group item
        // so you end up with the following /(a)|(b)|(*<name><name>)
        // and now you have sequential <name><name>, which is not legal
        "/a|b|*<name><name>",

        // ILLEGAL named segment feature removed
        "/<name>*<name>",

        // ILLEGAL named segment feature removed
        "/<name>*<name>?",

        // ILLEGAL ! applied to expression with unbounded wildcard
        "/!(a|(b(*)))",

        // ILLEGAL ! applied to expression with unbounded wildcard
        "/!(a|(b(**)))",
        "/*<*>",
        "/*<**>",
        "/*<[1,3]>",
        "/*<(a)>",
        "/*<(a|b)>",
        "/*<(/a)>",
        "/*<(a a)>",
        "/a(<name>)",
        "/*([1])",
        "/*(<name>[1]?)",
        "/*<name>([1])?",
        "/*(?)",
        "/(!)a",
        "/a a",
        "/***",
        "/a//",
      })
  public void testInvalid(final String input) {
    final var errors = parseInput(input);
    assertThat(errors).isNotEmpty();
    // assertEquals(1, errors.size());
  }

  public static class ErrorTrackingListener extends BaseErrorListener {
    private final List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
      return errors;
    }

    @Override
    public void syntaxError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg,
        RecognitionException e) {
      errors.add("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
    }
  }
}

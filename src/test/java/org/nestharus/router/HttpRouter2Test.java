package org.nestharus.router;

import static org.junit.jupiter.api.Assertions.*;

public class HttpRouter2Test {
  //  @Nested
  //  class StaticRoutes {
  //    @Test
  //    void singleStaticText() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/text", null);
  //      assertEquals(List.of("text"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void multipleStaticSegments() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/a/b/c", null);
  //      assertEquals(
  //          List.of("a", "b", "c"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void staticTextWithSpecialCharacters() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/file.txt", null);
  //      assertEquals(List.of("file.txt"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void emptyStaticText() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/", null);
  //      assertEquals(List.of(), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void optionalStaticText() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/optional?", null);
  //      assertEquals(List.of("optional?"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //  }
  //
  //  @Nested
  //  class StarRoutes {
  //    @Test
  //    void plainStar() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/*", null);
  //      assertEquals(List.of("*"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithPrefix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files*", null);
  //      assertEquals(List.of("files*"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithSuffix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/*.txt", null);
  //      assertEquals(List.of("*", ".txt"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithPrefixAndSuffix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files*.txt", null);
  //      assertEquals(
  //          List.of("files*", ".txt"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithOptionalPrefix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files?*", null);
  //      assertEquals(List.of("files*?"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithOptionalSuffix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/*.txt?", null);
  //      assertEquals(
  //          List.of("*", ".txt?"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void starWithOptionalPrefixAndSuffix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files?*.txt?", null);
  //      assertEquals(
  //          List.of("files*?", ".txt?"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void optionalWildcard() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/*?", null);
  //      assertEquals(List.of("*?"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //  }
  //
  //  @Nested
  //  class DoubleStarRoutes {
  //    @Test
  //    void simpleDoubleStar() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/api/**", null);
  //      assertEquals(
  //          List.of("api", "**[0]"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void doubleStarWithPrefix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files/docs**", null);
  //      assertEquals(
  //          List.of("files", "docs**[0]"),
  // router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void doubleStarWithSuffix() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/users/**/settings", null);
  //      assertEquals(
  //          List.of("users", "**[0]", "settings"),
  //          router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void doubleStarWithOptionalAndRequiredUnnamedWildcards() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/path/**[*, *?]", null);
  //      assertEquals(
  //          List.of("path", "**[1]"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //  }
  //
  //  @Nested
  //  class DoubleStarWithVariables {
  //    @Test
  //    void singleRequiredVariable() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/api/**{version}", null);
  //      assertEquals(
  //          List.of("api", "**[1]"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void multipleRequiredVariables() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/files/**[*{path}, *{type}]", null);
  //      assertEquals(
  //          List.of("files", "**[2]"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void requiredAndOptionalVariables() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/users/**[*{id}, *{name}?]", null);
  //      assertEquals(
  //          List.of("users", "**[1]"), router.getNodes().stream().map(Object::toString).toList());
  //    }
  //  }
  //
  //  @Nested
  //  class MixedPatterns {
  //    @Test
  //    void doubleStarAndStarWithVariables() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/api/v1/**{org}/users/*.json", null);
  //      assertEquals(
  //          List.of("api", "v1", "**[1]", "users", "*", ".json"),
  //          router.getNodes().stream().map(Object::toString).toList());
  //    }
  //
  //    @Test
  //    void doubleStarAndStar() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      router.add("/data/**/files/*.txt", null);
  //      assertEquals(
  //          List.of("data", "**[0]", "files", "*", ".txt"),
  //          router.getNodes().stream().map(Object::toString).toList());
  //    }
  //  }
  //
  //  @Nested
  //  class InvalidRoutes {
  //    @Test
  //    void invalidVariableGroup() {
  //      HttpRouter2<HttpParameters> router = new HttpRouter2<>();
  //      assertThrows(IllegalArgumentException.class, () -> router.add("/**{invalid,", null));
  //    }
  //  }
}

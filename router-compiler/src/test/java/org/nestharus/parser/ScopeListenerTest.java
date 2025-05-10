package org.nestharus.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ScopeListenerTest {

  private RouteParser parse(final String route) {
    final var lexer = new RouteLexer(CharStreams.fromString(route));
    final var tokens = new CommonTokenStream(lexer);

    return new RouteParser(tokens);
  }
}

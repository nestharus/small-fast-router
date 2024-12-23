package org.nestharus.router;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.ArrayList;
import java.util.List;

public class HttpRouter2<T extends HttpParameters> {
	public interface RouteNode {
		String toString();
	}

	public static class StaticNode implements RouteNode {
		final String text;
		final boolean optional;

		StaticNode(String text, boolean optional) {
			this.text = text;
			this.optional = optional;
		}

		@Override
		public String toString() {
			return text + (optional ? "?" : "");
		}
	}

	public static class StarNode implements RouteNode {
		final String prefix;
		final boolean optional;

		StarNode(String prefix, boolean optional) {
			this.prefix = prefix;
			this.optional = optional;
		}

		@Override
		public String toString() {
			return prefix + "*" + (optional ? "?" : "");
		}
	}

	public static class DoubleStarNode implements RouteNode {
		final String prefix;
		final int minLength;
		DoubleStarNode(String prefix, int minLength) {
			this.prefix = prefix;
			this.minLength = minLength;
		}

		@Override
		public String toString() {
			return prefix + "**[" + minLength + "]";
		}
	}

	private final List<RouteNode> nodes = new ArrayList<>();

	public List<RouteNode> getNodes() {
		return nodes;
	}

	public T get(final String uri) {
		return null;
	}

	public void add(final String route, final T handler) {
		CharStream cs = CharStreams.fromString(route);
		RouteLexer lexer = new RouteLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RouteParser parser = new RouteParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			                        int line, int charPositionInLine,
			                        String msg, RecognitionException e) {
				throw new IllegalArgumentException("Invalid route syntax: " + msg);
			}
		});

		ParseTree tree = parser.main();

		new RouteParserBaseVisitor<Void>() {
			@Override
			public Void visitSegment(RouteParser.SegmentContext ctx) {
				if (ctx.static_segment() != null) {
					RouteParser.Static_segmentContext staticSegment = ctx.static_segment();
					nodes.add(new StaticNode(
						staticSegment.STATIC_TEXT().getText(),
						staticSegment.OPTIONAL() != null
					));
				}
				return super.visitSegment(ctx);
			}

			@Override
			public Void visitWildcard_segment(RouteParser.Wildcard_segmentContext ctx) {
				List<RouteParser.Static_segmentContext> staticSegments = ctx.static_segment();

				// If there's static text before the wildcard, it's a prefix
				if (!staticSegments.isEmpty() && staticSegments.get(0).getStart().getStartIndex() < ctx.wildcard().getStart().getStartIndex()) {
					nodes.add(new StarNode(staticSegments.get(0).STATIC_TEXT().getText(),
						staticSegments.get(0).OPTIONAL() != null));
				} else {
					// No prefix
					nodes.add(new StarNode("", ctx.wildcard().OPTIONAL() != null));
				}

				// If there's static text after the wildcard, it's a suffix
				if (!staticSegments.isEmpty() && staticSegments.get(0).getStart().getStartIndex() > ctx.wildcard().getStop().getStopIndex()) {
					nodes.add(new StaticNode(staticSegments.get(0).STATIC_TEXT().getText(),
						staticSegments.get(0).OPTIONAL() != null));
				} else if (staticSegments.size() > 1) {
					nodes.add(new StaticNode(staticSegments.get(1).STATIC_TEXT().getText(),
						staticSegments.get(1).OPTIONAL() != null));
				}

				return null;
			}

			@Override
			public Void visitGlob_segment(RouteParser.Glob_segmentContext ctx) {
				String prefix = "";
				if (ctx.STATIC_TEXT() != null) {
					prefix = ctx.STATIC_TEXT().getText();
				}

				int minLength = 0;
				// Check for single varname
				if (ctx.varname() != null) {
					minLength = 1; // Single variable name counts as 1 required variable
				}
				// Check for varname_group
				else if (ctx.varname_group() != null) {
					RouteParser.Var_listContext varList = ctx.varname_group().var_list();

					// Count non-optional unnamed wildcards
					minLength += varList.unnamed_wildcard().stream()
						.filter(w -> w.OPTIONAL() == null)
						.count();

					// Count non-optional named wildcards
					minLength += varList.named_wildcard().stream()
						.filter(w -> w.OPTIONAL() == null)
						.count();
				}

				nodes.add(new DoubleStarNode(prefix, minLength));
				return null;
			}
		}.visit(tree);
	}
}
// Generated from C:/Users/xteam/IdeaProjects/small-fast-router/src/main/resources/org/nestharus/router/RouteParser.g4 by ANTLR 4.13.2
package org.nestharus.router;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RouteParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RouteParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RouteParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(RouteParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#url}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrl(RouteParser.UrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#segments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegments(RouteParser.SegmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(RouteParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#wildcard_segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard_segment(RouteParser.Wildcard_segmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#wildcard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(RouteParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#glob_segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlob_segment(RouteParser.Glob_segmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#varname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarname(RouteParser.VarnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#varname_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarname_group(RouteParser.Varname_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#var_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_list(RouteParser.Var_listContext ctx);
}
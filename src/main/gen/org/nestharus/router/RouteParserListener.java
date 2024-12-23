// Generated from C:/Users/xteam/IdeaProjects/small-fast-router/src/main/resources/org/nestharus/router/RouteParser.g4 by ANTLR 4.13.2
package org.nestharus.router;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RouteParser}.
 */
public interface RouteParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RouteParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(RouteParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(RouteParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#url}.
	 * @param ctx the parse tree
	 */
	void enterUrl(RouteParser.UrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#url}.
	 * @param ctx the parse tree
	 */
	void exitUrl(RouteParser.UrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(RouteParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(RouteParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#static_segment}.
	 * @param ctx the parse tree
	 */
	void enterStatic_segment(RouteParser.Static_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#static_segment}.
	 * @param ctx the parse tree
	 */
	void exitStatic_segment(RouteParser.Static_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#wildcard_segment}.
	 * @param ctx the parse tree
	 */
	void enterWildcard_segment(RouteParser.Wildcard_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#wildcard_segment}.
	 * @param ctx the parse tree
	 */
	void exitWildcard_segment(RouteParser.Wildcard_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#glob_segment}.
	 * @param ctx the parse tree
	 */
	void enterGlob_segment(RouteParser.Glob_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#glob_segment}.
	 * @param ctx the parse tree
	 */
	void exitGlob_segment(RouteParser.Glob_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#varname}.
	 * @param ctx the parse tree
	 */
	void enterVarname(RouteParser.VarnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#varname}.
	 * @param ctx the parse tree
	 */
	void exitVarname(RouteParser.VarnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(RouteParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(RouteParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#unnamed_wildcard}.
	 * @param ctx the parse tree
	 */
	void enterUnnamed_wildcard(RouteParser.Unnamed_wildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#unnamed_wildcard}.
	 * @param ctx the parse tree
	 */
	void exitUnnamed_wildcard(RouteParser.Unnamed_wildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#named_wildcard}.
	 * @param ctx the parse tree
	 */
	void enterNamed_wildcard(RouteParser.Named_wildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#named_wildcard}.
	 * @param ctx the parse tree
	 */
	void exitNamed_wildcard(RouteParser.Named_wildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#varname_group}.
	 * @param ctx the parse tree
	 */
	void enterVarname_group(RouteParser.Varname_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#varname_group}.
	 * @param ctx the parse tree
	 */
	void exitVarname_group(RouteParser.Varname_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#var_list}.
	 * @param ctx the parse tree
	 */
	void enterVar_list(RouteParser.Var_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#var_list}.
	 * @param ctx the parse tree
	 */
	void exitVar_list(RouteParser.Var_listContext ctx);
}
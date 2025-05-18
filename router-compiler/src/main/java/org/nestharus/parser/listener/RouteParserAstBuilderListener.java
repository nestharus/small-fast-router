package org.nestharus.parser.listener;

import java.util.*;

import org.antlr.v4.runtime.Token;
import org.nestharus.parser.AstBuilderErrorHandler;
import org.nestharus.parser.RouteParser;
import org.nestharus.parser.RouteParserBaseListener;
import org.nestharus.parser.mapper.PatternNodeMapper;
import org.nestharus.parser.mapper.SourceNodeMapper;
import org.nestharus.parser.mapper.TextNodeMapper;
import org.nestharus.parser.mapper.WildcardNodeMapper;
import org.nestharus.parser.value.*;

public class RouteParserAstBuilderListener extends RouteParserBaseListener {
  private RootNode rootNode;
  private IdentityHashMap<Object, List<Token>> tokenMap;
  private final ParserGroupContext groupContext;
  private final AstBuilderErrorHandler errorHandler;

  public RouteParserAstBuilderListener() {
    groupContext = new ParserGroupContext();
    errorHandler = new AstBuilderErrorHandler();
    tokenMap = new IdentityHashMap<>();
  }

  public void addErrorListener(final SemanticErrorListener listener) {
    errorHandler.addErrorListener(listener);
  }

  public RootNode getRootNode() {
    return rootNode;
  }

  public IdentityHashMap<Object, List<Token>> getTokenMap() {
    return tokenMap;
  }

  @Override
  public void enterMain(final RouteParser.MainContext ctx) {
    groupContext.push();
  }

  @Override
  public void exitMain(final RouteParser.MainContext ctx) {
    rootNode = new RootNode(groupContext.pop());
  }

  @Override
  public void enterGroupExpression(final RouteParser.GroupExpressionContext ctx) {
    groupContext.push();
  }

  @Override
  public void exitGroupExpression(final RouteParser.GroupExpressionContext ctx) {
    errorHandler.handle(
        () -> {
          final var children = groupContext.pop();
          final var node = PatternNodeMapper.fromGroupExpressionContext(ctx, children);

          groupContext.add(node);
        });
  }

  @Override
  public void enterStarPattern(final RouteParser.StarPatternContext ctx) {
    groupContext.push();
  }

  @Override
  public void exitStarPattern(final RouteParser.StarPatternContext ctx) {
    errorHandler.handle(
        () -> {
          final var children = groupContext.pop();
          final var node = PatternNodeMapper.fromStarPatternContext(ctx, children);

          groupContext.add(node);
        });
  }

  @Override
  public void enterBranchExpression(final RouteParser.BranchExpressionContext ctx) {
    groupContext.push();
  }

  @Override
  public void exitBranchExpression(final RouteParser.BranchExpressionContext ctx) {
    final var children = groupContext.pop();
    final var node =
        new BranchExpressionNode(children.stream().map(BranchNode.class::cast).toList());

    groupContext.add(node);
  }

  @Override
  public void enterBranch(final RouteParser.BranchContext ctx) {
    groupContext.push();
  }

  @Override
  public void exitBranch(final RouteParser.BranchContext ctx) {
    final var children = groupContext.pop();
    final var node = new BranchNode(children);

    groupContext.add(node);
  }

  @Override
  public void enterTextExpression(final RouteParser.TextExpressionContext ctx) {
    errorHandler.handle(
        () -> {
          final var node = TextNodeMapper.fromTextExpressionContext(ctx);

          groupContext.add(node);
        });
  }

  @Override
  public void enterStarExpression(final RouteParser.StarExpressionContext ctx) {
    errorHandler.handle(
        () -> {
          final var node = WildcardNodeMapper.fromStarExpression(ctx);

          groupContext.add(node);
        });
  }

  @Override
  public void enterSlash(final RouteParser.SlashContext ctx) {
    final var token = ctx.SLASH().getSymbol();
    final var source = SourceNodeMapper.fromToken(token);
    final var node = new SlashNode(Optional.of(source));

    groupContext.add(node);
  }
}

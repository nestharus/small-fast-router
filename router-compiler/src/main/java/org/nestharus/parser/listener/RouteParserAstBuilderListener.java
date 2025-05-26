package org.nestharus.parser.listener;

import java.util.*;

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
  private final ParserGroupContext groupContext;
  private final AstBuilderErrorHandler errorHandler;

  public RouteParserAstBuilderListener() {
    groupContext = new ParserGroupContext();
    errorHandler = new AstBuilderErrorHandler();
  }

  public void addErrorListener(final SemanticErrorListener listener) {
    errorHandler.addErrorListener(listener);
  }

  public RootNode getRootNode() {
    return rootNode;
  }

  @Override
  public void enterMain(final RouteParser.MainContext context) {
    groupContext.push();
  }

  @Override
  public void exitMain(final RouteParser.MainContext context) {
    rootNode = new RootNode(groupContext.pop());
  }

  @Override
  public void enterGroupExpression(final RouteParser.GroupExpressionContext context) {
    groupContext.push();
  }

  @Override
  public void exitGroupExpression(final RouteParser.GroupExpressionContext context) {
    errorHandler.handle(
        () -> {
          final var children = groupContext.pop();
          final var node = PatternNodeMapper.fromGroupExpressionContext(context, children);

          groupContext.add(node);
        });
  }

  @Override
  public void enterStarPattern(final RouteParser.StarPatternContext context) {
    groupContext.push();
  }

  @Override
  public void exitStarPattern(final RouteParser.StarPatternContext context) {
    errorHandler.handle(
        () -> {
          final var children = groupContext.pop();
          final var node = PatternNodeMapper.fromStarPatternContext(context, children);

          groupContext.add(node);
        });
  }

  @Override
  public void enterBranchExpression(final RouteParser.BranchExpressionContext context) {
    groupContext.push();
  }

  @Override
  public void exitBranchExpression(final RouteParser.BranchExpressionContext context) {
    final var children = groupContext.pop();
    final var node =
        new BranchExpressionNode(children.stream().map(BranchNode.class::cast).toList());

    groupContext.add(node);
  }

  @Override
  public void enterBranch(final RouteParser.BranchContext context) {
    groupContext.push();
  }

  @Override
  public void exitBranch(final RouteParser.BranchContext context) {
    final var children = groupContext.pop();
    final var node = new BranchNode(children);

    groupContext.add(node);
  }

  @Override
  public void enterTextExpression(final RouteParser.TextExpressionContext context) {
    errorHandler.handle(
        () -> {
          final var node = TextNodeMapper.fromTextExpressionContext(context);

          groupContext.add(node);
        });
  }

  @Override
  public void enterStarExpression(final RouteParser.StarExpressionContext context) {
    errorHandler.handle(
        () -> {
          final var node = WildcardNodeMapper.fromStarExpression(context);

          groupContext.add(node);
        });
  }

  @Override
  public void enterSlash(final RouteParser.SlashContext context) {
    final var token = context.SLASH().getSymbol();
    final var source = SourceNodeMapper.fromToken(token);
    final var node = new SlashNode(Optional.of(source));

    groupContext.add(node);
  }
}

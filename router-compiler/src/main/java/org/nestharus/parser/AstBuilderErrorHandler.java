package org.nestharus.parser;

import java.util.ArrayList;
import java.util.List;

import org.nestharus.parser.exception.AstBuildException;
import org.nestharus.parser.exception.TokenMapperException;
import org.nestharus.parser.listener.SemanticErrorListener;

public class AstBuilderErrorHandler {
  public interface Function {
    void run() throws TokenMapperException;
  }

  private final List<SemanticErrorListener> errorListeners;

  public AstBuilderErrorHandler() {
    errorListeners = new ArrayList<>();
  }

  public void addErrorListener(final SemanticErrorListener listener) {
    errorListeners.add(listener);
  }

  public void handle(final Function toHandle) {
    try {
      toHandle.run();
    } catch (final TokenMapperException tokenMapperException) {
      errorListeners.forEach(
          listener ->
              listener.reportSemanticError(
                  tokenMapperException.getMessage(), tokenMapperException.getTokens().get(0)));

      throw new AstBuildException();
    }
  }
}

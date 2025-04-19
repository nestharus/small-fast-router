package org.nestharus.router;

import java.util.concurrent.CountDownLatch;

import javax.annotation.Nonnull;

import io.vertx.core.*;
import jdk.incubator.vector.ByteVector;
import org.nestharus.router.utils.StringAccessUtils;
import org.nestharus.router.utils.VectorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Server {
  private static final Logger logger = LoggerFactory.getLogger(Server.class);
  private static final String route = "/5000";
  private static final ByteVector ROUTE_5000_VECTOR =
      VectorUtils.createVector(StringAccessUtils.getStringBytes(route));

  // Private constructor to prevent instantiation
  private Server() {}

  public static synchronized void main(@Nonnull final String[] args) throws InterruptedException {
    final var vertx = Vertx.vertx();
    final var server = vertx.createHttpServer();

    server.requestHandler(
        request -> {
          final var uriBytes = StringAccessUtils.getStringBytes(request.uri());
          final var requestVector = VectorUtils.createVector(uriBytes);
          final var areEqual = VectorUtils.compareVectors(requestVector, ROUTE_5000_VECTOR);

          // Debug output
          logger.debug("Request URI: {}", request.uri());
          logger.debug("Route: /5000");

          final var equal = areEqual ? "YOU ARE AT /5000" : "YOU ARE NOT AT /5000";

          request.absoluteURI();
          request.response().putHeader("content-type", "text/plain").end(equal);
        });

    final var latch = new CountDownLatch(1);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  server
                      .close()
                      .andThen(
                          ar -> {
                            if (ar.succeeded()) {
                              logger.info("Server shut down successfully");
                            } else {
                              logger.error(
                                  "Failed to shut down server: {}", ar.cause().getMessage());
                            }
                            latch.countDown();
                          });
                }));

    server.listen();

    latch.await();
  }
}

package org.nestharus.router;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;

public class Server {
  private static final VarHandle STRING_VALUE_HANDLE;
  private static final VarHandle STRING_CODER_HANDLE;

  static {
    try {
      // Get VarHandles for the String class fields
      STRING_VALUE_HANDLE =
          MethodHandles.privateLookupIn(String.class, MethodHandles.lookup())
              .findVarHandle(String.class, "value", byte[].class);
      STRING_CODER_HANDLE =
          MethodHandles.privateLookupIn(String.class, MethodHandles.lookup())
              .findVarHandle(String.class, "coder", byte.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static synchronized void main(String[] args) throws InterruptedException {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();

    final var species = ByteVector.SPECIES_PREFERRED;
    final int byteVectorLength = species.length();
    final VectorMask<Byte>[] masks = (VectorMask<Byte>[]) new VectorMask[byteVectorLength];
    boolean[] maskArray = new boolean[byteVectorLength];
    for (int i = 0; i < byteVectorLength; i++) {
      Arrays.fill(maskArray, 0, i + 1, true);
      Arrays.fill(maskArray, i + 1, byteVectorLength, false);
      masks[i] = VectorMask.fromArray(species, maskArray, 0);
    }

    server.requestHandler(
        request -> {
          // handle ? for query param (input from client)
          // handle ? for url params (input from route definition)
          // handle trailing /
          // parameter names aren't passed back. just a list of parameters
          // each parameter can be a scaler or a list
          // *
          // *{name}
          // **
          // **{name}
          // **[*] // atleast 1 parameter
          // **{name}[*{name1}, *{name2}, *, *] // atleast 4 parameters
          //
          // * can be optional
          // only the last *'s in a ** can be optional. A middle * cannot.
          //
          // *?
          // *{name}?
          // **[*?] // same as **
          // **{name}[*{name1}, *{name2}, *?, *?] (same as **{name}[*{name1}, *{name2}])
          // **{name}[*{name1}, *{name2}?, *?, *?] (same as **{name}[*{name1}]) BUT allows naming of
          // param #2
          final byte[] value = (byte[]) STRING_VALUE_HANDLE.get(request.uri());
          final String first = "/5000";
          final byte[] value2 = first.getBytes(StandardCharsets.UTF_8);
          System.out.println(new String(value, StandardCharsets.UTF_8));
          System.out.println(new String(value2, StandardCharsets.UTF_8));
          final var vec = ByteVector.fromArray(species, value, 0, masks[value.length - 1]);
          final var vec2 = ByteVector.fromArray(species, value2, 0, masks[value2.length - 1]);
          boolean areEqual =
              vec.lanewise(VectorOperators.XOR, vec2).reduceLanes(VectorOperators.OR) == 0;
          String equal = areEqual ? "YOU ARE AT /5000" : "YOU ARE NOT AT /5000";

          request.absoluteURI();
          request.response().putHeader("content-type", "text/plain").end(equal);
        });

    CountDownLatch latch = new CountDownLatch(1);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  server
                      .close()
                      .andThen(
                          ar -> {
                            if (ar.succeeded()) {
                              System.out.println("Server shut down successfully");
                            } else {
                              System.err.println("Failed to shut down server: " + ar.cause());
                            }
                            latch.countDown();
                          });
                }));

    server.listen();

    latch.await();
  }
}

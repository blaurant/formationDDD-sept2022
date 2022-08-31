package com.restaurant.infrastructure.rest;

import com.restaurant.application.TableService;
import com.restaurant.domain.Table;
import com.restaurant.domain.Tables;
import com.restaurant.infrastructure.repository.InMemoryTableRepo;
import io.vavr.collection.HashSet;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(VertxExtension.class)
public class TableRestTest {
//
//    private Vertx vertx = Vertx.vertx();
//    private int port = 16969;
//
//    private static final Tables tables = new Tables(HashSet.of(
//            Table.of(1, 2),
//            Table.of(2, 6),
//            Table.of(3, 4),
//            Table.of(4, 2),
//            Table.of(5, 2)));
////
////    @Before
////    public void setUp(TestContext context) {
////        TableService tableService = new TableService(new InMemoryTableRepo(tables));
////        new RestService(vertx, tableService, port).start();
////    }
////
////    @After
////    public void tearDown(TestContext context) {
////        vertx.close(context.asyncAssertSuccess());
////    }
//
//    @Test
//    public void start_http_server() throws Throwable {
//        VertxTestContext testContext = new VertxTestContext();
//
//        vertx.createHttpServer()
//                .requestHandler(req -> req.response().end())
//                .listen(port)
//                .onComplete(testContext.succeedingThenComplete());
//
//        assertThat(testContext.awaitCompletion(5, TimeUnit.SECONDS)).isTrue();
//        if (testContext.failed()) {
//            throw testContext.causeOfFailure();
//        }
//    }
//
//    @Test
//    public void testPlaceFreeSet() throws InterruptedException {
//
//        TableService tableService = new TableService(new InMemoryTableRepo(tables));
//        new RestService(vertx, tableService, port).start();
//
//        WebClient webClient = WebClient.create(vertx);
//        webClient.get(port, "localhost", "/tables/").send()
//                .onComplete(response -> System.out.println("Received response with status code"))
//                .onFailure(err -> System.out.println("Something went wrong " + err.getMessage()));
//
//    }
}

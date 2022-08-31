package com.restaurant.infrastructure.rest;

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

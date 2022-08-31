package com.restaurant.infrastructure.rest;

import com.restaurant.application.TableService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import static com.restaurant.infrastructure.rest.TableAdaptor.*;
import static java.lang.Integer.parseInt;

public class RestService extends AbstractVerticle {

    private final TableService tableService;
    private final int port;

    public RestService(Vertx vertx, TableService tableService, int port) {
        this.vertx = vertx;
        this.port = port;
        this.tableService = tableService;
    }

    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.get("/tables/")
                .respond(ctx -> Future.succeededFuture(
                        tablesToDto(tableService.getAllTables())));
        router.get("/tables/:tableNumber")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.getTable(tableNumber(ctx.pathParam("tableNumber"))))));
        router.get("/tables/place/:numberOfCustomers")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.place(parseInt(ctx.pathParam("numberOfCustomers"))))));
        router.get("/tables/free/:tableNumber")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.freeTable(tableNumber(ctx.pathParam("tableNumber"))))));
        router.get("/tables/set/:tableNumber")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.setTable(tableNumber(ctx.pathParam("tableNumber"))))));
        server.requestHandler(router).listen(port);
    }
}
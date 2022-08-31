package com.restaurant.infrastructure.rest;

import com.restaurant.application.TableService;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableNumber;
import com.restaurant.domain.Tables;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.List;

import static java.lang.Integer.parseInt;

public class RestService extends AbstractVerticle {

    private final TableService tableService;

    public RestService(Vertx vertx, TableService tableService) {
        this.vertx = vertx;
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
                        tableToDto(tableService.getTable(new TableNumber(parseInt(ctx.pathParam("tableNumber")))))));
        router.get("/tables/place/:numberOfCustomers")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.place(parseInt(ctx.pathParam("numberOfCustomers"))))));
        router.get("/tables/free/:tableNumber")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.freeTable(new TableNumber(parseInt(ctx.pathParam("tableNumber")))))));
        router.get("/tables/set/:tableNumber")
                .respond(ctx -> Future.succeededFuture(
                        tableToDto(tableService.setTable(new TableNumber(parseInt(ctx.pathParam("tableNumber")))))));
        server.requestHandler(router).listen(8080);
    }

    private List<TableDto> tablesToDto(Tables tables) {
        return tables.tables.map(t-> tableToDto(t)).toJavaList();
    }

    private static TableDto tableToDto(Table table) {
        return new TableDto(table);
    }
}
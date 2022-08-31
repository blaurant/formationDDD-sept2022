package com.restaurant;

import com.restaurant.application.TableService;
import com.restaurant.domain.Table;
import com.restaurant.domain.Tables;
import com.restaurant.infrastructure.repository.InMemoryTableRepo;
import com.restaurant.infrastructure.rest.RestService;
import io.vavr.collection.HashSet;
import io.vertx.core.Vertx;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        TableService tableService = new TableService(new InMemoryTableRepo(tables));
        new RestService(vertx, tableService).start();
    }

    private static final Tables tables = new Tables(HashSet.of(
                Table.of(1, 2),
                Table.of(2, 6),
                Table.of(3, 4),
                Table.of(4, 2),
                Table.of(5, 2)));
}

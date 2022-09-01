package com.restaurant.application;

import com.restaurant.domain.Meal;
import com.restaurant.domain.Order;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableFreedEvent;
import com.restaurant.infrastructure.bus.TableEndPoint;
import com.restaurant.infrastructure.bus.VertxBus;
import com.restaurant.infrastructure.repository.InMemoryMealRepo;
import com.restaurant.infrastructure.repository.InMemoryTableRepo;
import io.vertx.core.Vertx;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static com.restaurant.domain.Meals.noMeals;
import static com.restaurant.domain.Tables.noTables;
import static com.restaurant.domain.TablesTest.createTables;

public class AggregatesInteractionTest {

    @Test
    public void payMailToTableFreed() {
        Vertx vertx = Vertx.vertx();
        VertxBus bus = VertxBus.createVertxBusWithGenCodec(vertx);
        MealService mealService = new MealService(new InMemoryMealRepo(noMeals), bus);
        TableService tableService = new TableService(new InMemoryTableRepo(noTables), bus);
        tableService.setupRestaurantHall(createTables());
        TableEndPoint tableEndPoint = new TableEndPoint(bus, tableService);
        tableEndPoint.start();

        Table table = tableService.place(2);
        Meal.Id mealId = mealService.order(table.getId(), new Order(table.getId(), "pizza"));
        mealService.readyOrder(mealId);
        mealService.serveOrder(mealId);
        mealService.billPlease(mealId);

        CompletableFuture<TableFreedEvent> comp = new CompletableFuture<>();
        bus.consume(Table.TABLE_CHANNEL, o -> {
            if (o instanceof TableFreedEvent) {
                TableFreedEvent tableFreedEvent = (TableFreedEvent) o;
                comp.complete(tableFreedEvent);
            }
        });
        mealService.pay(mealId);

        comp.whenComplete((result, e) -> {
            tableService.setTable(table.getId());
            Assertions.assertThat(tableService.loadByTableNumber(table.number()).get().state())
                    .isEqualTo(Table.State.SET);
        });
    }
}

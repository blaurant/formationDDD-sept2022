package com.restaurant.infrastructure.bus;

import DDD.framework.Bus;
import com.restaurant.application.TableService;
import com.restaurant.domain.MealPaidEvent;
import com.restaurant.domain.TableNumber;

import static com.restaurant.domain.Meal.MEAL_CHANNEL;

public class TableEndPoint {

    private final TableService tableService;
    private final Bus bus;

    public TableEndPoint(Bus bus, TableService tableService) {
        this.bus = bus;
        this.tableService = tableService;
    }

    public void start() {
        reactTo(MEAL_CHANNEL);
    }

    private void reactTo(String subject) {
        bus.consume(subject, obj -> eventToCommand(subject, obj));
    }

    private void eventToCommand(String subject, Object obj) {
        if (subject.equals(MEAL_CHANNEL)) {
            MealPaidEvent mealPaidEvent = (MealPaidEvent) obj;
            TableNumber tableNumber = new TableNumber(mealPaidEvent.tableNumber);
            tableService.freeTable(tableNumber);
        }
    }
}

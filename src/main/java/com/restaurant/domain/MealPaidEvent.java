package com.restaurant.domain;

import DDD.framework.DomainEvent;

import static com.restaurant.domain.Meal.MEAL_CHANNEL;

public class MealPaidEvent implements DomainEvent {


    public final String mealId;
    public final int tableNumber;

    public MealPaidEvent(String mealId, int tableNumber) {
        this.mealId = mealId;
        this.tableNumber = tableNumber;
    }

    @Override
    public String getChannel() {
        return MEAL_CHANNEL;
    }
}

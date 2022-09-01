package com.restaurant.application;

import DDD.framework.Bus;
import com.restaurant.domain.Meal;
import com.restaurant.domain.Order;
import com.restaurant.domain.TableNumber;
import com.restaurant.infrastructure.repository.InMemoryMealRepo;
import org.junit.Test;

import static com.restaurant.domain.Meal.State.*;
import static com.restaurant.domain.Meals.noMeals;
import static org.assertj.core.api.Assertions.assertThat;

public class MealServiceTest {

    @Test
    public void firstOrder() {
        // GIVEN
        MealService mealService = createMealService();
        // WHEN
        Meal.Id mealID = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pizza"));
        // THEN
        assertThat(mealService.loadByID(mealID).get().state())
                .isEqualTo(ORDER_PLACED);
    }

    @Test
    public void orderReady() {
        // GIVEN
        MealService mealService = createMealService();
        Meal.Id mealId = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pizza"));
        // WHEN
        mealId = mealService.readyOrder(mealId);
        // THEN
        assertThat(mealService.loadByID(mealId).get().state())
                .isEqualTo(Meal.State.ORDER_READY);
    }

    @Test
    public void orderServed() {
        // GIVEN
        MealService mealService = createMealService();
        Meal.Id mealId = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pizza"));
        mealId = mealService.readyOrder(mealId);
        // WHEN
        mealId = mealService.serveOrder(mealId);
        // THEN
        assertThat(mealService.loadByID(mealId).get().state())
                .isEqualTo(ORDER_SERVED);
    }

    @Test
    public void twoOrderServed() {
        // GIVEN
        MealService mealService = createMealService();
        Meal.Id mealId = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pasta"));
        mealId = mealService.readyOrder(mealId);
        mealId = mealService.serveOrder(mealId);
        mealId = mealService.order(mealId, new Order(new TableNumber(1), "pizza"));
        mealId = mealService.readyOrder(mealId);
        // WHEN
        mealId = mealService.serveOrder(mealId);
        // THEN
        Meal meal = mealService.loadByID(mealId).get();
        assertThat(meal.state()).isEqualTo(ORDER_SERVED);
        assertThat(meal.orders.size()).isEqualTo(2);
    }

    @Test
    public void billPlease() {
        // GIVEN
        MealService mealService = createMealService();
        Meal.Id mealId = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pasta"));
        mealService.readyOrder(mealId);
        mealService.serveOrder(mealId);
        mealService.order(mealId, new Order(new TableNumber(1), "pizza"));
        mealService.readyOrder(mealId);
        mealService.serveOrder(mealId);
        // WHEN
        mealService.billPlease(mealId);
        // THEN
        Meal meal = mealService.loadByID(mealId).get();
        assertThat(meal.state()).isEqualTo(BILL_EDITED);
        assertThat(meal.bill().total()).isEqualTo(20);
    }

    @Test
    public void pay() {
        // GIVEN
        MealService mealService = createMealService();
        Meal.Id mealId = mealService.order(new TableNumber(1), new Order(new TableNumber(1), "pasta"));
        mealService.readyOrder(mealId);
        mealService.serveOrder(mealId);
        mealService.order(mealId, new Order(new TableNumber(1), "pizza"));
        mealService.readyOrder(mealId);
        mealService.serveOrder(mealId);
        mealService.billPlease(mealId);
        // WHEN
        mealService.pay(mealId);
        // THEN
        Meal meal = mealService.loadByID(mealId).get();
        assertThat(meal.state()).isEqualTo(PAID);
    }

    private MealService createMealService() {
        return new MealService(new InMemoryMealRepo(noMeals), new ConsoleBus());
    }
}

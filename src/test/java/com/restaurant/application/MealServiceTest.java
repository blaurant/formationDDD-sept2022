package com.restaurant.application;

import org.junit.Test;

public class MealServiceTest {

    @Test
    public void order() {
        // GIVEN
        MealService mealService = createMealService();
        // WHEN
        MealID mealID = mealService.order(new Order(TableNumber));
        // THEN
        assertThat(mealService.loadByID(mealID).get().state())
                .isEqualTo(ORDER_PLACED);
    }
}

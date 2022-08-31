package com.restaurant.domain;

import java.util.Optional;

public interface MealRepository {
    Optional<Meal> loadById(Meal.Id mealId);

    void save(Meal meal);
}

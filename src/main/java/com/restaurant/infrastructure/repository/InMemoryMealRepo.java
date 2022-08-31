package com.restaurant.infrastructure.repository;

import DDD.framework.Objects;
import com.restaurant.domain.Meal;
import com.restaurant.domain.MealRepository;
import com.restaurant.domain.Meals;

import java.util.Optional;

public class InMemoryMealRepo implements MealRepository {

    private Meals meals;

    public InMemoryMealRepo(Meals meals) {
        this.meals = Objects.requireNotNull(meals);
    }

    @Override
    public Optional<Meal> loadById(Meal.Id id) {
        return meals.filter(meal -> id.equals(meal.getId())).first();
    }

    @Override
    public void save(Meal meal) {
        this.meals = meals.add(meal);
    }

//    @Override
//    public void saveAll(Meals meals) {
//        this.meals = new Meals(this.meals.meals.addAll(meals.meals));
//    }
//
//    @Override
//    public Meals loadAll() {
//        return meals;
//    }
}

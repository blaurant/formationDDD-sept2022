package com.restaurant.domain;

import io.vavr.collection.HashSet;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class Meals {

    public static Meals noMeals = new Meals(HashSet.empty());

    public final HashSet<Meal> meals;

    public Meals(HashSet<Meal> meals) {
        this.meals = meals;
    }

    public Meals filter(Predicate<Meal> predicate) {
        return new Meals(meals.filter(predicate));
    }

    public Meals add(Meal meal) {
        return new Meals(meals.add(meal));
    }

    public Optional<Meal> first() {
        Meal meal = null;
        try {
            meal = meals.head();
        } catch (NoSuchElementException e) {
        }
        return Optional.ofNullable(meal);
    }
}

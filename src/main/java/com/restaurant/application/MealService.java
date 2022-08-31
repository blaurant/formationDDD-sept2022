package com.restaurant.application;

import com.restaurant.domain.Meal;
import com.restaurant.domain.MealRepository;
import com.restaurant.domain.Order;

import java.util.Optional;

import static DDD.framework.Objects.requireNotNull;

public class MealService {

    private MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @DDD.Scenario
    public Optional<Meal> loadByID(Meal.Id mealID) {
        return mealRepository.loadById(mealID);
    }

    @DDD.Scenario
    public Meal.Id order(Order order) {
        requireNotNull(order);
        Meal meal = new Meal(Meal.Id.generate(), order);
        mealRepository.save(meal);
        return meal.getId();
    }

    @DDD.Scenario
    public Meal.Id order(Meal.Id mealId, Order order) {
        requireNotNull(mealId);
        requireNotNull(order);
        Meal meal = loadMeal(mealId);
        meal.order(order);
        mealRepository.save(meal);
        return meal.getId();
    }

    @DDD.Scenario
    public Meal.Id readyOrder(Meal.Id mealId) {
        requireNotNull(mealId);
        Meal meal = loadMeal(mealId);
        meal.readyOrder();
        mealRepository.save(meal);
        return meal.getId();
    }

    @DDD.Scenario
    public Meal.Id serveOrder(Meal.Id mealId) {
        requireNotNull(mealId);
        Meal meal = loadMeal(mealId);
        meal.serveOrder();
        mealRepository.save(meal);
        return meal.getId();
    }

    private Meal loadMeal(Meal.Id mealId) {
        Optional<Meal> mealOptional = loadByID(mealId);
        if (!mealOptional.isPresent())
            throw new MealServiceException("Meal with Id " + mealId + " doesn't exits");
        return mealOptional.get();
    }

    @DDD.Scenario
    public Meal.Id billPlease(Meal.Id mealId) {
        requireNotNull(mealId);
        Meal meal = loadMeal(mealId);
        meal.billPlease();
        mealRepository.save(meal);
        return meal.getId();
    }

    public Meal.Id pay(Meal.Id mealId) {
        requireNotNull(mealId);
        Meal meal = loadMeal(mealId);
        meal.pay();
        mealRepository.save(meal);
        return meal.getId();
    }
}

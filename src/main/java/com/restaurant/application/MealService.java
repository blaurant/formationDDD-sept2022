package com.restaurant.application;

import DDD.framework.Bus;
import com.restaurant.domain.*;

import java.util.Optional;

import static DDD.framework.Objects.requireNotNull;

public class MealService {

    private final MealRepository mealRepository;
    private final Bus bus;

    public MealService(MealRepository mealRepository, Bus bus) {
        this.mealRepository = mealRepository;
        this.bus = bus;
    }

    @DDD.Scenario
    public Optional<Meal> loadByID(Meal.Id mealID) {
        return mealRepository.loadById(mealID);
    }

    @DDD.Scenario
    public Meal.Id order(TableNumber tableNumber, Order order) {
        requireNotNull(order);
        Meal meal = new Meal(Meal.Id.generate(), tableNumber, order);
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
        bus.publish(new MealPaidEvent(mealId.asString(), meal.tableNumber.value));
        return meal.getId();
    }
}

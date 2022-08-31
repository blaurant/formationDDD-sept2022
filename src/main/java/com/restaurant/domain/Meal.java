package com.restaurant.domain;

import DDD.framework.Entity;
import DDD.framework.EntityId;

import java.util.UUID;

import static DDD.framework.Objects.requireNotNull;
import static com.restaurant.domain.Meal.State.*;
import static com.restaurant.domain.Orders.emptyOrders;

@DDD.Entity
public class Meal extends Entity<Meal.Id> {

    public static class Id extends EntityId<UUID> {

        public Id(UUID id) {
            super(id);
        }

        public static Id generate() {
            return new Id(UUID.randomUUID());
        }

        public static Id fromString(String str) {
            return new Id(UUID.fromString(str));
        }

    }

    public enum State {
        ORDER_PLACED, ORDER_READY, ORDER_SERVED, BILL_EDITED, PAID

    }

    public Orders orders = emptyOrders;

    private State state;
    private Bill bill;

    public Meal(Id id, Order order) {
        super(id);
        requireNotNull(order);
        this.orders = orders.append(order);
        this.state = ORDER_PLACED;
    }

    @DDD.EntityCommand
    public Meal order(Order order) {
        if (isInState(ORDER_SERVED)) {
            this.orders = orders.append(order);
            this.state = ORDER_PLACED;
        }
        return this;
    }

    @DDD.EntityCommand
    public Meal readyOrder() {
        if (isInState(ORDER_PLACED))
            this.state = ORDER_READY;
        return this;
    }

    @DDD.EntityCommand
    public Meal serveOrder() {
        if (isInState(ORDER_READY))
            this.state = ORDER_SERVED;
        return this;
    }

    @DDD.EntityCommand
    public Meal billPlease() {
        if (isInState(ORDER_SERVED)) {
            this.bill = new Bill(orders);
            this.state = BILL_EDITED;
        }
        return this;
    }

    @DDD.EntityCommand
    public Meal pay() {
        if (isInState(BILL_EDITED)) {
            this.state = PAID;
        }
        return this;
    }

    private boolean isInState(State state) {
        return this.state == state;
    }

    public State state() {
        return this.state;
    }

    public Bill bill() {
        return this.bill;
    }
}

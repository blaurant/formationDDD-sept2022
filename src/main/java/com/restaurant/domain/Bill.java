package com.restaurant.domain;

@DDD.VO
public class Bill {

    private static final int PRICE_UNIT = 10;

    public Orders orders;

    public Bill(Orders orders) {
        this.orders = orders;
    }

    public double total() {
        return orders.size() * PRICE_UNIT;
    }
}

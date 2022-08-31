package com.restaurant.domain;

public class Order {

    public final TableNumber tableNumber;

    public Order(TableNumber tableNumber, String food) {
        this.tableNumber = tableNumber;
    }
}

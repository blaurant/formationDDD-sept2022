package com.restaurant.domain;

import io.vavr.collection.List;

public class Orders {

    public static Orders emptyOrders = new Orders(List.empty());
    public final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Orders append(Order order) {
        return new Orders(orders.append(order));
    }

    public int size() {
        return orders.size();
    }
}

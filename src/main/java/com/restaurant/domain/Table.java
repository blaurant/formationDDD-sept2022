package com.restaurant.domain;

import DDD.framework.Entity;
import DDD.framework.Objects;

public class Table extends Entity<TableNumber> {

    public final Capacity capacity;

    public Table(TableNumber tableNumber, Capacity capacity) {
        super(tableNumber);
        this.capacity = Objects.requireNotNull(capacity);
    }

    public static Table of(int number, int capacity) {
        return new Table(new TableNumber(number), new Capacity(2));
    }

    public int number() {
        return getId().value;
    }

    public int capacity() {
        return capacity.value;
    }
}

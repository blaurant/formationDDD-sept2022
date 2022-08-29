package com.restaurant.domain;

import DDD.framework.Entity;

public class Table extends Entity<TableNumber> {

    public Table(TableNumber tableNumber) {
        super(tableNumber);
    }

    public Table(int number) {
        this(new TableNumber(number));
    }
}

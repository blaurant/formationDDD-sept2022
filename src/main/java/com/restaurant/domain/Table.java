package com.restaurant.domain;

import DDD.framework.Objects;

import static DDD.framework.Objects.requireNotNull;

public class Table {

    public final TableNumber tableNumber;

    public Table(TableNumber tableNumber) {
        this.tableNumber = requireNotNull(tableNumber);
    }

    public Table(int number) {
        this(new TableNumber(number));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return tableNumber.equals(table.tableNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(tableNumber);
    }
}

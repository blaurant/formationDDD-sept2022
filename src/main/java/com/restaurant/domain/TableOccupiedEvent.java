package com.restaurant.domain;

import DDD.framework.DomainEvent;

import static com.restaurant.domain.Table.TABLE_CHANNEL;

public class TableOccupiedEvent implements DomainEvent {

    public final int number;
    public TableOccupiedEvent(int number) {
        this.number = number;
    }

    @Override
    public String getChannel() {
        return TABLE_CHANNEL;
    }

    @Override
    public String toString() {
        return "TableOccupiedEvent{" +
                "number=" + number +
                '}';
    }
}

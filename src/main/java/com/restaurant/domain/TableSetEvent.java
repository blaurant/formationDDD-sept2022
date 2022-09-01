package com.restaurant.domain;

import DDD.framework.DomainEvent;

import static com.restaurant.domain.Table.TABLE_CHANNEL;

public class TableSetEvent implements DomainEvent {

    public final int number;
    public TableSetEvent(int number) {
        this.number = number;
    }

    @Override
    public String getChannel() {
        return TABLE_CHANNEL;
    }

    @Override
    public String toString() {
        return "TableSetEvent{" +
                "number=" + number +
                '}';
    }
}

package com.restaurant.infrastructure.rest;

import com.restaurant.domain.Table;

public class TableDto {
    public final Integer number;
    public final String state;
    public final Integer capacity;

    public TableDto(Table table) {
        this.number = table.getId().value;
        this.state = table.state().name();
        this.capacity = table.capacity.value;
    }
}

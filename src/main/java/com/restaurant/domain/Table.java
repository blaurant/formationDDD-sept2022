package com.restaurant.domain;

import DDD.framework.Entity;
import DDD.framework.Objects;

import static com.restaurant.domain.Table.State.*;

@DDD.Entity
public class Table extends Entity<TableNumber> {

    public static final String TABLE_CHANNEL = "TABLE_CHANNEL";
    public enum State {SET, OCCUPIED, FREED}



    public final Capacity capacity;

    private State state;

    public Table(TableNumber tableNumber, Capacity capacity) {
        super(tableNumber);
        this.capacity = Objects.requireNotNull(capacity);
        this.state = SET;
    }

    public static Table of(int number, int capacity) {
        return new Table(new TableNumber(number), new Capacity(capacity));
    }

    public int number() {
        return getId().value;
    }

    public int capacity() {
        return capacity.value;
    }

    public State state() {
        return state;
    }

    @DDD.EntityCommand
    public Table assign() {
        if (isInState(SET))
            this.state = OCCUPIED;
        return this;
    }

    @DDD.EntityCommand
    public Table clear() {
        if (isInState(OCCUPIED))
            this.state = FREED;
        return this;
    }

    @DDD.EntityCommand
    public Table set() {
        if (isInState(FREED))
            this.state = SET;
        return this;
    }

    private boolean isInState(State state) {
        return this.state == state;
    }

}

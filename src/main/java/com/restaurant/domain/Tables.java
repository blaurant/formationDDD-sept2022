package com.restaurant.domain;

import DDD.framework.Objects;
import io.vavr.collection.List;

import java.util.function.Predicate;

import static DDD.framework.Objects.requireNotNull;

public class Tables {

    public final List<Table> tables;

    public Tables(List<Table> tables) {
        this.tables = requireNotNull(tables);
    }

    public Tables filter(Predicate<Table> predicate) {
        return new Tables(tables.filter(predicate));
    }

    public Table first() {
        return tables.head();
    }
}

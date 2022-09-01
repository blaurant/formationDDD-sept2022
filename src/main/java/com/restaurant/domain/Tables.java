package com.restaurant.domain;

import DDD.VO;
import io.vavr.collection.HashSet;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static DDD.framework.Objects.requireNotNull;

@VO
public class Tables {

    public static Tables noTables = new Tables(HashSet.empty());

    public final HashSet<Table> tables;

    public Tables(HashSet<Table> tables) {
        this.tables = requireNotNull(tables);
    }

    public Tables filter(Predicate<Table> predicate) {
        return new Tables(tables.filter(predicate));
    }

    public Optional<Table> first() {
        Table table = null;
        try {
            table = tables.head();
        } catch (NoSuchElementException e) {
        }
        return Optional.ofNullable(table);
    }

    public Tables onlyWithState(Table.State state) {
        return filter(t -> t.state().equals(state));
    }

    public Tables onlyWithCapacity(Capacity capacity) {
        return filter(t -> t.capacity.equals(capacity));
    }

    public Tables add(Table table) {
        return new Tables(tables.add(table));
    }
}

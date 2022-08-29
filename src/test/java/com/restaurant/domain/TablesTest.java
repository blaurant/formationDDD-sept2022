package com.restaurant.domain;

import io.vavr.collection.HashSet;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TablesTest {

    public static Tables noTables = new Tables(HashSet.empty());

    public static Tables createTables() {
        return new Tables(HashSet.of(Table.of(1, 2), Table.of(2, 6)));
    }

    public static Tables createOneTable() {
        return new Tables(HashSet.of(Table.of(1, 2)));
    }

    @Test
    public void onlyWithCapacity() {
        assertThat(createTables().onlyWithCapacity(new Capacity(6)).first().get().capacity.value)
                .isEqualTo(6);
    }
}

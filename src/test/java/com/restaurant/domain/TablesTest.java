package com.restaurant.domain;

import io.vavr.collection.HashSet;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TablesTest {

    public static Table table1 = Table.of(1, 2);
    public static Table table2 = Table.of(1, 6);
    public static Tables noTables = new Tables(HashSet.empty());
    public static Tables oneTable = new Tables(HashSet.of(table1));
    public static Tables someTables = new Tables(HashSet.of(table1, table2));

    @Test
    public void onlyWithCapacity() {
        assertThat(someTables.onlyWithCapacity(new Capacity(6)).first().get()).isEqualTo(table2);
    }
}

package com.restaurant.application;

import com.restaurant.domain.Capacity;
import com.restaurant.domain.Table;
import io.vavr.collection.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static com.restaurant.domain.Table.State.OCCUPIED;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TableServiceTest {

    static Tables oneTable = new Tables(List.of(Table.of(1, 2)));
    static Tables someTables = new Tables(List.of(
            Table.of(1, 2),
            Table.of(2, 6)));

    @Test
    public void badSeatingCustomers() {
        TableService tableService = new TableService(new InMemoryRepo());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.seatingCustomers(0));
    }

    @Test
    public void seatingCustomers() {
        TableService tableService = new TableService(new InMemoryRepo());
        // GIVEN
        tableService.setupRestaurantHall(tables);
        // WHEN
        Table table = tableService.seatingCustomers(2);
        // THEN
        Assertions.assertThat(tableService.loadByNumber(table.number()).status())
                .isEqualsTo(OCCUPIED);
    }

    @Test
    public void seatingCustomersWithAdjust() {
        TableService tableService = new TableService(new InMemoryRepo());
        // GIVEN
        tableService.setupRestaurantHall(someTables);
        // WHEN
        Table table = tableService.seatingCustomers(5);
        // THEN
        Assertions.assertThat(tableService.loadByNumber(table.number()).capacity())
                .isEqualsTo(6);
        Assertions.assertThat(tableService.loadByNumber(table.number()).status())
                .isEqualsTo(OCCUPIED);
    }
}

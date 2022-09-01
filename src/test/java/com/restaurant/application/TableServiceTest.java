package com.restaurant.application;

import DDD.framework.Bus;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableNumber;
import com.restaurant.infrastructure.repository.InMemoryTableRepo;
import org.junit.Test;

import static com.restaurant.domain.Table.State.*;
import static com.restaurant.domain.TablesTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TableServiceTest {

    @Test
    public void badSeatingCustomers() {
        TableService tableService = createTableService();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.place(0));
    }

    @Test
    public void loadByTableNumber() {
        TableService tableService = createTableService();
        assertThat(tableService.loadByTableNumber(2).isPresent())
                .isFalse();
    }

    @Test
    public void seatingCustomers() {
        TableService tableService = createTableService();
        // GIVEN
        tableService.setupRestaurantHall(createOneTable());
        // WHEN
        Table table = tableService.place(2);
        // THEN
        assertThat(tableService.loadByTableNumber(table.number()).get().state())
                .isEqualTo(OCCUPIED);
    }

    @Test
    public void seatingCustomersWithAdjust() {
        TableService tableService = createTableService();
        // GIVEN
        tableService.setupRestaurantHall(createTables());
        // WHEN
        Table table = tableService.place(5);
        // THEN
        assertThat(tableService.loadByTableNumber(table.number()).get().capacity())
                .isEqualTo(6);
        assertThat(tableService.loadByTableNumber(table.number()).get().state())
                .isEqualTo(OCCUPIED);
    }

    @Test
    public void freeTable() {
        TableService tableService = createTableService();
        // GIVEN
        tableService.setupRestaurantHall(createTables());
        TableNumber tableNumber = tableService.place(5).getId();
        // WHEN
        tableService.freeTable(tableNumber);
        // THEN
        assertThat(tableService.loadByTableNumber(tableNumber.value).get().state())
                .isEqualTo(FREED);
    }

    @Test
    public void setTable() {
        TableService tableService = createTableService();
        // GIVEN
        tableService.setupRestaurantHall(createTables());
        TableNumber tableNumber = tableService.place(5).getId();
        tableService.freeTable(tableNumber);
        // WHEN
        tableService.setTable(tableNumber);
        // THEN
        assertThat(tableService.loadByTableNumber(tableNumber.value).get().state())
                .isEqualTo(SET);
    }

    private TableService createTableService() {
        return new TableService(new InMemoryTableRepo(noTables), new ConsoleBus());
    }

}

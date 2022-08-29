package com.restaurant.application;

import DDD.framework.Objects;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableNumber;
import com.restaurant.domain.TableRepository;
import com.restaurant.domain.Tables;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

import static com.restaurant.domain.Table.State.*;
import static com.restaurant.domain.TablesTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TableServiceTest {

    @Test
    public void badSeatingCustomers() {
        TableService tableService = createTableService(noTables);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.seatingCustomers(0));
    }

    @Test
    public void loadByTableNumber() {
        TableService tableService = createTableService(noTables);
        assertThat(tableService.loadByTableNumber(2).isPresent())
                .isFalse();
    }

    @Test
    public void seatingCustomers() {
        TableService tableService = createTableService(oneTable);
        // GIVEN
        tableService.setupRestaurantHall(oneTable);
        // WHEN
        Table table = tableService.seatingCustomers(2);
        // THEN
        assertThat(tableService.loadByTableNumber(table.number()).get().state())
                .isEqualTo(OCCUPIED);
    }

    private TableService createTableService(Tables tables) {
        return new TableService(new InMemoryRepo(tables));
    }

    @Test
    public void seatingCustomersWithAdjust() {
        TableService tableService = createTableService(someTables);
        // GIVEN
        tableService.setupRestaurantHall(someTables);
        // WHEN
        Table table = tableService.seatingCustomers(5);
        // THEN
        assertThat(tableService.loadByTableNumber(table.number()).get().capacity())
                .isEqualTo(6);
        assertThat(tableService.loadByTableNumber(table.number()).get().state())
                .isEqualTo(OCCUPIED);
    }

    @Test
    public void freeTable() {
        TableService tableService = createTableService(someTables);
        // GIVEN
        tableService.setupRestaurantHall(someTables);
        TableNumber tableNumber = tableService.seatingCustomers(5).getId();
        // WHEN
        tableService.freeTable(tableNumber);
        // THEN
        assertThat(tableService.loadByTableNumber(tableNumber.value).get().state())
                .isEqualTo(FREED);
    }

    @Test
    public void setTable() {
        TableService tableService = createTableService(someTables);
        // GIVEN
        tableService.setupRestaurantHall(someTables);
        TableNumber tableNumber = tableService.seatingCustomers(5).getId();
        tableService.freeTable(tableNumber);
        // WHEN
        tableService.setTable(tableNumber);
        // THEN
        assertThat(tableService.loadByTableNumber(tableNumber.value).get().state())
                .isEqualTo(SET);
    }

    private class InMemoryRepo implements TableRepository {

        private Tables tables;

        InMemoryRepo(Tables tables) {
            this.tables = Objects.requireNotNull(tables);
        }

        @Override
        public Optional<Table> loadByTableNumber(int number) {
            return tables.filter(new Predicate<Table>() {
                @Override
                public boolean test(Table table) {
                    return number == table.number();
                }
            }).first();
        }

        @Override
        public void save(Table table) {
            this.tables = tables.add(table);
        }

        @Override
        public void saveAll(Tables tables) {
            this.tables = new Tables(this.tables.tables.addAll(tables.tables));
        }

        @Override
        public Tables loadAll() {
            return tables;
        }
    }

}

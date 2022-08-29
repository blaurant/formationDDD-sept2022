package com.restaurant.application;

import DDD.framework.Objects;
import com.restaurant.domain.Capacity;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableRepository;
import com.restaurant.domain.Tables;
import io.vavr.collection.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Predicate;

import static com.restaurant.domain.Table.State.OCCUPIED;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TableServiceTest {

//    static Tables oneTable = new Tables(List.of(Table.of(1, 2)));
//    static Tables someTables = new Tables(List.of(
//            Table.of(1, 2),
//            Table.of(2, 6)));

    @Test
    public void badSeatingCustomers() {
        TableService tableService = new TableService(new InMemoryRepo(new Tables(List.empty())));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.seatingCustomers(0));
    }


//    @Test
//    public void seatingCustomers() {
//        TableService tableService = new TableService(new InMemoryRepo());
//        // GIVEN
//        tableService.setupRestaurantHall(tables);
//        // WHEN
//        Table table = tableService.seatingCustomers(2);
//        // THEN
//        Assertions.assertThat(tableService.loadByNumber(table.number()).status())
//                .isEqualsTo(OCCUPIED);
//    }
//
//    @Test
//    public void seatingCustomersWithAdjust() {
//        TableService tableService = new TableService(new InMemoryRepo());
//        // GIVEN
//        tableService.setupRestaurantHall(someTables);
//        // WHEN
//        Table table = tableService.seatingCustomers(5);
//        // THEN
//        Assertions.assertThat(tableService.loadByNumber(table.number()).capacity())
//                .isEqualsTo(6);
//        Assertions.assertThat(tableService.loadByNumber(table.number()).status())
//                .isEqualsTo(OCCUPIED);
//    }

    private class InMemoryRepo implements TableRepository {

        private final Tables tables;

        InMemoryRepo(Tables tables) {
            this.tables = Objects.requireNotNull(tables);
        }

        @Override
        public Table loadByNumber(int number) {
            return tables.filter(new Predicate<Table>() {
                @Override
                public boolean test(Table table) {
                    return number == table.number();
                }
            }).first();
        }
    }

}

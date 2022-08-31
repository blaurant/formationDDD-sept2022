package com.restaurant.infrastructure.rest;

import com.restaurant.domain.Table;
import com.restaurant.domain.TableNumber;
import com.restaurant.domain.Tables;

import java.util.List;

import static java.lang.Integer.parseInt;

class TableAdaptor {
    static List<TableDto> tablesToDto(Tables tables) {
        return tables.tables.map(t -> tableToDto(t)).toJavaList();
    }

    static TableDto tableToDto(Table table) {
        return new TableDto(table);
    }

    static TableNumber tableNumber(String tableNumberStr) {
        return new TableNumber(parseInt(tableNumberStr));
    }

    public static class TableDto {
        public final Integer number;
        public final String state;
        public final Integer capacity;

        public TableDto(Table table) {
            this.number = table.getId().value;
            this.state = table.state().name();
            this.capacity = table.capacity.value;
        }
    }
}

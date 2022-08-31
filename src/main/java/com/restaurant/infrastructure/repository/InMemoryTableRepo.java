package com.restaurant.infrastructure.repository;

import DDD.framework.Objects;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableRepository;
import com.restaurant.domain.Tables;

import java.util.Optional;

public class InMemoryTableRepo implements TableRepository {

    private Tables tables;

    public InMemoryTableRepo(Tables tables) {
        this.tables = Objects.requireNotNull(tables);
    }

    @Override
    public Optional<Table> loadByTableNumber(int number) {
        return tables.filter(table -> number == table.number()).first();
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

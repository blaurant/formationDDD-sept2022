package com.restaurant.application;

import com.restaurant.domain.*;

import java.util.Optional;

import static DDD.framework.Objects.requireNotNull;
import static com.restaurant.domain.Capacity.MAX_CAPACITY;
import static com.restaurant.domain.Table.State.SET;

public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = requireNotNull(tableRepository);
    }

    @DDD.Scenario
    public Tables getAllTables() {
        return tableRepository.loadAll();
    }

    @DDD.Scenario
    public Table place(int numberOfCustomer) {
        checkNumberOfCustomer(numberOfCustomer);
        Table table = tableRepository.loadAll()
                .onlyWithState(SET)
                .onlyWithCapacity(new Capacity(adjust(numberOfCustomer)))
                .first()
                .orElseThrow(() -> new TableServiceException("can not seat customers"));
        table.assign();
        tableRepository.save(table);
        return table;
    }

    @DDD.Scenario
    public Table freeTable(TableNumber tableNumber) {
        Table table = loadByTableNumber(tableNumber.value)
                .orElseThrow(() -> new TableServiceException("no Table " + tableNumber.value));
        table.clear();
        tableRepository.save(table);
        return table;
    }

    @DDD.Scenario
    public Table setTable(TableNumber tableNumber) {
        Table table = loadByTableNumber(tableNumber.value)
                .orElseThrow(() -> new TableServiceException("no Table " + tableNumber.value));
        table.set();
        tableRepository.save(table);
        return table;
    }

    private int adjust(int numberOfCustomer) {
        return numberOfCustomer % 2 == 0 ? numberOfCustomer : numberOfCustomer + 1;
    }

    private void checkNumberOfCustomer(int numberOfCustomer) {
        if (numberOfCustomer <= 0)
            throw new IllegalArgumentException("number of customer must be more then 1");
        if (numberOfCustomer > MAX_CAPACITY)
            throw new IllegalArgumentException("number of customer must be less then " + MAX_CAPACITY);
    }

    void setupRestaurantHall(Tables tables) {
        tableRepository.saveAll(tables);
    }

    Optional<Table> loadByTableNumber(int number) {
        return tableRepository.loadByTableNumber(number);
    }

    public Table getTable(TableNumber tableNumber) {
        return tableRepository.loadByTableNumber(tableNumber.value)
                .orElseThrow(() -> new TableServiceException("no table " + tableNumber.value));
    }
}

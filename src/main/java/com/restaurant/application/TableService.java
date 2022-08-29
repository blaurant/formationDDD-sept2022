package com.restaurant.application;

import com.restaurant.domain.Capacity;
import com.restaurant.domain.Table;
import com.restaurant.domain.TableNumber;
import com.restaurant.domain.TableRepository;

import static DDD.framework.Objects.requireNotNull;
import static com.restaurant.domain.Capacity.MAX_CAPACITY;

public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = requireNotNull(tableRepository);
    }

    public Table seatingCustomers(int numberOfCustomer) {
        if (numberOfCustomer <= 0)
            throw new IllegalArgumentException("number of customer must be more then 1");
        if (numberOfCustomer > MAX_CAPACITY)
            throw new IllegalArgumentException("number of customer must be less then " + MAX_CAPACITY);
        return null;
    }
}

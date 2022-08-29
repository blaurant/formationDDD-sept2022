package com.restaurant.domain;

import java.util.Optional;

@DDD.Repository
public interface TableRepository {

    Tables loadAll();

    Optional<Table> loadByTableNumber(int number);

    void save(Table table);

    void saveAll(Tables tables);
}

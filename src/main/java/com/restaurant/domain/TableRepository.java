package com.restaurant.domain;

public interface TableRepository {

    Table loadByNumber(int number);
}

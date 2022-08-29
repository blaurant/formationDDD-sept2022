package com.restaurant.domain;

import DDD.framework.SimpleValueObject;

import static DDD.framework.Integers.requireBetween;

/**
 * Tables number are starting from 1 to TOTAL_TABLE included
 */
public class TableNumber extends SimpleValueObject<Integer> {

    public static final int TOTAL_TABLE = 20;

    public TableNumber(int i) {
        super(requireBetween(i, 1, TOTAL_TABLE + 1));
    }
}

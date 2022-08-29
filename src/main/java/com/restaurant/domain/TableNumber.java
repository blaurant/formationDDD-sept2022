package com.restaurant.domain;

/**
 * Tables number are starting from 1 to TOTAL_TABLE included
 */
public class TableNumber {

    public static final int TOTAL_TABLE = 20;
    public final int number;

    public TableNumber(int i) {
        this.number = requireBetween(i, 1, TOTAL_TABLE+1);
    }

    private int requireBetween(int i, int lowerBound, int upperBound) {
        if (lowerBound>i)
            throw new IllegalArgumentException("number is below " + lowerBound);
        if (i>upperBound)
            throw new IllegalArgumentException("number is upper " + upperBound);
        return i;
    }
}

package com.restaurant.domain;

import org.junit.Test;

public class TableNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void numberTooSmall() {
        new TableNumber(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void numberTooBig() {
        new TableNumber(TOTAL_TABLE + 1);
    }

}

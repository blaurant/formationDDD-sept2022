package com.restaurant.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.restaurant.domain.TableNumber.TOTAL_TABLE;

public class TableNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void numberTooSmall() {
        new TableNumber(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void numberTooBig() {
        new TableNumber(TOTAL_TABLE + 2);
    }

    @Test
    public void numberOk() {
        Assertions.assertThat(new TableNumber(1).value).isEqualTo(1);
    }
}

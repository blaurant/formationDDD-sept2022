package com.restaurant.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * First we test identity, then invariants, than life cycle.
 */
public class TableTest {

    @Test
    public void badBuildTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Table(null, new Capacity(2)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Table(new TableNumber(1), null));
    }

    @Test
    public void integrityBuildTest() {
        Table table = Table.of(1, 2);
        assertThat(table.number()).isEqualTo(1);
        assertThat(table.capacity()).isEqualTo(2);
    }

    @Test
    public void identityTest() {
        Assertions.assertThat(Table.of(2, 2)).isEqualTo(Table.of(2, 2));
        Assertions.assertThat(Table.of(2, 2)).isNotEqualTo(Table.of(3, 2));
    }
}











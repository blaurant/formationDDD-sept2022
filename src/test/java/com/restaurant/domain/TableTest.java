package com.restaurant.domain;

import org.junit.Test;

import static com.restaurant.domain.Table.State.*;
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
        assertThat(table.state()).isEqualTo(SET);
    }

    @Test
    public void identityTest() {
        assertThat(Table.of(2, 2)).isEqualTo(Table.of(2, 2));
        assertThat(Table.of(2, 2)).isNotEqualTo(Table.of(3, 2));
    }

    @Test
    public void assignTest() {
        assertThat(Table.of(1, 2)
                .assign()
                .state()).isEqualTo(OCCUPIED);
    }

    @Test
    public void clearTest() {
        assertThat(Table.of(1, 2)
                .assign()
                .clear()
                .state()).isEqualTo(FREED);
    }

    @Test
    public void setTest() {
        assertThat(Table.of(1, 2)
                .assign()
                .clear()
                .set()
                .state()).isEqualTo(SET);
    }
}











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
                .isThrownBy(() -> new Table(null, 2));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Table(1, null));
    }

    @Test
    public void integrityBuildTest() {
        Table table = new Table(1, 2);
        assertThat(table.number()).isEqualTo(1);
        assertThat(table.capacity()).isEqualTo(2);
    }

    @Test
    public void identityTest() {
        Assertions.assertThat(new Table(2, 2)).isEqualTo(new Table(2, 2));
        Assertions.assertThat(new Table(2, 2)).isNotEqualTo(new Table(3, 2));
    }
}











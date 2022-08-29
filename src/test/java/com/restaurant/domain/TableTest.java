package com.restaurant.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import sun.tools.jconsole.Tab;

import static DDD.framework.Integers.requireBetween;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * First we test identity, then invariants, than life cycle.
 *
 */
public class TableTest {

    @Test
    public void buildTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Table(null));
    }

    @Test
    public void identityTest() {
        Assertions.assertThat(new Table(2)).isEqualTo(new Table(2));
        Assertions.assertThat(new Table(2)).isNotEqualTo(new Table(3));
    }

}
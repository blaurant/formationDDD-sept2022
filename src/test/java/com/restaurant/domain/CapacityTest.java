package com.restaurant.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CapacityTest {

    @Test
    public void badCapacityBuildTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Capacity(null));
        assertThatExceptionOfType(IllegalCapacityException.class)
                .isThrownBy(() -> new Capacity(1));
        assertThatExceptionOfType(CapacityExceededException.class)
                .isThrownBy(() -> new Capacity(6));
    }

    @Test
    public void buildTest() {
        Assertions.assertThat(new Capacity(2).value).equals(2);
    }
}

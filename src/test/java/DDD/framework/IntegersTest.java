package DDD.framework;

import org.junit.Test;

import static DDD.framework.Integers.requireBetween;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class IntegersTest {

    @Test
    public void requireBetweenTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> requireBetween(0, 1, 2));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> requireBetween(4, 2, 3));
        assertThat(requireBetween(1, 1, 3)).isEqualTo(1);
        assertThat(requireBetween(3, 2, 3)).isEqualTo(3);
    }
}

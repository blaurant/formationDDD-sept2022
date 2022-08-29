package DDD.framework;

import java.util.Objects;

import static DDD.framework.Objects.requireNotNull;

public abstract class SimpleValueObject<T> {
    public final T value;

    public SimpleValueObject(T value) {
        this.value = requireNotNull(value);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), value.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SimpleValueObject<?> other = (SimpleValueObject<?>) obj;
        return Objects.equals(value, other.value);
    }
}

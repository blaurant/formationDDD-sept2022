package DDD.framework;

public class Integers {

    public static int requireBetween(Integer i, int lowerBound, int upperBound) {
        Objects.requireNotNull(i);
        if (lowerBound > i)
            throw new IllegalArgumentException("number is below " + lowerBound);
        if (i > upperBound)
            throw new IllegalArgumentException("number is upper " + upperBound);
        return i;
    }
}

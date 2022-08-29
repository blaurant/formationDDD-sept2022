package DDD.framework;

public class Objects {

    public static final String CAN_NOT_BE_NULL = "can not be null";

    public static <T> T requireNotNull(T obj) {
        if (obj == null)
            throw new IllegalArgumentException(CAN_NOT_BE_NULL);
        return obj;
    }
}

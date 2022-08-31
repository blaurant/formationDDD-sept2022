package com.restaurant.application;

public class MealServiceException extends RuntimeException {
    public MealServiceException(String message) {
        super(message);
    }

    public MealServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealServiceException(Throwable cause) {
        super(cause);
    }

    public MealServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.restaurant.application;

public class TableServiceException extends RuntimeException {

    public TableServiceException(String message) {
        super(message);
    }

    public TableServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableServiceException(Throwable cause) {
        super(cause);
    }

    protected TableServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

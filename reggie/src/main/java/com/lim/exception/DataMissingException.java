package com.lim.exception;

public class DataMissingException extends RuntimeException {
    public DataMissingException(String message) {
        super(message);
    }
}

package com.lim.exception;

public class ReferentialIntegrityException extends RuntimeException{
    public ReferentialIntegrityException() {
            super();
    }

    public ReferentialIntegrityException(String message) {
        super(message);
    }
}

package com.zlotran.happyhours.exception;

public class InvalidRecordException extends RuntimeException {

    public InvalidRecordException() {
    }

    public InvalidRecordException(String message) {
        super(message);
    }
}

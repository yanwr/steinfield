package com.my.steinfield.Steinfield.exceptions;

public class DataInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataInvalidException(String msg, Throwable cause) {
        super("Data invalid. "+msg, cause);
    }
}
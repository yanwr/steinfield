package com.my.steinfield.Steinfield.exceptions;

public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(Throwable cause, String msg) {
        super("Data not found." + msg, cause);
    }
    public DataNotFoundException(String msg) {
        super("Data not found." + msg);
    }
}
package com.example.parser;

import java.util.List;

public class ParseException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<String> errors;

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParseException(List<String> errors) {
        this.errors = errors;
        throw this;
    }

    public List<String> getErrors() {
        return this.errors;
    }

}

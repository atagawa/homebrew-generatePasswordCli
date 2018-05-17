package com.example.parser;

import java.util.List;

public class ParseException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<String> errors;

    public ParseException(List<String> errors) {
        this.errors = errors;
        throw this;
    }

    public List<String> getErrors() {
        return this.errors;
    }

}

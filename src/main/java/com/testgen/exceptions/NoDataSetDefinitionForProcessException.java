package com.testgen.exceptions;

public class NoDataSetDefinitionForProcessException extends Exception {
    private static final String message = "No dataset configuration present for leaf process ";
    public NoDataSetDefinitionForProcessException(String message) {
        super(message);
    }

    public static NoDataSetDefinitionForProcessException build() {
        return new NoDataSetDefinitionForProcessException(String.format(message));
    }
} 

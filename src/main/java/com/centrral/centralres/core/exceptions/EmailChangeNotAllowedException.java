package com.centrral.centralres.core.exceptions;

public class EmailChangeNotAllowedException extends RuntimeException {
    public EmailChangeNotAllowedException(String message) {
        super(message);
    }
}

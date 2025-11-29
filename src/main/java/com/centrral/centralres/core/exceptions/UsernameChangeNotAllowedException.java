package com.centrral.centralres.core.exceptions;

public class UsernameChangeNotAllowedException extends RuntimeException {
    public UsernameChangeNotAllowedException(String message) {
        super(message);
    }
}

package com.centrral.centralres.core.exceptions;

public class CustomerAlreadyExistsException extends ConflictException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}

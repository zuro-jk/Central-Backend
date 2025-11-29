package com.centrral.centralres.core.exceptions;

public class EmailAlreadyExistsException extends ConflictException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

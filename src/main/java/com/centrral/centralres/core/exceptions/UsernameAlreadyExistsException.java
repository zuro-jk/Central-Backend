package com.centrral.centralres.core.exceptions;

public class UsernameAlreadyExistsException extends ConflictException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}

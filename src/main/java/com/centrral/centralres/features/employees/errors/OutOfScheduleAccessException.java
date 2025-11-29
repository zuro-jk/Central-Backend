package com.centrral.centralres.features.employees.errors;

public class OutOfScheduleAccessException extends RuntimeException {
    public OutOfScheduleAccessException(String message) {
        super(message);
    }
}
package com.centrral.centralres.features.suppliers.exceptions;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
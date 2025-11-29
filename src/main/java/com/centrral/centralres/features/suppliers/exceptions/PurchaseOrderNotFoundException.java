package com.centrral.centralres.features.suppliers.exceptions;

public class PurchaseOrderNotFoundException extends RuntimeException {
    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}

package com.centrral.centralres.features.products.exceptions;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String message) {
        super(message);
    }
}

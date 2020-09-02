package com.dnieln7.javaspringapi.exception;

public enum ServerErrors {
    DASHBOARD_PRICE_PRODUCT("Can´t perform the operation: There are not products registered!"),
    SELLER_NOT_FOUND("Seller not found!"),
    SELLER_CONSTRAINT_VIOLATION("Can´t delete, this seller has one o more products registered!"),
    PRODUCT_NOT_FOUND("Product not found!"),
    ;

    private final String message;

    ServerErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

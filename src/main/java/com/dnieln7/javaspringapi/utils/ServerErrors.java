package com.dnieln7.javaspringapi.utils;

public enum ServerErrors {
    GENERIC_ERROR("There was an error!"),
    BRAND_DUPLICATED("Duplicated! there is a brand with the same name"),
    BRAND_NOT_FOUND("No brand found with id: "),
    BRAND_CONSTRAINT_VIOLATION("Can´t delete, this brand is used by one or more products!"),
    CATEGORY_DUPLICATED("Duplicated! there is a category with the same name"),
    CATEGORY_NOT_FOUND("No category found with id: "),
    CATEGORY_CONSTRAINT_VIOLATION("Can´t delete, this category is used by one or more products!"),
    PRODUCT_NOT_FOUND("No product found with id: "),
    PRODUCT_DUPLICATED("Duplicated! there is a product with the same bar code"),
    STOCK_NOT_FOUND("No stock found with id: "),
    STOCK_DUPLICATED("Duplicated! there is a stock with the same unit type"),
    RETURN_ORDER_NOT_FOUND("No return order found with id: "),
    SALE_ORDER_NOT_FOUND("No sale order found with id: "),
    CUSTOMER_NOT_FOUND("No customer found with id: "),
    CUSTOMER_DUPLICATED("Duplicated! there is a customer with the same email or phone code"),
    CUSTOMER_CONSTRAINT_VIOLATION("Can´t delete, this customer is used by one or more tables!"),
    SUPPLY_PURCHASE_NOT_FOUND("No supply purchase found with id: "),
    SUPPLY_DELIVERY_NOT_FOUND("No supply delivery found with id: "),
    SUPPLIER_NOT_FOUND("No supplier found with id: "),
    SUPPLIER_DUPLICATED("Duplicated! there is a supplier with the same email or phone code"),
    SUPPLIER_CONSTRAINT_VIOLATION("Can´t delete, this supplier is included in one or more supply purchase registries"),
    ;

    private final String message;

    ServerErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

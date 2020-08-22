package com.dnieln7.javaspringapi.data.model;

/**
 * View to show product id, name, quantity and price properties.
 *
 * @author dnieln7
 */
public interface ViewNameQuantityPrice {
    public int getId();

    public String getName();

    public int getQuantity();

    public double getPrice();
}

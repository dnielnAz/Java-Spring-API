package com.dnieln7.javaspringapi.data.model;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.seller.Seller;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Product model (One product can have many sellers).
 *
 * @author dnieln7
 */
@Entity
@Table(name = "products")
public class Product extends Auditable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    @ManyToOne
    private Seller seller;

    public Product() {
    }

    public Product(int id, String name, String description, int quantity, double price, Seller seller) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

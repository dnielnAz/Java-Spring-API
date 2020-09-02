package com.dnieln7.javaspringapi.data.model;

import com.dnieln7.javaspringapi.data.model.seller.Seller;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Product model (One product can have many sellers).
 *
 * @author dnieln7
 */
@Entity
@Table(name = "products")
public class Product {

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
    private LocalDateTime updated;
    @Column(updatable = false)
    private LocalDateTime created;

    public Product() {
    }

    public Product(int id, String name, String description, int quantity, double price, Seller seller, LocalDateTime updated, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.seller = seller;
        this.updated = updated;
        this.created = created;
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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

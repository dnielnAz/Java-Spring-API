package com.dnieln7.javaspringapi.data.model.seller;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Seller model (One seller can have many products).
 *
 * @author dnieln7
 */
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    private String name;
    private String address;
    private String phone;
    private LocalDateTime updated;
    @Column(updatable = false)
    private LocalDateTime created;

    public Seller() {
    }

    public Seller(int id) {
        this.id = id;
    }

    public Seller(int id, String name, String address, String phone, LocalDateTime updated, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
package com.dnieln7.javaspringapi.data.model.product;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.brand.Brand;
import com.dnieln7.javaspringapi.data.model.category.Category;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @NotNull
    private String barCode;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @ManyToOne
    private Brand brand;
    @NotNull
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(Integer id, String barCode, String name, String description, Brand brand, Category category) {
        this.id = id;
        this.barCode = barCode;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand=" + brand +
                ", category=" + category +
                '}';
    }
}

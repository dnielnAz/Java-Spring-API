package com.dnieln7.javaspringapi.data.model.stock;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.product.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_stock")
public class Stock extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @OneToOne
    private Product product;
    @NotNull
    private String unitType;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double purchasePrice;
    @NotNull
    private Double publicPrice;
    @NotNull
    private Double discount;

    public Stock() {
    }

    public Stock(Integer id, Product product, String unitType, Integer quantity, Double purchasePrice, Double publicPrice, Double discount) {
        this.id = id;
        this.product = product;
        this.unitType = unitType;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.publicPrice = publicPrice;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Double publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", product=" + product +
                ", unit=" + unitType +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", publicPrice=" + publicPrice +
                ", discount=" + discount +
                '}';
    }
}

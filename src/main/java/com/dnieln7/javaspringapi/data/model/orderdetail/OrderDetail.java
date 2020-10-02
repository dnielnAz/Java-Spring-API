package com.dnieln7.javaspringapi.data.model.orderdetail;

import com.dnieln7.javaspringapi.data.model.product.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tb_order_detail")
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @ManyToOne
    private Product product;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;
    @NotNull
    private Double total;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Product product, Integer quantity, Double price, Double total) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}

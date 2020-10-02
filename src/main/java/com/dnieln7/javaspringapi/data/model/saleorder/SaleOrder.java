package com.dnieln7.javaspringapi.data.model.saleorder;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.customer.Customer;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tb_sale_order")
public class SaleOrder extends Auditable {
    
    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @OneToOne
    private Customer customer;
    @OneToMany
    private List<OrderDetail> items;
    @NotNull
    private OffsetDateTime saleDate;
    @NotNull
    private Double total;

    public SaleOrder() {
    }

    public SaleOrder(Integer id, Customer customer, List<OrderDetail> items, OffsetDateTime saleDate, Double total) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.saleDate = saleDate;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }

    public OffsetDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(OffsetDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + items +
                ", saleDate=" + saleDate +
                ", total=" + total +
                '}';
    }
}

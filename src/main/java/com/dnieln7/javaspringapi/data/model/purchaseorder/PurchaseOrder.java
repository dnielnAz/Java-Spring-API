package com.dnieln7.javaspringapi.data.model.purchaseorder;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.supplier.Supplier;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tb_purchase_order")
public class PurchaseOrder extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @OneToMany
    private List<OrderDetail> items;
    @OneToOne
    private Supplier supplier;
    @NotNull
    private OffsetDateTime purchaseDate;
    private Double discount;
    @NotNull
    private Double total;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, List<OrderDetail> items, Supplier supplier, OffsetDateTime purchaseDate, Double discount, Double total) {
        this.id = id;
        this.items = items;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;
        this.discount = discount;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public OffsetDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(OffsetDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SupplyPurchase{" +
                "id=" + id +
                ", products=" + items +
                ", supplier=" + supplier +
                ", purchaseDate=" + purchaseDate +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

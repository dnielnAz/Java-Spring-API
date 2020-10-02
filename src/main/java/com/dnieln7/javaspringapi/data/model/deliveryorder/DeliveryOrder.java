package com.dnieln7.javaspringapi.data.model.deliveryorder;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.purchaseorder.PurchaseOrder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tb_delivery_order")
public class DeliveryOrder extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @OneToOne
    private PurchaseOrder purchaseOrder;
    @OneToMany
    private List<OrderDetail> delivery;
    @NotNull
    private String deliveryManName;
    private String deliveryManPhone;
    @NotNull
    private OffsetDateTime deliveryDate;
    private String comments;
    @NotNull
    private Boolean deliveryIssues;
    private String issuesDescription;

    public DeliveryOrder() {
    }

    public DeliveryOrder(Integer id, PurchaseOrder purchaseOrder, List<OrderDetail> delivery, String deliveryManName, String deliveryManPhone, OffsetDateTime deliveryDate, String comments, Boolean deliveryIssues, String issuesDescription) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.delivery = delivery;
        this.deliveryManName = deliveryManName;
        this.deliveryManPhone = deliveryManPhone;
        this.deliveryDate = deliveryDate;
        this.comments = comments;
        this.deliveryIssues = deliveryIssues;
        this.issuesDescription = issuesDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<OrderDetail> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<OrderDetail> delivery) {
        this.delivery = delivery;
    }

    public String getDeliveryManName() {
        return deliveryManName;
    }

    public void setDeliveryManName(String deliveryManName) {
        this.deliveryManName = deliveryManName;
    }

    public String getDeliveryManPhone() {
        return deliveryManPhone;
    }

    public void setDeliveryManPhone(String deliveryManPhone) {
        this.deliveryManPhone = deliveryManPhone;
    }

    public OffsetDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(OffsetDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getDeliveryIssues() {
        return deliveryIssues;
    }

    public void setDeliveryIssues(Boolean deliveryIssues) {
        this.deliveryIssues = deliveryIssues;
    }

    public String getIssuesDescription() {
        return issuesDescription;
    }

    public void setIssuesDescription(String issuesDescription) {
        this.issuesDescription = issuesDescription;
    }

    @Override
    public String toString() {
        return "SupplyDelivery{" +
                "id=" + id +
                ", supplyPurchase=" + purchaseOrder +
                ", deliveredProducts=" + delivery +
                ", deliveryManName='" + deliveryManName + '\'' +
                ", deliveryManPhone='" + deliveryManPhone + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", comments='" + comments + '\'' +
                ", deliveryIssues=" + deliveryIssues +
                ", issuesDescription='" + issuesDescription + '\'' +
                '}';
    }
}

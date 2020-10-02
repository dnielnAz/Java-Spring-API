package com.dnieln7.javaspringapi.data.model.returnorder;

import com.dnieln7.javaspringapi.data.Auditable;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.customer.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tb_return_order")
public class ReturnOrder extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @OneToOne
    private Customer customer;
    @NotNull
    private String reason;
    @OneToMany
    private List<OrderDetail> items;
    @NotNull
    private OffsetDateTime submitDate;
    private OffsetDateTime approvedDate;
    @NotNull
    private Boolean approved;
    private String comments;
    @NotNull
    private Double total;

    public ReturnOrder() {
    }

    public ReturnOrder(Integer id, Customer customer, String reason, List<OrderDetail> items, OffsetDateTime submitDate, OffsetDateTime approvedDate, Boolean approved, String comments, Double total) {
        this.id = id;
        this.customer = customer;
        this.reason = reason;
        this.items = items;
        this.submitDate = submitDate;
        this.approvedDate = approvedDate;
        this.approved = approved;
        this.comments = comments;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }

    public OffsetDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(OffsetDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public OffsetDateTime getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(OffsetDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReturnOrder{" +
                "id=" + id +
                ", customer=" + customer +
                ", reason='" + reason + '\'' +
                ", stocks=" + items +
                ", submitDate=" + submitDate +
                ", approvedDate=" + approvedDate +
                ", approved=" + approved +
                ", total=" + total +
                '}';
    }
}

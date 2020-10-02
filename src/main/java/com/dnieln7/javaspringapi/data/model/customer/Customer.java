package com.dnieln7.javaspringapi.data.model.customer;

import com.dnieln7.javaspringapi.data.Auditable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_customer")
public class Customer extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @NotNull
    private String name;
    @Pattern(regexp = "\\+[0-9]{1,9}")
    @Size(min = 2, max = 10)
    private String phoneCode;
    @Size(min = 10, max = 10)
    private String phone;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    public Customer() {
    }

    public Customer(Integer id, String name, String phoneCode, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phoneCode = phoneCode;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

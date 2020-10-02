package com.dnieln7.javaspringapi.data.model.brand;

import com.dnieln7.javaspringapi.data.Auditable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_brand")
public class Brand extends Auditable {

    @Id
    @GeneratedValue(generator = "increment_gen")
    @GenericGenerator(name = "increment_gen", strategy = "increment")
    private Integer id;
    @NotNull
    private String name;
    private String fullName;

    public Brand() {
    }

    public Brand(Integer id, String name, String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

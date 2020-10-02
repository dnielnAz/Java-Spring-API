package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.supplier.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
    public Optional<Supplier> findByPhoneOrEmail(String phone, String email);
}

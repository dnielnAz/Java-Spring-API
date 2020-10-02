package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.customer.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public Optional<Customer> findByPhoneOrEmail(String phone, String email);
}

package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Optional<Product> findByBarCode(String barCode);
}

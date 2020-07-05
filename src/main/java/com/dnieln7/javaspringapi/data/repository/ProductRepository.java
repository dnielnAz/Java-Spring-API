package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByStoreId(int storeId);
}

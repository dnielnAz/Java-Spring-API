package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.brand.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
    public Optional<Brand> findByName(String name);
}

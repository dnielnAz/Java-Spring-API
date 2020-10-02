package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.category.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}

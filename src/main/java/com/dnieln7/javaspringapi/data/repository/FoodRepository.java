package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Integer> {

    public List<Food> findByStoreId(int storeId);
}

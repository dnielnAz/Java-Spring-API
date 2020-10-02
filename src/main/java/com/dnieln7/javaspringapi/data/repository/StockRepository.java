package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.stock.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    public Optional<Stock> findByUnitType(String unitType);
}

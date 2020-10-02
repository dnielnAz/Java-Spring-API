package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.saleorder.SaleOrder;
import org.springframework.data.repository.CrudRepository;

public interface SaleOrderRepository extends CrudRepository<SaleOrder, Integer> {
}

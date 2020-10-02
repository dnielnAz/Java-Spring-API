package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.returnorder.ReturnOrder;
import org.springframework.data.repository.CrudRepository;

public interface ReturnOrderRepository extends CrudRepository<ReturnOrder, Integer> {
}

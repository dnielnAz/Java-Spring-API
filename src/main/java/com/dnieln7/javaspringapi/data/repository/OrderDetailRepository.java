package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
}

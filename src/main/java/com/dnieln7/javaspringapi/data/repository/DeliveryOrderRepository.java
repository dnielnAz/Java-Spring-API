package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.deliveryorder.DeliveryOrder;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer> {
}

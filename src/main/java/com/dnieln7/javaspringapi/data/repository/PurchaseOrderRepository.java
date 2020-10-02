package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.purchaseorder.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer> {
}

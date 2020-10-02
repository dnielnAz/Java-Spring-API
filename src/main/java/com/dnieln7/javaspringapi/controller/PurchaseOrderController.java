package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.purchaseorder.PurchaseOrder;
import com.dnieln7.javaspringapi.data.repository.OrderDetailRepository;
import com.dnieln7.javaspringapi.data.repository.PurchaseOrderRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/supply/purchase")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<Iterable<PurchaseOrder>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<PurchaseOrder> supplyPurchase = repository.findById(id);

        if (supplyPurchase.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.SUPPLY_PURCHASE_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplyPurchase.get());
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody PurchaseOrder entity) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        orderDetailRepository.saveAll(entity.getItems()).forEach(orderDetails::add);
        entity.setItems(orderDetails);

        if (orderDetails.size() != entity.getItems().size()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(ServerErrors.GENERIC_ERROR.getMessage()));
        }

        PurchaseOrder saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}

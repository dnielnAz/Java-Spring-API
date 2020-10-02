package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.deliveryorder.DeliveryOrder;
import com.dnieln7.javaspringapi.data.repository.OrderDetailRepository;
import com.dnieln7.javaspringapi.data.repository.DeliveryOrderRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/supply/delivery")
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<Iterable<DeliveryOrder>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<DeliveryOrder> supplyPurchase = repository.findById(id);

        if (supplyPurchase.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.SUPPLY_DELIVERY_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplyPurchase.get());
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody DeliveryOrder entity) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        orderDetailRepository.saveAll(entity.getDelivery()).forEach(orderDetails::add);
        entity.setDelivery(orderDetails);

        if (orderDetails.size() != entity.getDelivery().size()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(ServerErrors.GENERIC_ERROR.getMessage()));
        }

        DeliveryOrder saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}

package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.saleorder.SaleOrder;
import com.dnieln7.javaspringapi.data.repository.OrderDetailRepository;
import com.dnieln7.javaspringapi.data.repository.SaleOrderRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/sale")
public class SaleOrderController {

    @Autowired
    private SaleOrderRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<Iterable<SaleOrder>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<SaleOrder> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.SALE_ORDER_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody SaleOrder entity) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        orderDetailRepository.saveAll(entity.getItems()).forEach(orderDetails::add);
        entity.setItems(orderDetails);

        if (orderDetails.size() != entity.getItems().size()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(ServerErrors.GENERIC_ERROR.getMessage()));
        }

        SaleOrder saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}

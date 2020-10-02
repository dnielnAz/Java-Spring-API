package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.customer.Customer;
import com.dnieln7.javaspringapi.utils.exception.ResponseException;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import com.dnieln7.javaspringapi.data.model.orderdetail.OrderDetail;
import com.dnieln7.javaspringapi.data.model.returnorder.ReturnOrder;
import com.dnieln7.javaspringapi.data.repository.OrderDetailRepository;
import com.dnieln7.javaspringapi.data.repository.ReturnOrderRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/returned")
public class ReturnOrderController {

    @Autowired
    private ReturnOrderRepository repository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<Iterable<ReturnOrder>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<ReturnOrder> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.RETURN_ORDER_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody ReturnOrder entity) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        orderDetailRepository.saveAll(entity.getItems()).forEach(orderDetails::add);
        entity.setItems(orderDetails);

        if (orderDetails.size() != entity.getItems().size()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(ServerErrors.GENERIC_ERROR.getMessage()));
        }

        ReturnOrder saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Integer id, @RequestBody ReturnOrder entity) {
        Optional<ReturnOrder> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.RETURN_ORDER_NOT_FOUND.getMessage() + id));
        }

        ReturnOrder modified = container.map(returnOrder -> {

            returnOrder.setCustomer(entity.getCustomer());
            returnOrder.setReason(entity.getReason());
            returnOrder.setSubmitDate(entity.getSubmitDate());
            returnOrder.setApprovedDate(entity.getApprovedDate());
            returnOrder.setApproved(entity.getApproved());
            returnOrder.setComments(entity.getComments());
            returnOrder.setTotal(entity.getTotal());

            return returnOrder;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        ReturnOrder saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }
}

package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.customer.Customer;
import com.dnieln7.javaspringapi.data.model.product.Product;
import com.dnieln7.javaspringapi.utils.ServerMessages;
import com.dnieln7.javaspringapi.utils.message.DeleteMessage;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import com.dnieln7.javaspringapi.data.repository.CustomerRepository;
import com.dnieln7.javaspringapi.utils.exception.ResponseException;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController implements GenericController<Customer> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public ResponseEntity<Iterable<Customer>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(Integer id) {
        Optional<Customer> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.CUSTOMER_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Customer entity) {
        Optional<Customer> container = repository.findByPhoneOrEmail(entity.getPhone(), entity.getEmail());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.CUSTOMER_DUPLICATED.getMessage()));
        }

        Customer saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Customer entity) {
        Optional<Customer> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.CUSTOMER_NOT_FOUND.getMessage() + id));
        }

        Customer modified = container.map(customer -> {

            customer.setName(entity.getName());
            customer.setPhoneCode(entity.getPhoneCode());
            customer.setPhone(entity.getPhone());
            customer.setEmail(entity.getEmail());

            return customer;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Customer saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Customer> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.CUSTOMER_NOT_FOUND.getMessage() + id));
        }

        try {
            repository.delete(container.get());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ResponseException(ServerErrors.CUSTOMER_CONSTRAINT_VIOLATION.getMessage());
            }
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

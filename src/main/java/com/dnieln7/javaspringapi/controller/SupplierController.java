package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.customer.Customer;
import com.dnieln7.javaspringapi.utils.ServerMessages;
import com.dnieln7.javaspringapi.utils.message.DeleteMessage;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import com.dnieln7.javaspringapi.data.model.supplier.Supplier;
import com.dnieln7.javaspringapi.data.repository.SupplierRepository;
import com.dnieln7.javaspringapi.utils.exception.ResponseException;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController implements GenericController<Supplier> {

    @Autowired
    private SupplierRepository repository;

    @Override
    public ResponseEntity<Iterable<Supplier>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(Integer id) {
        Optional<Supplier> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.SUPPLIER_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Supplier entity) {
        Optional<Supplier> container = repository.findByPhoneOrEmail(entity.getPhone(), entity.getEmail());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.SUPPLIER_DUPLICATED.getMessage()));
        }

        Supplier saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Supplier entity) {
        Optional<Supplier> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.SUPPLIER_NOT_FOUND.getMessage() + id));
        }

        Supplier modified = container.map(supplier -> {

            supplier.setName(entity.getName());
            supplier.setAddress(entity.getAddress());
            supplier.setPhoneCode(entity.getPhoneCode());
            supplier.setPhone(entity.getPhone());
            supplier.setEmail(entity.getEmail());

            return supplier;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Supplier saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) {
        Optional<Supplier> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.SUPPLIER_NOT_FOUND.getMessage() + id));
        }

        try {
            repository.delete(container.get());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ResponseException(ServerErrors.SUPPLIER_CONSTRAINT_VIOLATION.getMessage());
            }
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

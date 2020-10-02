package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.brand.Brand;
import com.dnieln7.javaspringapi.data.repository.BrandRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import com.dnieln7.javaspringapi.utils.ServerMessages;
import com.dnieln7.javaspringapi.utils.exception.ResponseException;
import com.dnieln7.javaspringapi.utils.message.DeleteMessage;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
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
@RequestMapping("/brands")
public class BrandController implements GenericController<Brand> {

    @Autowired
    private BrandRepository repository;

    @Override
    public ResponseEntity<Iterable<Brand>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Brand> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.BRAND_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Brand entity) {
        Optional<Brand> container = repository.findByName(entity.getName());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.BRAND_DUPLICATED.getMessage()));
        }

        Brand saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Brand entity) {
        Optional<Brand> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.BRAND_NOT_FOUND.getMessage() + id));
        }

        Brand modified = container.map(brand -> {

            brand.setName(entity.getName());
            brand.setFullName(entity.getFullName());

            return brand;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Brand saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Brand> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.BRAND_NOT_FOUND.getMessage() + id));
        }

        try {
            repository.delete(container.get());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ResponseException(ServerErrors.BRAND_CONSTRAINT_VIOLATION.getMessage());
            }
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

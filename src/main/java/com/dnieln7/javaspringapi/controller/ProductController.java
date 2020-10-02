package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.product.Product;
import com.dnieln7.javaspringapi.data.repository.ProductRepository;
import com.dnieln7.javaspringapi.utils.ServerErrors;
import com.dnieln7.javaspringapi.utils.ServerMessages;
import com.dnieln7.javaspringapi.utils.exception.ResponseException;
import com.dnieln7.javaspringapi.utils.message.DeleteMessage;
import com.dnieln7.javaspringapi.utils.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController implements GenericController<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public ResponseEntity<Iterable<Product>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(Integer id) {
        Optional<Product> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.PRODUCT_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Product entity) {
        Optional<Product> container = repository.findByBarCode(entity.getBarCode());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.PRODUCT_DUPLICATED.getMessage()));
        }

        Product saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Product entity) {
        Optional<Product> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.PRODUCT_NOT_FOUND.getMessage() + id));
        }

        Product modified = container.map(product -> {

            product.setBarCode(entity.getBarCode());
            product.setName(entity.getName());
            product.setDescription(entity.getDescription());
            product.setBrand(entity.getBrand());
            product.setCategory(entity.getCategory());

            return product;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Product saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Product> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.PRODUCT_NOT_FOUND.getMessage() + id));
        }

        repository.delete(container.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

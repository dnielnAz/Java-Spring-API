package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.category.Category;
import com.dnieln7.javaspringapi.data.repository.CategoryRepository;
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
@RequestMapping("/categories")
public class CategoryController implements GenericController<Category>{

    @Autowired
    private CategoryRepository repository;

    @Override
    public ResponseEntity<Iterable<Category>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Category> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.CATEGORY_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Category entity) {
        // Treat with uppercase to reduce duplication
        entity.setName(entity.getName().toUpperCase());

        Optional<Category> container = repository.findByName(entity.getName());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.CATEGORY_DUPLICATED.getMessage()));
        }

        Category saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Category entity) {
        // Treat with uppercase to reduce duplication
        entity.setName(entity.getName().toUpperCase());

        Optional<Category> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.BRAND_NOT_FOUND.getMessage() + id));
        }

        Category modified = container.map(category -> {

            category.setName(entity.getName());
            category.setDescription(entity.getDescription());

            return category;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Category saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Category> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.CATEGORY_NOT_FOUND.getMessage() + id));
        }

        try {
            repository.delete(container.get());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ResponseException(ServerErrors.CATEGORY_CONSTRAINT_VIOLATION.getMessage());
            }
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.stock.Stock;
import com.dnieln7.javaspringapi.data.repository.StockRepository;
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
@RequestMapping("/stocks")
public class StockController implements GenericController<Stock> {

    @Autowired
    private StockRepository repository;

    @Override
    public ResponseEntity<Iterable<Stock>> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    @Override
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Stock> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.STOCK_NOT_FOUND.getMessage() + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(container.get());
    }

    @Override
    public ResponseEntity<Object> post(Stock entity) {
        // Treat with uppercase to reduce duplication
        entity.setUnitType(entity.getUnitType().toUpperCase());

        Optional<Stock> container = repository.findByUnitType(entity.getUnitType());

        if (container.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage(ServerErrors.STOCK_DUPLICATED.getMessage()));
        }

        Stock saved = repository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> put(Integer id, Stock entity) {
        // Treat with uppercase to reduce duplication
        entity.setUnitType(entity.getUnitType().toUpperCase());

        Optional<Stock> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage(ServerErrors.STOCK_NOT_FOUND.getMessage() + id));
        }

        Stock modified = container.map(stock -> {

            stock.setProduct(entity.getProduct());
            stock.setUnitType(entity.getUnitType());
            stock.setQuantity(entity.getQuantity());
            stock.setPurchasePrice(entity.getPurchasePrice());
            stock.setPublicPrice(entity.getPublicPrice());
            stock.setDiscount(entity.getDiscount());

            return stock;
        }).orElseThrow(() -> new ResponseException(ServerErrors.GENERIC_ERROR.getMessage()));

        Stock saved = repository.save(modified);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saved);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Stock> container = repository.findById(id);

        if (container.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new DeleteMessage(false, ServerErrors.STOCK_NOT_FOUND.getMessage() + id));
        }

        repository.delete(container.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeleteMessage(true, ServerMessages.DELETE_SUCCESS.getMessage(), container.get()));
    }
}

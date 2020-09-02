package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.seller.Seller;
import com.dnieln7.javaspringapi.data.repository.SellerRepository;
import com.dnieln7.javaspringapi.exception.ResponseException;
import com.dnieln7.javaspringapi.exception.ServerErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * API controller for seller operations.
 *
 * @author dnieln7
 */
@RestController
public class SellerController {

    @Autowired
    private SellerRepository repository;

    @GetMapping("/sellers")
    public List<Seller> getSeller() {
        return (List<Seller>) repository.findAll();
    }

    @GetMapping("/sellers/{id}")
    public Seller getSellerById(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseException(ServerErrors.SELLER_NOT_FOUND.getMessage()));
    }

    @PostMapping("/sellers")
    public Seller postSeller(@RequestBody Seller seller) {
        seller.setCreated(LocalDateTime.now());
        seller.setUpdated(LocalDateTime.now());
        return repository.save(seller);
    }

    @PutMapping("/sellers/{id}")
    public Seller putSeller(@PathVariable int id, @RequestBody Seller seller) {
        return repository.findById(id)
                .map(found -> {
                    seller.setId(id);
                    seller.setUpdated(LocalDateTime.now());
                    return repository.save(seller);
                })
                .orElseThrow(() -> new ResponseException(ServerErrors.SELLER_NOT_FOUND.getMessage()));
    }

    @DeleteMapping("/sellers/{id}")
    public Seller deleteSeller(@PathVariable int id) {
        Seller seller = repository.findById(id)
                .orElseThrow(() -> new ResponseException(ServerErrors.SELLER_NOT_FOUND.getMessage()));

        try {
            repository.delete(seller);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ResponseException(ServerErrors.SELLER_CONSTRAINT_VIOLATION.getMessage());
            }
        }

        return seller;
    }
}

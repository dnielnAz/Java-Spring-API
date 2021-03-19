package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Seller;
import com.dnieln7.javaspringapi.data.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/sellers")
    public Seller postSeller(@RequestBody Seller seller) {
        seller.setCreated(LocalDateTime.now());
        seller.setUpdated(LocalDateTime.now());
        return repository.save(seller);
    }

    @PutMapping("/sellers/{id}")
    public Seller putSeller(@PathVariable int id, @RequestBody Seller seller) {
        seller.setId(id);
        seller.setUpdated(LocalDateTime.now());
        return repository.save(seller);
    }

    @DeleteMapping("/sellers/{id}")
    public DeleteResponse deleteSeller(@PathVariable int id) {
        Seller seller = repository.findById(id).orElse(null);

        if (seller == null) {
            return new DeleteResponse(1, "Not found!");
        }

        repository.delete(seller);

        return new DeleteResponse(1, "Deleted!");
    }
}

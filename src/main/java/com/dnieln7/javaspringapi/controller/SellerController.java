package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Seller;
import com.dnieln7.javaspringapi.data.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * API controller for seller operations.
 *
 * @author dnieln7
 */
@RestController
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/store")
    public List<Seller> getStore() {
        List<Seller> sellers = new ArrayList<>();

        sellerRepository.findAll().forEach(sellers::add);

        return sellers;
    }

    @GetMapping("/store/{id}")
    public Seller getStoreById(@PathVariable int id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @PostMapping("/store")
    public Seller postStore(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }

    @PutMapping("/store/{id}")
    public Seller putStore(@PathVariable int id, @RequestBody Seller seller) {
        seller.setId(id);

        return sellerRepository.save(seller);
    }

    @DeleteMapping("/store/{id}")
    public DeleteResponse deleteStore(@PathVariable int id) {
        Seller seller = sellerRepository.findById(id).orElse(null);

        if (seller == null) {
            return new DeleteResponse(1, "Not found!");
        }

        sellerRepository.delete(seller);

        return new DeleteResponse(1, "Deleted!");
    }
}

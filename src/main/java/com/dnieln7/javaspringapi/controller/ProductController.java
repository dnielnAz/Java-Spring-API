package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Product;
import com.dnieln7.javaspringapi.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API controller for seller operations.
 *
 * @author dnieln7
 */
@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(required = false) Integer seller) {
        List<Product> products;

        if (seller == null) {
            products = (List<Product>) repository.findAll();
        } else {
            products = repository.findBySellerId(seller);
        }

        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/products")
    public Product postProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product putProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);

        return repository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public DeleteResponse deleteProduct(@PathVariable int id) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return new DeleteResponse(1, "Not found!");
        }

        repository.delete(product);

        return new DeleteResponse(1, "Deleted!");
    }
}

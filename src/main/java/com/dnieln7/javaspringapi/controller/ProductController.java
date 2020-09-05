package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.Product;
import com.dnieln7.javaspringapi.data.repository.ProductRepository;
import com.dnieln7.javaspringapi.exception.ResponseException;
import com.dnieln7.javaspringapi.exception.ServerErrors;
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
        return repository.findById(id)
                .orElseThrow(() -> new ResponseException(ServerErrors.PRODUCT_NOT_FOUND.toString()));
    }

    @PostMapping("/products")
    public Product postProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product putProduct(@PathVariable int id, @RequestBody Product product) {
        return repository.findById(id)
                .map(found -> {

                    found.setName(product.getName());
                    found.setDescription(product.getDescription());
                    found.setQuantity(product.getQuantity());
                    found.setPrice(product.getPrice());
                    found.setSeller(product.getSeller());

                    return repository.save(found);
                })
                .orElseThrow(() -> new ResponseException(ServerErrors.PRODUCT_NOT_FOUND.toString()));
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseException(ServerErrors.PRODUCT_NOT_FOUND.toString()));

        repository.delete(product);

        return product;
    }
}

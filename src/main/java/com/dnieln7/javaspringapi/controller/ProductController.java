package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Product;
import com.dnieln7.javaspringapi.data.repository.ProductRepository;
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
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getProducts(@RequestParam(required = false) Integer store) {
        List<Product> product;

        if (store == null) {
            product = new ArrayList<>();
            productRepository.findAll().forEach(product::add);
        } else {
            product = productRepository.findByStoreId(store);
        }

        return product;
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("/product")
    public Product postProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    public Product putProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);

        return productRepository.save(product);
    }

    @DeleteMapping("/product/{id}")
    public DeleteResponse deleteProduct(@PathVariable int id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return new DeleteResponse(1, "Not found!");
        }

        productRepository.delete(product);

        return new DeleteResponse(1, "Deleted!");
    }
}

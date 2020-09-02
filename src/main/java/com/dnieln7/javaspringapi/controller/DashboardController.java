package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.data.model.seller.ViewNamePhone;
import com.dnieln7.javaspringapi.data.model.seller.ViewNamePhoneProducts;
import com.dnieln7.javaspringapi.data.model.Product;
import com.dnieln7.javaspringapi.data.repository.ProductRepository;
import com.dnieln7.javaspringapi.data.repository.SellerRepository;
import com.dnieln7.javaspringapi.exception.ResponseException;
import com.dnieln7.javaspringapi.exception.ServerErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API controller to get statistical data.
 *
 * @author dnieln7
 */
@RestController
public class DashboardController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("dashboard/products/max-price")
    public Product maxPrice() {
        return productRepository.findMaxPrice()
                .orElseThrow(() -> new ResponseException(ServerErrors.DASHBOARD_PRICE_PRODUCT.getMessage()));
    }

    @GetMapping("dashboard/products/min-price")
    public Product minPrice() {
        return productRepository.findMinPrice()
                .orElseThrow(() -> new ResponseException(ServerErrors.DASHBOARD_PRICE_PRODUCT.getMessage()));
    }

    @GetMapping("dashboard/products/most-available")
    public List<Product> mostAvailableProducts(@RequestParam(required = true) int limit) {
        return productRepository.findMostAvailableProducts(limit);
    }

    @GetMapping("dashboard/products/less-available")
    public List<Product> lessAvailableProducts(@RequestParam(required = true) int limit) {
        return productRepository.findLessAvailableProducts(limit);
    }

    @GetMapping("dashboard/products/newest")
    public List<Product> newestProducts(@RequestParam(required = true) int days) {
        return productRepository.findNewestProducts(days);
    }

    @GetMapping("dashboard/sellers/popular")
    public List<ViewNamePhoneProducts> popularSellers(@RequestParam(required = true) int limit) {
        return sellerRepository.findPopularSellers(limit);
    }

    @GetMapping("dashboard/sellers/newest")
    public List<ViewNamePhone> newestSellers(@RequestParam(required = true) int days) {
        return sellerRepository.findNewestSellers(days);
    }
}

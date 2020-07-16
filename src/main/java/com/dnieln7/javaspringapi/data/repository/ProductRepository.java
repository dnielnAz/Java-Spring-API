package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud operations for {@link Product} model, and Integer for it's id type.
 *
 * @author dnieln7
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * Find all products by it´s seller.
     *
     * @param sellerId Seller id.
     * @return A list of {@link Product} that share the provided seller.
     */
    public List<Product> findBySellerId(int sellerId);
}

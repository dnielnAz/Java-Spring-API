package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Crud operations for {@link Product} model.
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

    /**
     * Find most expensive product.
     *
     * @return A {@link Product} instance with the highest price.
     */
    @Query(value = "select * from products order by products.price desc limit 1", nativeQuery = true)
    public Optional<Product> findMaxPrice();

    /**
     * Find cheapest product.
     *
     * @return A {@link Product} instance with the lowest price.
     */
    @Query(value = "select * from products order by products.price asc limit 1", nativeQuery = true)
    public Optional<Product> findMinPrice();

    /**
     * Find most available products.
     *
     * @param limit Number of products to show.
     * @return A list of {@link Product} with the highest quantity.
     */
    @Query(value = "select * from products order by products.quantity desc limit ?1", nativeQuery = true)
    public List<Product> findMostAvailableProducts(int limit);

    /**
     * Find less available products.
     *
     * @param limit Number of products to show.
     * @return A list of {@link Product} with the lowest quantity.
     */
    @Query(value = "select * from products order by products.quantity asc limit ?1", nativeQuery = true)
    public List<Product> findLessAvailableProducts(int limit);

    /**
     * Find newest products based on their created_at property.
     *
     * @param days Number of days in the past to search from.
     * @return A list of {@link Product} that meets the time criteria.
     */
    @Query(value = "select * from products where products.created_at > current_date - ?1", nativeQuery = true)
    public List<Product> findNewestProducts(int days);
}

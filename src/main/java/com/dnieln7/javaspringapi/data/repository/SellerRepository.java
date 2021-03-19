package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Seller;
import com.dnieln7.javaspringapi.data.model.ViewNamePhone;
import com.dnieln7.javaspringapi.data.model.ViewNamePhoneProducts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud operations for {@link Seller} model.
 *
 * @author dnieln7
 */
public interface SellerRepository extends CrudRepository<Seller, Integer> {

    /**
     * Find sellers with the highest number of products registered.
     *
     * @param limit Number of sellers to show.
     * @return A list of {@link Seller}.
     */
    @Query(
            value = "select sellers.id, sellers.name, count(*) as products from sellers inner join products on sellers.id = products.seller_id group by sellers.id order by products desc limit ?1",
            nativeQuery = true
    )
    public List<ViewNamePhoneProducts> findPopularSellers(int limit);

    /**
     * Find newest sellers based on their created property.
     *
     * @param days Number of days in the past to search from.
     * @return A list of {@link Seller} that meets the time criteria.
     */
    @Query(
            value = "select sellers.id, sellers.name, sellers.phone from sellers where sellers.created > current_date - ?1",
            nativeQuery = true
    )
    public List<ViewNamePhone> findNewestSellers(int days);
}

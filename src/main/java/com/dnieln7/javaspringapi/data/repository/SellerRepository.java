package com.dnieln7.javaspringapi.data.repository;

import com.dnieln7.javaspringapi.data.model.Seller;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud operations for {@link Seller} model, and Integer for it's id type.
 *
 * @author dnieln7
 */
public interface SellerRepository extends CrudRepository<Seller, Integer> {

}

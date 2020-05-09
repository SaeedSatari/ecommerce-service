package com.sattari.ecommerce.dal.repository;

import com.sattari.ecommerce.dal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Saeed Sattari
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

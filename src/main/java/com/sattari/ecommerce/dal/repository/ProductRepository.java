package com.sattari.ecommerce.dal.repository;

import com.sattari.ecommerce.dal.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Saeed Sattari
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategoryId(Long categoryId);

  List<Product> findByNameContaining(String name);
}

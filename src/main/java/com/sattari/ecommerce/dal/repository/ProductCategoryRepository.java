package com.sattari.ecommerce.dal.repository;

import com.sattari.ecommerce.dal.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Saeed Sattari
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {}

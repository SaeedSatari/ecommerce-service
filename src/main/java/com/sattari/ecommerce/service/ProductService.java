package com.sattari.ecommerce.service;

import com.sattari.ecommerce.commons.exceptions.EntityNotFoundException;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Saeed Sattari
 */
@Service
@Transactional(readOnly = true)
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> fetchProducts() {
    List<Product> fetchedProducts = productRepository.findAll();
    if (fetchedProducts.isEmpty()) {
      throw new EntityNotFoundException(Product.class);
    } else {
      return fetchedProducts;
    }
  }

  public Product fetchProduct(String productId) {
    Optional<Product> optionalProduct = productRepository.findById(
      Long.valueOf(productId)
    );
    if (optionalProduct.isPresent()) {
      return optionalProduct.get();
    } else {
      throw new EntityNotFoundException(Product.class, "productId", productId);
    }
  }

  public List<Product> findByCategoryId(String categoryId) {
    List<Product> fetchedProducts = productRepository.findByCategoryId(
      Long.valueOf(categoryId)
    );
    if (fetchedProducts.isEmpty()) {
      throw new EntityNotFoundException(Product.class, "categoryId", categoryId);
    } else {
      return fetchedProducts;
    }
  }

  public List<Product> findByKeyword(String name) {
    List<Product> fetchedProducts = productRepository.findByNameContaining(name);
    if (fetchedProducts.isEmpty()) {
      throw new EntityNotFoundException(Product.class, "name", name);
    } else {
      return fetchedProducts;
    }
  }
}

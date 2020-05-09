package com.sattari.ecommerce.service;

import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Saeed Sattari
 */
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(Long.valueOf(productId));
    }
}

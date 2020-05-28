package com.sattari.ecommerce.service;

import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Product> fetchProducts() throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findAll();
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedProducts;
        }
    }

    public Product fetchProduct(String productId) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new NotFoundException("Nothing found for " + productId + " id!");
        }
    }

    public List<Product> findByCategoryId(String categoryId) throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findByCategoryId(Long.valueOf(categoryId));
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedProducts;
        }
    }

    public List<Product> findByKeyword(String name) throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findByNameContaining(name);
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds for " + name + " keyword!");
        } else {
            return fetchedProducts;
        }
    }
}

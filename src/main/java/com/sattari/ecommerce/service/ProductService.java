package com.sattari.ecommerce.service;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Page<Product> getPagedProducts(int page, int size) throws NotFoundException {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> fetchedProducts = productRepository.findAll(paging);
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

    public List<ProductResponse> findByCategoryId(String categoryId) throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findByCategoryId(Long.valueOf(categoryId));
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedProducts.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList());
        }
    }

    public List<ProductResponse> findByKeyword(String name) throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findByNameContaining(name);
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds for " + name + " keyword!");
        } else {
            return fetchedProducts.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList());
        }
    }
}

package com.sattari.ecommerce.service;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import javassist.NotFoundException;
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

    public List<ProductResponse> fetchProductsDetails() throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findAll();
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedProducts.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList());
        }
    }

    public ProductResponse fetchProductDetails(String productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
        if (optionalProduct.isPresent()) {
            return ProductMapper.MAPPER.productToProductResponse(optionalProduct.get());
        } else {
            throw new NotFoundException("Nothing found for " + productId + " id!");
        }
    }
}

package com.sattari.ecommerce.service;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final ProductCategoryService productCategoryService;

    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
    }

    public List<ProductResponse> fetchProductList() throws NotFoundException {
        List<Product> fetchedProducts = productRepository.findAll();
        if (fetchedProducts.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return mapToProductResponses(fetchedProducts);
        }
    }

    private List<ProductResponse> mapToProductResponses(List<Product> fetchedProducts) throws NotFoundException {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : fetchedProducts) {
            ProductResponse productResponse = ProductMapper.MAPPER.productToProductResponse(product);
            productResponse.setCategoryName(getCategoryName(product.getCategory().getId()));
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    public String getCategoryName(Long catalogId) throws NotFoundException {
        return productCategoryService.fetchProductCategory(String.valueOf(catalogId)).getCategoryName();
    }

    public ProductResponse fetchProduct(String productId) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
        if (optionalProduct.isPresent()) {
            return mapToProductResponse(optionalProduct.get());
        } else {
            throw new NotFoundException("Nothing found for " + productId + " id!");
        }
    }

    private ProductResponse mapToProductResponse(Product product) throws NotFoundException {
        ProductResponse productResponse = ProductMapper.MAPPER.productToProductResponse(product);
        productResponse.setCategoryName(getCategoryName(product.getCategory().getId()));
        return productResponse;
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

package com.sattari.ecommerce.service;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public List<ProductResponse> getProducts() {
        List<Product> fetchedProducts = productRepository.findAll();
        List<ProductResponse> productResponses = new LinkedList<>();
        for (Product product : fetchedProducts) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(product.getName());
            productResponse.setSku(product.getSku());
            productResponse.setDescription(product.getDescription());
            productResponse.setUnitPrice(product.getUnitPrice());
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    public ProductResponse getProductById(String productId) throws Exception {
        Optional<Product> byId = productRepository.findById(Long.valueOf(productId));
        if (byId.isPresent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setName(byId.get().getName());
            productResponse.setSku(byId.get().getSku());
            productResponse.setDescription(byId.get().getDescription());
            productResponse.setUnitPrice(byId.get().getUnitPrice());
            return productResponse;
        } else {
            throw new Exception();
        }
    }
}

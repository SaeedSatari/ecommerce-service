package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Saeed Sattari
 */
@RestController
@RequestMapping(ProductController.BASE_URL)
@CrossOrigin
public class ProductController {

    public static final String BASE_URL = "/products";

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable String productId) {
        return productService.getProductById(productId);
    }
}

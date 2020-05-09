package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable String productId) throws Exception {
        return productService.getProductById(productId);
    }
}

package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.service.ProductService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Saeed Sattari
 */
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() throws NotFoundException {
        return productService.fetchProductsDetails();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable String productId) throws Exception {
        return productService.fetchProductDetails(productId);
    }
}

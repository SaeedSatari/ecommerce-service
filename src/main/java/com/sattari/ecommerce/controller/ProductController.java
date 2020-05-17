package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.controller.response.PagedProductsResponse;
import com.sattari.ecommerce.service.ProductService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Saeed Sattari
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedProductsResponse getProductsDetails(@RequestParam int page,
                                                    @RequestParam int size) throws NotFoundException {
        return productService.getPagedProducts(page, size);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductDetails(@PathVariable String productId) throws NotFoundException {
        return productService.fetchProduct(productId);
    }

    @GetMapping("/search/categoryId")
    public List<ProductResponse> searchByCategoryId(@RequestParam String id) throws NotFoundException {
        return productService.findByCategoryId(id);
    }

    @GetMapping("/search/keyword")
    public List<ProductResponse> searchByKeyword(@RequestParam("name") String name) throws NotFoundException {
        return productService.findByKeyword(name);
    }
}

package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.ProductCategoryResponse;
import com.sattari.ecommerce.service.ProductCategoryService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Saeed Sattari
 */
@RestController
@RequestMapping("/api/product-categories")
@CrossOrigin
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategoryResponse> getProductCategories() throws NotFoundException {
        return productCategoryService.fetchProductCategories();
    }

    @GetMapping("/{categoryId}")
    public ProductCategoryResponse getProductCategory(@PathVariable String categoryId) throws NotFoundException {
        return productCategoryService.fetchProductCategory(categoryId);
    }
}

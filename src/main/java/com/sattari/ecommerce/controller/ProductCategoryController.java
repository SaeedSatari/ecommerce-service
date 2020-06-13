package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.ProductCategoryResponse;
import com.sattari.ecommerce.dal.entity.ProductCategory;
import com.sattari.ecommerce.service.ProductCategoryService;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

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
  public List<ProductCategoryResponse> getProductCategories() {
    List<ProductCategory> productCategories = productCategoryService.fetchProductCategories();
    return productCategories
      .stream()
      .map(ProductMapper.MAPPER::productCategoryToProductCategoryResponse)
      .collect(Collectors.toList());
  }

  @GetMapping("/{categoryId}")
  public ProductCategoryResponse getProductCategory(@PathVariable String categoryId) {
    ProductCategory productCategory = productCategoryService.fetchProductCategory(
      categoryId
    );
    return ProductMapper.MAPPER.productCategoryToProductCategoryResponse(productCategory);
  }
}

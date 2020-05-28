package com.sattari.ecommerce.service;

import com.sattari.ecommerce.dal.entity.ProductCategory;
import com.sattari.ecommerce.dal.repository.ProductCategoryRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Saeed Sattari
 */
@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> fetchProductCategories() throws NotFoundException {
        List<ProductCategory> fetchedCategories = productCategoryRepository.findAll();
        if (fetchedCategories.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedCategories;
        }
    }

    public ProductCategory fetchProductCategory(String categoryId) throws NotFoundException {
        Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(Long.valueOf(categoryId));
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new NotFoundException("Nothing found for " + categoryId + " id!");
        }
    }
}

package com.sattari.ecommerce.service;

import com.sattari.ecommerce.controller.response.ProductCategoryResponse;
import com.sattari.ecommerce.dal.entity.ProductCategory;
import com.sattari.ecommerce.dal.repository.ProductCategoryRepository;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Saeed Sattari
 */
@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategoryResponse> fetchProductCategories() throws NotFoundException {
        List<ProductCategory> fetchedCategories = productCategoryRepository.findAll();
        if (fetchedCategories.isEmpty()) {
            throw new NotFoundException("Nothing founds!");
        } else {
            return fetchedCategories.stream().map(ProductMapper.MAPPER::productCategoryToProductCategoryResponse).collect(Collectors.toList());
        }
    }

    public ProductCategoryResponse fetchProductCategory(String categoryId) throws NotFoundException {
        Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(Long.valueOf(categoryId));
        if (optionalCategory.isPresent()) {
            return ProductMapper.MAPPER.productCategoryToProductCategoryResponse(optionalCategory.get());
        } else {
            throw new NotFoundException("Nothing found for " + categoryId + " id!");
        }
    }
}

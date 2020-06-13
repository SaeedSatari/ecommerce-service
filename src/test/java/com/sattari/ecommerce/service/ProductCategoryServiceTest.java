package com.sattari.ecommerce.service;

import com.sattari.ecommerce.MotherObject;
import com.sattari.ecommerce.commons.exceptions.EntityNotFoundException;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.entity.ProductCategory;
import com.sattari.ecommerce.dal.repository.ProductCategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository categoryRepository;

    @InjectMocks
    private ProductCategoryService categoryService;

    @Test
    @DisplayName("fetchProductCategories when find all the product categories then should returns list of the product categories")
    void fetchProductCategories_whenFindAllTheProductCategories_thenShouldReturnsListOfTheProductCategories() {
        List<ProductCategory> anyCategories = MotherObject.anyProductCategories();
        doReturn(anyCategories).when(categoryRepository).findAll();
        List<ProductCategory> persistedCategories = categoryService.fetchProductCategories();
        assertNotNull(persistedCategories);
        assertTrue(anyCategories.containsAll(persistedCategories) && persistedCategories.containsAll(anyCategories));
    }

    @Test
    @DisplayName("fetchProductCategories when can not find product categories then should throws EntityNotFoundException")
    void fetchProductCategories_whenCanNotFindProductCategories_thenShouldThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class,
                () -> categoryService.fetchProductCategories());
    }

    @Test
    @DisplayName("fetchProductCategory when find a product category with given id then should returns the product category")
    void fetchProductCategory_whenFindAProductCategoryWithGivenId_thenShouldReturnsTheProductCategory() {
        ProductCategory anyProductCategory = MotherObject.anyProductCategory();
        doReturn(Optional.of(anyProductCategory)).when(categoryRepository).findById(anyLong());
        ProductCategory persistedProductCategory = categoryService.fetchProductCategory("1");
        assertEquals(anyProductCategory.getCategoryName(), persistedProductCategory.getCategoryName());
    }

    @Test
    @DisplayName("fetchProductCategory when can not find a product category with given id then should throws EntityNotFoundException")
    void fetchProductCategory_whenCanNotFindAProductCategoryWithGivenId_thenShouldThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class,
                () -> categoryService.fetchProductCategory("1"));
    }
}
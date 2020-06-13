package com.sattari.ecommerce.service.mapper;

import com.sattari.ecommerce.MotherObject;
import com.sattari.ecommerce.controller.response.ProductCategoryResponse;
import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.entity.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class ProductMapperTest {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private Product product;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        product = MotherObject.anyProduct();
        productCategory = MotherObject.anyProductCategory();
    }

    @Test
    void productToProductResponse() {
        ProductResponse productResponse = productMapper.productToProductResponse(product);
        assertEquals(product.getSku(), productResponse.getSku());
        assertEquals(product.getName(), productResponse.getName());
        assertEquals(product.getDescription(), productResponse.getDescription());
        assertEquals(product.getImageUrl(), productResponse.getImageUrl());
        assertEquals(product.getCategory().getCategoryName(), productResponse.getCategoryName());
        assertEquals(product.getUnitPrice(), productResponse.getUnitPrice());
        assertEquals(product.getUnitsInStock(), productResponse.getUnitsInStock());

    }

    @Test
    void productCategoryToProductCategoryResponse() {
        ProductCategoryResponse categoryResponse = productMapper.productCategoryToProductCategoryResponse(productCategory);
        assertEquals(productCategory.getCategoryName(), categoryResponse.getCategoryName());
        assertEquals(productCategory.getId(), categoryResponse.getId());
    }

    @Test
    @DisplayName("productToProductResponse when given product is null then product response is null")
    void productToProductResponse_whenGivenProductIsNull_thenProductResponseIsNull() {
        ProductResponse productResponse = productMapper.productToProductResponse(null);
        assertNull(productResponse);
    }

    @Test
    @DisplayName("productCategoryToProductCategoryResponse when given category is null then category response is null")
    void productCategoryToProductCategoryResponse_whenGivenCategoryIsNull_thenCategoryResponseIsNull() {
        ProductCategoryResponse categoryResponse = productMapper.productCategoryToProductCategoryResponse(null);
        assertNull(categoryResponse);
    }
}
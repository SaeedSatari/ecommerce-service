package com.sattari.ecommerce.service.mapper;

import com.sattari.ecommerce.MotherObject;
import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class ProductMapperTest {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private Product product;

    @BeforeEach
    void setUp() {
        product = MotherObject.anyProduct();
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
    }
}
package com.sattari.ecommerce.service;

import com.sattari.ecommerce.MotherObject;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("fetchProducts when findAll the products then should returns list of the products")
    void fetchProducts_whenFindAllTheProducts_thenShouldReturnsListOfTheProducts() throws NotFoundException {
        List<Product> anyProducts = MotherObject.anyProducts();
        doReturn(anyProducts).when(productRepository).findAll();
        List<Product> persistedProducts = productService.fetchProducts();
        assertNotNull(persistedProducts);
        assertEquals(anyProducts.size(), persistedProducts.size());
    }

    @Test
    @DisplayName("fetchProducts when can not find products then should throws new exception")
    void fetchProducts_whenCanNotFindProducts_thenShouldThrowsNewException() {
        assertThrows(Exception.class,
                () -> productService.fetchProducts());
    }

    @Test
    void fetchProduct() {
    }

    @Test
    void findByCategoryId() {
    }

    @Test
    void findByKeyword() {
    }
}
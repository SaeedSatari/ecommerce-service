package com.sattari.ecommerce.service;

import com.sattari.ecommerce.MotherObject;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.repository.ProductRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

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
    @DisplayName("fetchProduct when find a product with given id then should returns the product")
    void fetchProduct_whenFindAProductWithGivenId_thenShouldReturnsTheProduct() throws NotFoundException {
        Product anyProduct = MotherObject.anyProduct();
        doReturn(Optional.of(anyProduct)).when(productRepository).findById(anyLong());
        Product persistedProduct = productService.fetchProduct("1");
        assertEquals(anyProduct.getName(), persistedProduct.getName());
        assertEquals(anyProduct.getSku(), persistedProduct.getSku());
    }

    @Test
    @DisplayName("fetchProduct when can not find a product with given id then should throws new exception")
    void fetchProduct_whenCanNotFindAProductWithGivenId_thenShouldThrowsNewException() {
        assertThrows(Exception.class,
                () -> productService.fetchProduct("1"));
    }

    @Test
    @DisplayName("findByCategoryId when find products by category id then should returns list of the products")
    void findByCategoryId_whenFindProductsByCategoryId_thenShouldReturnsListOfTheProducts() throws NotFoundException {
        List<Product> anyProducts = MotherObject.anyProducts();
        doReturn(anyProducts).when(productRepository).findByCategoryId(anyLong());
        List<Product> persistedProducts = productService.findByCategoryId("1");
        assertNotNull(persistedProducts);
        assertEquals(anyProducts.size(), persistedProducts.size());
    }

    @Test
    @DisplayName("findByCategoryId when can not find products by category id then should throws new exception")
    void findByCategoryId_whenCanNotFindProductsByCategoryId_thenShouldThrowsNewException() {
        assertThrows(Exception.class,
                () -> productService.findByCategoryId("1"));
    }

    @Test
    @DisplayName("findByKeyword when find products by keyword then should returns list of the products")
    void findByKeyword_whenFindProductsByKeyword_thenShouldReturnsListOfTheProducts() throws NotFoundException {
        List<Product> anyProducts = MotherObject.anyProducts();
        doReturn(anyProducts).when(productRepository).findByNameContaining(anyString());
        List<Product> persistedProducts = productService.findByKeyword("Dummy Name");
        assertNotNull(persistedProducts);
        assertEquals(anyProducts.size(), persistedProducts.size());
    }

    @Test
    @DisplayName("findByKeyword when can not find products by keyword then should throws new exception")
    void findByKeyword_whenCanNotFindProductsByKeyword_thenShouldThrowsNewException() {
        assertThrows(Exception.class,
                () -> productService.findByKeyword("Dummy"));
    }
}
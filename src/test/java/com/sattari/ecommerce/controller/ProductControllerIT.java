package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.sattari.ecommerce.MotherObject.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerIT {

    @Spy
    @InjectMocks
    ProductController productController;

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getPagedProducts when request is valid then returns list of the products")
    void getPagedProducts_whenRequestIsValid_thenReturnsListOfProducts() throws Exception {
        when(productService.fetchProducts()).thenReturn(anyProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .param("page", "1")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].sku").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].description").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].unitPrice").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].categoryName").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].unitsInStock").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].imageUrl").isNotEmpty())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("getPagedProducts when request is invalid then response client error")
    void getPagedProducts_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("getProductDetails when request is valid then should returns founded product response")
    void getProductDetails_whenRequestIsValid_thenShouldReturnsFoundedProductResponse() throws Exception {
        when(productService.fetchProduct(anyString())).thenReturn(anyProduct());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/" + anyProductId())
                .param("productId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("getProductDetails when request is invalid then response client error")
    void getProductDetails_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        when(productService.fetchProduct(anyString())).thenReturn(anyProduct());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products" + anyProductId())
                .param("productId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("searchByCategoryId when request is valid then returns list of the products")
    void searchByCategoryId_whenRequestIsValid_thenReturnsListOfProducts() throws Exception {
        when(productService.findByCategoryId(anyString())).thenReturn(anyProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/search/categoryId")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("searchByCategoryId when request is invalid then response client error")
    void searchByCategoryId_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        when(productService.findByCategoryId(anyString())).thenReturn(anyProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/search/categoryIds")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("searchByKeyword when request is valid then returns list of the products")
    void searchByKeyword_whenRequestIsValid_thenReturnsListOfProducts() throws Exception {
        when(productService.findByKeyword(anyString())).thenReturn(anyProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/search/keyword")
                .param("name", "Java")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("searchByKeyword when request is invalid then response client error")
    void searchByKeyword_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        when(productService.findByKeyword(anyString())).thenReturn(anyProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/search/keywords")
                .param("name", "Java")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
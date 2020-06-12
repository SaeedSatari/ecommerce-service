package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.service.ProductCategoryService;
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

import static com.sattari.ecommerce.MotherObject.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductCategoryController.class)
class ProductCategoryControllerIT {

    @Spy
    @InjectMocks
    ProductCategoryController controller;

    @MockBean
    ProductCategoryService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getProductCategories when request is valid then returns list of product categories")
    void getProductCategories_whenRequestIsValid_thenReturnsListOfProductCategories() throws Exception {
        when(service.fetchProductCategories()).thenReturn(anyProductCategories());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product-categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("getProductCategories when request is invalid then response client error")
    void getProductCategories_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        when(service.fetchProductCategories()).thenReturn(anyProductCategories());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product-categorie")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("getProductCategory when request is valid then returns founded category")
    void getProductCategory_whenRequestIsValid_thenReturnsFoundedCategory() throws Exception {
        when(service.fetchProductCategory(anyString())).thenReturn(anyProductCategory());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product-categories/" + anyProductCategoryId())
                .param("categoryId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("getProductCategory when request is invalid then response client error")
    void getProductCategory_whenRequestIsInvalid_thenResponseClientError() throws Exception {
        when(service.fetchProductCategory(anyString())).thenReturn(anyProductCategory());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product-categories" + anyProductCategoryId())
                .param("categoryId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
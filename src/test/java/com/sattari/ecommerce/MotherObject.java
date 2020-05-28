package com.sattari.ecommerce;

import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.entity.ProductCategory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class MotherObject {

    public static Product anyProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setCategory(anyProductCategory());
        product.setSku("Dummy SKU");
        product.setName("Dummy Name");
        product.setDescription("Dummy Description");
        product.setUnitPrice(BigDecimal.valueOf(65));
        product.setUnitsInStock(10);
        product.setImageUrl("Dummy URL");
        return product;
    }

    public static ProductCategory anyProductCategory() {
        ProductCategory category = new ProductCategory();
        category.setId(1L);
        category.setCategoryName("Dummy Category Name");
        return category;
    }

    public static List<Product> anyProducts() {
        return Collections.singletonList(anyProduct());
    }
}
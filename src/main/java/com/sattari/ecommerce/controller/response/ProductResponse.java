package com.sattari.ecommerce.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private String sku;
    private String name;
    private String description;
    private BigDecimal unitPrice;
}

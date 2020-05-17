package com.sattari.ecommerce.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Saeed Sattari
 */
@Getter
@Setter
public class PagedProductsResponse {
    private List<ProductResponse> products;
    private PageResponse page;
}

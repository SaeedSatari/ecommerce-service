package com.sattari.ecommerce.controller.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Saeed Sattari
 */
@Getter
@Setter
public class PagedProductsResponse {
  private List<ProductResponse> products;
  private PageResponse page;
}

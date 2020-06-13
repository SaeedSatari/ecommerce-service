package com.sattari.ecommerce.controller.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Saeed Sattari
 */
@Getter
@Setter
public class ProductResponse {
  private Long id;
  private String sku;
  private String name;
  private String description;
  private BigDecimal unitPrice;
  private String categoryName;
  private int unitsInStock;
  private String imageUrl;
}

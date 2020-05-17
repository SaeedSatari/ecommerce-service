package com.sattari.ecommerce.controller.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Saeed Sattari
 */
@Getter
@Setter
public class PageResponse {
    private int size;
    private long totalElements;
    private int totalPages;
    private int number;
}

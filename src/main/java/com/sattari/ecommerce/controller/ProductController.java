package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.PageResponse;
import com.sattari.ecommerce.controller.response.PagedProductsResponse;
import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.service.ProductService;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Saeed Sattari
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedProductsResponse getPagedProducts(@RequestParam int page,
                                                  @RequestParam int size) throws NotFoundException {
        PagedProductsResponse response = new PagedProductsResponse();
        Page<Product> pagedProducts = productService.getPagedProducts(page, size);
        response.setPage(initiatePageResponse(pagedProducts));
        response.setProducts(pagedProducts.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList()));
        return response;
    }

    private PageResponse initiatePageResponse(Page<Product> pagedProducts) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalPages(pagedProducts.getTotalPages());
        pageResponse.setTotalElements(pagedProducts.getTotalElements());
        pageResponse.setSize(pagedProducts.getSize());
        pageResponse.setNumber(pagedProducts.getNumber());
        return pageResponse;
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductDetails(@PathVariable String productId) throws NotFoundException {
        Product product = productService.fetchProduct(productId);
        return ProductMapper.MAPPER.productToProductResponse(product);
    }

    @GetMapping("/search/categoryId")
    public List<ProductResponse> searchByCategoryId(@RequestParam String id) throws NotFoundException {
        return productService.findByCategoryId(id);
    }

    @GetMapping("/search/keyword")
    public List<ProductResponse> searchByKeyword(@RequestParam("name") String name) throws NotFoundException {
        return productService.findByKeyword(name);
    }
}

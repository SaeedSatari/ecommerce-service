package com.sattari.ecommerce.controller;

import com.sattari.ecommerce.controller.response.PageResponse;
import com.sattari.ecommerce.controller.response.PagedProductsResponse;
import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.service.ProductService;
import com.sattari.ecommerce.service.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public PagedProductsResponse getPagedProducts(@RequestParam int page, @RequestParam int size) {
        PagedProductsResponse response = new PagedProductsResponse();
        Pageable paging = PageRequest.of(page, size);
        List<Product> products = productService.fetchProducts();
        Page<Product> pagedProducts = new PageImpl<>(products, paging, products.size());
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
    public ProductResponse getProductDetails(@PathVariable String productId) {
        Product product = productService.fetchProduct(productId);
        return ProductMapper.MAPPER.productToProductResponse(product);
    }

    @GetMapping("/search/categoryId")
    public List<ProductResponse> searchByCategoryId(@RequestParam String id) {
        List<Product> products = productService.findByCategoryId(id);
        return products.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList());
    }

    @GetMapping("/search/keyword")
    public List<ProductResponse> searchByKeyword(@RequestParam("name") String name) {
        List<Product> products = productService.findByKeyword(name);
        return products.stream().map(ProductMapper.MAPPER::productToProductResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        Product createdProduct = productService.createProduct(productRequest);
        return ProductMapper.MAPPER.productToProductResponse(createdProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse editProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        UpdatedProductResponse response = new UpdatedProductResponse();
        Product product = productService.editProduct(productRequest, id);
        response.setSuccessful("Product Updated Successfully");
        response.setProductResponse(ProductMapper.MAPPER.productToProductResponse(product));
        return response;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}

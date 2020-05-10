package com.sattari.ecommerce.service.mapper;

import com.sattari.ecommerce.controller.response.ProductCategoryResponse;
import com.sattari.ecommerce.controller.response.ProductResponse;
import com.sattari.ecommerce.dal.entity.Product;
import com.sattari.ecommerce.dal.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Saeed Sattari
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "sku", source = "source.sku")
    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "unitPrice", source = "source.unitPrice")
    @Mapping(target = "unitsInStock", source = "source.unitsInStock")
    @Mapping(target = "imageUrl", source = "source.imageUrl")
    ProductResponse productToProductResponse(Product source);

    @Mapping(target = "categoryName", source = "source.categoryName")
    ProductCategoryResponse productCategoryToProductCategoryResponse(ProductCategory source);
}
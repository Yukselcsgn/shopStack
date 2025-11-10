package com.shopstack.catalog.mapper;

import com.shopstack.catalog.domain.Product;
import com.shopstack.catalog.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class})
public interface ProductMapper {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "brand", target = "brand")
    ProductResponse toResponse(Product product);
}
package com.shopstack.catalog.mapper;

import com.shopstack.catalog.domain.Category;
import com.shopstack.catalog.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
}
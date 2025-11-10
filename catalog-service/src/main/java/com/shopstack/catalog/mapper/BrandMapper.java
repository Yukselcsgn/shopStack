package com.shopstack.catalog.mapper;

import com.shopstack.catalog.domain.Brand;
import com.shopstack.catalog.dto.BrandDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand brand);
}
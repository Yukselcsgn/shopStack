package com.shopstack.catalog.service;

import com.shopstack.catalog.dto.BrandDto;

import java.util.List;

public interface BrandService {

    BrandDto create(String name);

    void delete(Long id);

    BrandDto getById(Long id);

    List<BrandDto> getAll();
}
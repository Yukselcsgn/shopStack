package com.shopstack.catalog.service;

import com.shopstack.catalog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto create(String name);

    void delete(Long id);

    CategoryDto getById(Long id);

    List<CategoryDto> getAll();
}
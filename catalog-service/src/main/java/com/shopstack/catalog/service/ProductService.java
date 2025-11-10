package com.shopstack.catalog.service;

import com.shopstack.catalog.dto.ProductRequest;
import com.shopstack.catalog.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    List<ProductResponse> getByCategory(Long categoryId);

    List<ProductResponse> getByBrand(Long brandId);

    List<ProductResponse> search(String keyword);
}
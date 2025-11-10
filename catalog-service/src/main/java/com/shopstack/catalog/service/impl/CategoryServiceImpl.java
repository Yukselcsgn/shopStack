package com.shopstack.catalog.service.impl;

import com.shopstack.catalog.domain.Category;
import com.shopstack.catalog.dto.CategoryDto;
import com.shopstack.catalog.mapper.CategoryMapper;
import com.shopstack.catalog.repository.CategoryRepository;
import com.shopstack.catalog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper){
        this.categoryRepository=categoryRepository;
        this.categoryMapper=categoryMapper;
    }

    @Override
    public CategoryDto create(String name){
        if(categoryRepository.existsByName(name)){
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = new Category(name);
        Categoy saved = categoryRepository.save(category);

        return categoryMapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
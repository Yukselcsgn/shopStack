package com.shopstack.catalog.service.impl;

import com.shopstack.catalog.domain.Brand;
import com.shopstack.catalog.dto.BrandDto;
import com.shopstack.catalog.mapper.BrandMapper;
import com.shopstack.catalog.repository.BrandRepository;
import com.shopstack.catalog.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository,
                            BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public BrandDto create(String name) {
        if (brandRepository.existsByName(name)) {
            throw new IllegalArgumentException("Brand already exists: " + name);
        }

        Brand brand = new Brand(name);
        Brand saved = brandRepository.save(brand);

        return brandMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandDto getById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        return brandMapper.toDto(brand);
    }

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDto)
                .toList();
    }
}
package com.store.service;

import com.store.dto.ProductCategoryDTO;
import com.store.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory find(Long id);
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory client);
    ProductCategory update(ProductCategory client);
    void delete(Long id);
    ProductCategory fromDto(ProductCategoryDTO dto);

}

package com.store.service.impl;

import com.store.dto.ProductCategoryDTO;
import com.store.entity.ProductCategory;
import com.store.repository.ProductCategoryRepository;
import com.store.service.ProductCategoryService;
import com.store.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory find(Long id) {
        Optional<ProductCategory> productCategory = repository.findById(id);
        return productCategory.orElseThrow(() -> new ObjectNotFoundException(
                "Product Category with id " + id + " not found"
        ));
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        productCategory.setId(null);
        productCategory = repository.save(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        ProductCategory newObj = find(productCategory.getId());
        updateData(newObj, productCategory);
        return repository.save(newObj);
    }

    private void updateData(ProductCategory newProductCategory, ProductCategory oldProductCategory) {
        newProductCategory.setCategoryName(oldProductCategory.getCategoryName());
        newProductCategory.setProducts(oldProductCategory.getProducts());
    }

    @Override
    public void delete(Long id) {
        find(id);
        repository.deleteById(id);
    }

    public ProductCategory fromDto(ProductCategoryDTO dto) {
        ProductCategory productCategory = new ProductCategory(dto.getId(), dto.getCategoryName());
        return productCategory;
    }

}

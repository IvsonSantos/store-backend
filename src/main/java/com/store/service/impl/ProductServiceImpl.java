package com.store.service.impl;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.repository.ProductRepository;
import com.store.service.ProductCategoryService;
import com.store.service.ProductService;
import com.store.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public Product find(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException(
                "Product with id " + id + " not found"
        ));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        product.setId(null);
        product = repository.save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        Product newObj = find(product.getId());
        updateData(newObj, product);
        return repository.save(newObj);
    }

    private void updateData(Product newProduct, Product oldProduct) {
        newProduct.setActive(oldProduct.isActive());
        newProduct.setCategory(oldProduct.getCategory());
        newProduct.setDescription(oldProduct.getDescription());
        newProduct.setCategory(oldProduct.getCategory());
        newProduct.setImageUrl(oldProduct.getImageUrl());
        newProduct.setName(oldProduct.getName());
        newProduct.setUnitPrice(oldProduct.getUnitPrice());
        newProduct.setUnitsInStock(oldProduct.getUnitsInStock());
    }

    @Override
    public void delete(Long id) {
        find(id);
        repository.deleteById(id);
    }

    public Product fromDto(ProductDTO dto) {
        Product product = new Product(
                dto.getId(),
                productCategoryService.find(dto.getCategory()),
                dto.getName(),
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getImageUrl(),
                dto.isActive(),
                dto.getUnitsInStock(),
                dto.getDateCreated(),
                null
        );

        return product;
    }

}

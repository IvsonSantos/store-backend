package com.store.service;

import com.store.dto.ProductDTO;
import com.store.entity.Product;

import java.util.List;

public interface ProductService {

    Product find(Long id);
    List<Product> findAll();
    Product save(Product client);
    Product update(Product client);
    void delete(Long id);
    Product fromDto(ProductDTO dto);

}

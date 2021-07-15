package com.store.controller;

import com.store.dto.ProductCategoryDTO;
import com.store.entity.ProductCategory;
import com.store.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/product_category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> find(@PathVariable Long id) {
        ProductCategory productCategory = service.find(id);
        return ResponseEntity.ok().body(productCategory);
    }

    @PostMapping()
    public ResponseEntity<ProductCategory> save(@Valid @RequestBody ProductCategoryDTO dto) {
        ProductCategory obj = service.fromDto(dto);
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> findAll() {
        List<ProductCategory> list = this.service.findAll();
        List<ProductCategoryDTO> listDTO = list.stream().map(obj -> new ProductCategoryDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProductCategoryDTO dto, @PathVariable Long id) {
        ProductCategory obj = service.fromDto(dto);
        obj.setId(id);
        this.service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

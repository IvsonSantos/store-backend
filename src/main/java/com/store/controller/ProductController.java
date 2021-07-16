package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        Product product = service.find(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping()
    public ResponseEntity<Product> loginToYoutube(@Valid @RequestBody ProductDTO dto) {
        Product obj = service.fromDto(dto);
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> list = this.service.findAll();
        List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProductDTO dto, @PathVariable Long id) {
        Product obj = service.fromDto(dto);
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

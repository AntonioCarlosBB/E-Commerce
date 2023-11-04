package com.sistempdv.pdv.controller;

import com.sistempdv.pdv.record.ProductRecord;
import com.sistempdv.pdv.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductRecord> findById(@PathVariable Long id) {
        ProductRecord productRecord = productService.findById(id);
        return ResponseEntity.ok(productRecord);
    }

    @GetMapping
    public ResponseEntity<Page<ProductRecord>> findAll(Pageable pageable) {
        Page<ProductRecord> productRecord = productService.findAll(pageable);
        return ResponseEntity.ok(productRecord);

    }

    @PostMapping
    public ResponseEntity<ProductRecord> insert(@Valid @RequestBody ProductRecord productRecord){
        productRecord = productService.insert(productRecord);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productRecord.id()).toUri();
        return ResponseEntity.created(uri).body(productRecord);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductRecord> update(@PathVariable Long id, @Valid @RequestBody ProductRecord productRecord) {
        productRecord = productService.update(id, productRecord);
        return ResponseEntity.ok(productRecord);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.sistempdv.pdv.service;

import com.sistempdv.pdv.record.ProductRecord;
import com.sistempdv.pdv.entity.Product;
import com.sistempdv.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public ProductRecord findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductRecord(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductRecord> findAll(Pageable pageable){
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.map(ProductRecord::new);
    }
}

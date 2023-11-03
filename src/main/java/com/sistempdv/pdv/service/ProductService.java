package com.sistempdv.pdv.service;

import com.sistempdv.pdv.record.ProductRecord;
import com.sistempdv.pdv.entity.Product;
import com.sistempdv.pdv.repository.ProductRepository;
import com.sistempdv.pdv.service.exception.DatabaseException;
import com.sistempdv.pdv.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public ProductRecord findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return new ProductRecord(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductRecord> findAll(Pageable pageable){
        Page<Product> productList = productRepository.findAll(pageable);
        return productList.map(ProductRecord::new);
    }

    @Transactional
    public ProductRecord insert(ProductRecord productRecord){
        Product entity = new Product();
        copyRecordToEntity(productRecord, entity);
        entity = productRepository.save(entity);
        return new ProductRecord(entity);
    }

    @Transactional
    public ProductRecord update(Long id, ProductRecord productRecord){
        try {
            Product entity = productRepository.getReferenceById(id);
            copyRecordToEntity(productRecord, entity);
            entity = productRepository.save(entity);
            return new ProductRecord(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Produto não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!productRepository.existsById(id)){
            throw new ResourceNotFoundException("ID não encontrado!");
        }
        try {
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial!");
        }
    }


    private void copyRecordToEntity(ProductRecord productRecord, Product entity) {
        entity.setName(productRecord.name());
        entity.setDescription(productRecord.description());
        entity.setPrice(productRecord.price());
        entity.setImgUrl(productRecord.imgUrl());
    }
}

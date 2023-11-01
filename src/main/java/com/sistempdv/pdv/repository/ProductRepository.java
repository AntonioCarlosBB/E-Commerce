package com.sistempdv.pdv.repository;

import com.sistempdv.pdv.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

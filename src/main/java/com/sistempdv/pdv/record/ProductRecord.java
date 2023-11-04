package com.sistempdv.pdv.record;

import com.sistempdv.pdv.entity.Product;

public record ProductRecord(Long id, String name, String description, Double price, String imgUrl) {
    public ProductRecord(Product entity){
        this(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getImgUrl());
    }
}

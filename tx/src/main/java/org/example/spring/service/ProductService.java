package org.example.spring.service;

import org.example.spring.model.Product;

public interface ProductService {
    int update(Product product);

    Product findById(Integer id);

    int delete(Integer id);
}

package org.example.spring.service.impl;

import lombok.AllArgsConstructor;
import org.example.spring.dao.ProductDAO;
import org.example.spring.model.Product;
import org.example.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ProductServiceImpl implements ProductService {
    final private ProductDAO productDAO;

    @Override
    public int update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public int delete(Integer id) {
        return productDAO.delete(id);
    }
}

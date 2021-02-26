package org.example.spring.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.dao.ProductDAO;
import org.example.spring.model.Product;
import org.example.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ProductServiceImpl implements ProductService {
    final private ProductDAO productDAO;

    @Override
    public int update(Product product) {
        int update = productDAO.update(product);
//        int i = 1 / 0;
//        System.out.println("i = " + i);
        return update;
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

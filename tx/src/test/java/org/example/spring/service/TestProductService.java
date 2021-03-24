package org.example.spring.service;

import org.example.spring.config.SpringConfig;
import org.example.spring.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class TestProductService {
    @Autowired
    private ProductService service;

    @Test
    public void findById() {
        Product res = service.findById(1001);
        System.out.println("res = " + res);
    }

    @Test
    public void update() {
        Product product = new Product();
        product.setId(1001);
        product.setAmount(-10);
        int res = service.update(product);
        System.out.println("res = " + res);
    }


}

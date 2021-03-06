package org.example.spring.service;

import org.example.spring.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class TestBuyProductService {
    @Autowired
    private BuyProductService buyService;

    @Test
    public void buy() {
        buyService.buy(1001, 11);
    }
}

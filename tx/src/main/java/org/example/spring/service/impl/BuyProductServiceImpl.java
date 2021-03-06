package org.example.spring.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.dao.ProductDAO;
import org.example.spring.dao.SaleRecordDAO;
import org.example.spring.model.Product;
import org.example.spring.model.SaleRecord;
import org.example.spring.service.BuyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BuyProductServiceImpl implements BuyProductService {
    private final ProductDAO productDAO;
    private final SaleRecordDAO saleRecordDAO;

    @Transactional(
//            transactionManager = "txManager",
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ,
            noRollbackFor = {QueryTimeoutException.class},
            timeout = 10)
    @Override
    public void buy(Integer productId, Integer quantity) {
        log.info("BuyProductServiceImpl.buy({},{})", productId, quantity);
        //1. 生成销售记录
        SaleRecord record = new SaleRecord();
        record.setQuantity(quantity);
        record.setGid(productId);
        saleRecordDAO.put(record);

        Product product = productDAO.findById(productId);

        //2.手工异常
        if (product == null) {
            throw new RuntimeException("商品不存在: id=" + productId);
        } else if (product.getAmount() < quantity) {
            throw new RuntimeException("商品库存不足: amount=" + product.getAmount() + ", 需要: q'ty=" + quantity);
        } else if (true) {
            throw new QueryTimeoutException("超时了");
        }

        //2. 更新库存
        Product buyProduct = new Product();
        buyProduct.setId(productId);
        buyProduct.setAmount(quantity);

        productDAO.update(buyProduct);
        log.info("== end ==");

    }
}

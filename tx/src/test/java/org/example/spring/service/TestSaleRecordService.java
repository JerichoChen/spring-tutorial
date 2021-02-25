package org.example.spring.service;

import org.example.spring.config.SpringConfig;
import org.example.spring.model.SaleRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class TestSaleRecordService {
    @Autowired
    private SaleRecordService service;

    @Test
    public void testPut() {
        SaleRecord record = new SaleRecord();
        record.setGid(1001);
        record.setQuantity(10);

        int res = service.put(record);
        System.out.println("res = " + res);
    }

    @Test
    public void testListAll() {
        List<SaleRecord> res = service.listAll();
        res.stream().forEach(r -> System.out.println("record = " + r));
    }

    @Test
    public void testDelete() {
        Integer res = service.delete(111);
        System.out.println("res = " + res);
    }
}

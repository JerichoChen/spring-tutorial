package org.example.spring.service.impl;

import lombok.AllArgsConstructor;
import org.example.spring.dao.SaleRecordDAO;
import org.example.spring.model.SaleRecord;
import org.example.spring.service.SaleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class SaleRecordServiceImpl implements SaleRecordService {
    private final SaleRecordDAO dao;

    @Override
    public int put(SaleRecord record) {
        return dao.put(record);
    }

    @Override
    public List<SaleRecord> listAll() {
        return dao.listAll();
    }

    @Override
    public Integer delete(Integer id) {
        return dao.delete(id);
    }
}

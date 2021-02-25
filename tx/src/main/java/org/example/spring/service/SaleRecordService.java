package org.example.spring.service;

import org.example.spring.model.SaleRecord;

import java.util.List;

public interface SaleRecordService {
    int put(SaleRecord record);

    List<SaleRecord> listAll();

    Integer delete(Integer id);

}

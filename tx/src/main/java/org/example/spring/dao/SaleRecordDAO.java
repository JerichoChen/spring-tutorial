package org.example.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.spring.model.SaleRecord;

import java.util.List;

@Mapper
public interface SaleRecordDAO {
    @Insert("INSERT INTO sale_record (gid, quantity) VALUES " +
            "(#{gid}, #{quantity})")
    int put(SaleRecord record);

    @Select("SELECT * FROM sale_record")
    List<SaleRecord> listAll();

    @Select("DELETE FROM sale_record WHERE id = #{id}")
    Integer delete(Integer id);

}

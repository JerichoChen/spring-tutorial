package org.example.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.spring.model.Product;

@Mapper
public interface ProductDAO {
    @Update("UPDATE produce SET (gid, quantity) VALUES " +
            "(#{gid}, #{quantity})")
    int update(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Integer id);

    @Select("DELETE FROM product WHERE id = #{id}")
    Integer delete(Integer id);

}

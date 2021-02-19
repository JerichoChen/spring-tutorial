package org.example.spring.service;

import org.apache.ibatis.annotations.Param;
import org.example.spring.model.SearchUserDTO;
import org.example.spring.model.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    /*用于 where标签测试*/
    List<User> findByNameOrAge(SearchUserDTO search);

    /*用于foreach标签测试*/
    List<User> findByIds(List<Integer> ids);

    /*用于多个list参数的 foreach标签测试*/
    List<User> findByIdsOrNames(@Param("ids") List<Integer> ids, @Param("names") List<String> names);

    /*用于choose when otherwise选择标签的测试*/
    /*ids不为空的, 按ids查询
     * ids为空时, 按names查询
     * 都为空时, 1=1*/
    List<User> findByIdsOtherwiseNames(@Param("ids") List<Integer> ids, @Param("names") List<String> names);

}

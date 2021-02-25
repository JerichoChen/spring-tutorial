package org.example.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.spring.model.SearchUserDTO;
import org.example.spring.model.User;

import java.util.List;

/**
 * Mapper可以采用注解和XML配置相结合的方式. 只要语句不重复即可.
 */
@SuppressWarnings("unused")
@Mapper
public interface UserDAO {

    /*由xml中配置.*/
    List<User> listAll();

    /*用于 where标签测试*/
    @Select("<script> " +
            "   SELECT * FROM USER_2 " +
            "   <where>" +
            "     <if test=\"userName != null and userName !=''\">" +
            "         user_name = #{userName}" +
            "     </if>" +
            "     <if test='userAge > 0'>" +
            "         OR user_age = #{userAge}" +
            "     </if>" +
            "   </where>" +
            "</script>")
    List<User> findByNameOrAge(SearchUserDTO search);

    /*用于foreach标签测试*/
    @Select("<script>" +
            "SELECT * FROM USER_2" +
            "<where>" +
            "   <if test='!list.isEmpty'>" +
            "       user_id IN" +
            "       <foreach collection='list' item='id' open='(' close=')' separator=',' index='idx'>" +
            "           #{id}" +
            "       </foreach>" +
            "   </if>" +
            " </where>" +
            "</script>")
    List<User> findByIds(List<Integer> ids);

    /*用于多个list参数的 foreach标签测试*/
    @Select("<script>" +
            "   SELECT * FROM USER_2" +
            "   <where>" +
            "       <if test='!ids.isEmpty'>" +
            "           user_id IN" +
            "           <foreach collection='ids' item='id' open='(' close=')' separator=',' index='idx'>" +
            "               #{id}" +
            "           </foreach>" +
            "       </if>" +
            "       <if test='!names.isEmpty'>" +
            "           OR user_name IN" +
            "           <foreach collection='names' item='name' open='(' close=')' separator=',' index='idx'>" +
            "               #{name}" +
            "           </foreach>" +
            "       </if>" +
            "   </where>" +
            "</script>")
    List<User> findByIdsOrNames(@Param("ids") List<Integer> ids, @Param("names") List<String> names);

    /*用于choose when otherwise选择标签的测试*/
    /*ids不为空的, 按ids查询
     * ids为空时, 按names查询
     * 都为空时, 1=1*/

    @Select("<script>" +
            "   SELECT * FROM USER_2" +
            "   <choose>" +
            "       <when test='!ids.isEmpty'>" +
            "           WHERE user_id IN" +
            "           <foreach collection='ids' item='id' open='(' close=')' separator=',' index='idx'>" +
            "               #{id}" +
            "           </foreach>" +
            "       </when>" +
            "       <when test='!names.isEmpty'>" +
            "           WHERE user_name IN" +
            "           <foreach collection='names' item='name' open='(' close=')' separator=',' index='idx'>" +
            "               #{name}" +
            "           </foreach>" +
            "       </when>" +
            "       <otherwise>" +
            "           WHERE user_id > 0" +
            "       </otherwise>" +
            "   </choose>" +
            "</script>")
    List<User> findByIdsOtherwiseNames(@Param("ids") List<Integer> ids, @Param("names") List<String> names);


}

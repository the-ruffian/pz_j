/*
 * @Description:UserRoleDao
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 10:16
 * @LastEditTime: 2021-06-02 10:16
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Permission;
import com.example.demo.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserRoleDao extends BaseMapper<UserRole> {
    @Select("select * from permission where id in" +
            "(select permission_id from role_permission where role_id in" +
            "(select role_id from user_role where user_id in" +
            "(select id from user where phone =#{phone})))")
    List<Permission> selectByPhone(@Param("phone") String phone);
}

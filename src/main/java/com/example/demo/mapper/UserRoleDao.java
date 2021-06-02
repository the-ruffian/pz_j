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
import com.example.demo.entity.User_role;
import org.springframework.stereotype.Component;

@Component
public interface UserRoleDao extends BaseMapper<User_role> {
}

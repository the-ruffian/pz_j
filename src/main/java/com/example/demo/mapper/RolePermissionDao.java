/*
 * @Description:RolePermissionDao
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 10:34
 * @LastEditTime: 2021-06-02 10:34
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role_permission;
import org.springframework.stereotype.Component;

@Component
public interface RolePermissionDao extends BaseMapper<Role_permission> {
}

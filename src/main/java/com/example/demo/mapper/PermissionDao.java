/*
 * @Description:PermissionDao
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 09:51
 * @LastEditTime: 2021-06-02 09:51
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public interface PermissionDao extends BaseMapper<Permission> {
}

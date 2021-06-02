/*
 * @Description:RoleDao
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 10:08
 * @LastEditTime: 2021-06-02 10:08
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleDao extends BaseMapper<Role> {
}

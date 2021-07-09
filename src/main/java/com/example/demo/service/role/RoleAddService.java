/*
 * @Description:新增角色
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-09 21:38:32
 * @LastEditTime: 2021-07-09 21:46:53
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.role;

import com.example.demo.model.dto.RoleAddDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface RoleAddService {
    OpenResponse add(@Param("roleAddDto")RoleAddDto roleAddDto);
}

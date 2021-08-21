/*
 * @Description:新增角色
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-09 21:38:32
 * @LastEditTime: 2021-08-21 18:05:21
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.RoleAddDto;
import com.example.demo.model.dto.RoleDeleteDto;
import com.example.demo.model.dto.RoleListDto;
import com.example.demo.model.dto.RoleUpdateDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface RoleService {
    OpenResponse add(@Param("roleAddDto")RoleAddDto roleAddDto);
    OpenResponse search(@Param("roleListDto")RoleListDto roleListDto);
    OpenResponse update(@Param("roleUpdateDto")RoleUpdateDto roleUpdateDto);
    OpenResponse delete(@Param("roleDeleteDto")RoleDeleteDto roleDeleteDto);
    OpenResponse permission();
}

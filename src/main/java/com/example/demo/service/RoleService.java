/*
 * @Description:新增角色
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-09 21:38:32
 * @LastEditTime: 2021-8-23 21:00:20
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.*;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface RoleService {
    OpenResponse add(@Param("roleAddDto")RoleAddDto roleAddDto);
    OpenResponse search(@Param("roleListDto")RoleListDto roleListDto);
    OpenResponse update(@Param("roleUpdateDto")RoleUpdateDto roleUpdateDto);
    OpenResponse delete(@Param("roleDeleteDto")RoleDeleteDto roleDeleteDto);
    OpenResponse permission();
    OpenResponse fixstatus(@Param("roleStatusDto")RoleStatusDto roleStatusDto);
}

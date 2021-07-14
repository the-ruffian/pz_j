/*
 * @Description:删除角色入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-14 19:19
 * @LastEditTime: 2021-7-14 19:34:21
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDeleteDto {
    @ApiModelProperty(value = "角色名")
    private Integer roleId;
}

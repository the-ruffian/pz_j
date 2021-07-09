/*
 * @Description:UserDeleteService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-07 20:55
 * @LastEditTime: 2021-07-07 20:55
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class RoleAddDto {
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
}

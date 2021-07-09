/*
 * @Description:新增角色请求参数
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-07 20:55
 * @LastEditTime: 2021-07-09 21:50:29
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleAddDto {
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
}

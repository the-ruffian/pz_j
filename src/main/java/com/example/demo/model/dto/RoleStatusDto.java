/*
 * @Description:RoleStatusDto
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-23 20:52
 * @LastEditTime: 2021-08-23 20:52
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleStatusDto {
    @ApiModelProperty(value = "角色状态")
    private Integer status;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}

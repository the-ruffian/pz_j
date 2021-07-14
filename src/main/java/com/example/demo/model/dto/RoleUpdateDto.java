/*
 * @Description:修改角色入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-14 17:20
 * @LastEditTime: 2021-7-14 19:20:34
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleUpdateDto {
    @ApiModelProperty(value = "旧角色名")
    private String oldName;
    @ApiModelProperty(value = "新角色名")
    private String newName;
    @ApiModelProperty(value = "备注")
    private String note;
}

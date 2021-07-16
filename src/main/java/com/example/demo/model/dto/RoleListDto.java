/*
 * @Description:角色列表参数
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-14 10:42
 * @LastEditTime: 2021-7-16 17:55:15
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleListDto {
    @ApiModelProperty(value = "页码")
    private Integer pageNo;
    @ApiModelProperty(value = "条数")
    private Integer pageSize;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
}

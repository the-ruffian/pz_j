/*
 * @Description:用户菜单权限入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-18 16:32:50
 * @LastEditTime: 2021-07-18 16:33:42
 * @LastEditors: the-ruffian
 */

package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserMenuListDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
}

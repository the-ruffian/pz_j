/*
 * @Description:UserLoginDto
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-04 16:45
 * @LastEditTime: 2021-07-04 16:45
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码,MD5加密")
    private String password;
}

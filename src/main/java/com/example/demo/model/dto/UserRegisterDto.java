/*
 * @Description:UserDto
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-01 21:10
 * @LastEditTime: 2021-07-01 21:10
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRegisterDto {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码(md5加密)")
    private String password;
    @ApiModelProperty(value = "性别(0女、1男、2保密)")
    private Integer gender;
    @ApiModelProperty(value = "邮箱地址")
    private String email;

}
